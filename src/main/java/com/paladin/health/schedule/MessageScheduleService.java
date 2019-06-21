package com.paladin.health.schedule;

import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.paladin.framework.common.PageResult;
import com.paladin.health.model.sms.SmsSendResponse;
import com.paladin.health.service.diagnose.DiagnoseTargetService;
import com.paladin.health.service.diagnose.vo.DiagnoseTargetSimpleVO;
import com.paladin.health.service.publicity.PublicityMessageService;
import com.paladin.health.service.publicity.vo.PublicityMessageVO;
import com.paladin.health.service.sms.SendMsgWebService;

@Component
public class MessageScheduleService {

	private static Logger logger = LoggerFactory.getLogger(MessageScheduleService.class);

	@Autowired
	private PublicityMessageService publicityMessageService;

	@Autowired
	private DiagnoseTargetService diagnoseTargetService;

	@Autowired
	private SendMsgWebService sendMsgWebService;

	
	private Pattern cellphonePattern = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
	

	
	
	// 0 0 2 * * ? 每天凌晨2点执行
	/**
	 * 每小时执行
	 */
	@Scheduled(cron = "0 0 */1 * * ?")
	public void sendMessage() {
		
		Calendar now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR_OF_DAY);
				
		List<PublicityMessageVO> messages = publicityMessageService.findSendMessage(hour);

		for (PublicityMessageVO message : messages) {

			String id = message.getId();
			String publishTarget = message.getPublishTarget();
			String content = message.getContent();
			int count = 0;
			
			int limit = 100;
			int offset = 0;

			PageResult<DiagnoseTargetSimpleVO> targets = null;

			do {
				try {
					targets = diagnoseTargetService.findTarget2SendMessage(publishTarget, offset, limit);
				} catch (Exception e) {
					logger.error("发送短信失败，查找短信发送目标异常：" + publishTarget, e);
					break;
				}

				for (DiagnoseTargetSimpleVO target : targets.getData()) {
					try {
						String cellphone = target.getCellphone();
						if(!cellphonePattern.matcher(cellphone).matches()) {
							continue;
						}
						
						System.out.println("发送短信----------"+cellphone);
						
						SmsSendResponse resp = sendMsgWebService.sendSms(cellphone, content);
						if (resp != null && SmsSendResponse.RESULT_SUCCESS.equals(resp.getResult())) {
							count++;
							continue;
						}
						
						logger.error("发送短信[ID:" + id + "]失败，发送目标[" + target.getName() + ":" + target.getCellphone() + "]");
					} catch (Exception e) {
						logger.error("发送短信[ID:" + id + "]失败，发送目标[" + target.getName() + ":" + target.getCellphone() + "]", e);
					}
				}

				offset += limit;
			} while (offset < targets.getTotal());

			publicityMessageService.updateToSended(id, count);
		}

	}

}
