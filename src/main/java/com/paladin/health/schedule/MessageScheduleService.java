package com.paladin.health.schedule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.paladin.framework.common.PageResult;
import com.paladin.health.service.diagnose.DiagnoseTargetService;
import com.paladin.health.service.diagnose.vo.DiagnoseTargetSimpleVO;
import com.paladin.health.service.publicity.PublicityMessageService;
import com.paladin.health.service.publicity.vo.PublicityMessageVO;
import com.paladin.health.service.sms.ShortMessageSender;

@Component
public class MessageScheduleService {

	private static Logger logger = LoggerFactory.getLogger(MessageScheduleService.class);

	@Autowired
	private PublicityMessageService publicityMessageService;

	@Autowired
	private DiagnoseTargetService diagnoseTargetService;

	@Autowired
	private ShortMessageSender shortMessageSender;

	// 0 0 2 * * ? 每天凌晨2点执行
	/**
	 * 每天凌晨2点执行，发送消息
	 */
	@Scheduled(cron = "0 0 2 * * ?")
	public void sendMessage() {

		List<PublicityMessageVO> messages = publicityMessageService.findSendMessage();

		for (PublicityMessageVO message : messages) {

			String id = message.getId();
			String publishTarget = message.getPublishTarget();
			String content = "【健康促进中心】" + message.getContent();

			int limit = 100;
			int offset = 0;

			PageResult<DiagnoseTargetSimpleVO> result = null;

			do {
				try {
					result = diagnoseTargetService.findTarget2SendMessage(publishTarget, offset, limit);
				} catch (Exception e) {
					logger.error("发送短信失败，查找短信发送目标异常：" + publishTarget, e);
					break;
				}

				for (DiagnoseTargetSimpleVO target : result.getData()) {
					try {
						shortMessageSender.sendMessage(content, target.getCellphone());
						
						// TODO 记录发送成功或失败到数据库
					} catch (Exception e) {
						logger.error("发送短信[ID:" + id + "]失败，发送目标[" + target.getName() + ":" + target.getCellphone() + "]", e);
					}						
				}

				offset += limit;
			} while (offset < result.getTotal());
			
			publicityMessageService.updateToSended(id);
		}

	}

}
