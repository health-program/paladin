package com.paladin.health.schedule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.paladin.framework.common.Condition;
import com.paladin.framework.common.QueryType;
import com.paladin.health.model.publicity.PublicityMessage;
import com.paladin.health.model.publicity.PublicityMessagePush;
import com.paladin.health.service.publicity.PublicityMessagePushService;
import com.paladin.health.service.publicity.PublicityMessageService;

@Component
public class MessageScheduleService {

	private static Logger logger = LoggerFactory.getLogger(MessageScheduleService.class);

	@Autowired
	private PublicityMessageService publicityMessageService;

	@Autowired
	private PublicityMessagePushService publicityMessagePushService;

	// 0 0 1 * * ? 每天凌晨1点执行
	/**
	 * 每天凌晨1点执行，检查需要推送的消息
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	public void checkMessage() {

		List<PublicityMessage> messages = publicityMessageService
				.searchAll(new Condition(PublicityMessage.COLUMN_FIELD_STATUS, QueryType.EQUAL, PublicityMessage.STATUS_EXAMINE_SUCCESS));

		for (PublicityMessage message : messages) {
			String id = message.getId();
			publicityMessagePushService.pushMessage(id, message.getType(), message.getPublishTime());
		}
	}

	// 0 0 2 * * ? 每天凌晨2点执行
	/**
	 * 每天凌晨2点执行，发送消息
	 */
	@Scheduled(cron = "0 0 2 * * ?")
	public void sendMessage() {

		List<PublicityMessagePush> messagePushs = publicityMessagePushService.findSendMessage();

		PublicityMessage message = null;
		String messageId = null;

		for (PublicityMessagePush messagePush : messagePushs) {
			String id = messagePush.getMessageId();
			if (!id.equals(messageId) || message == null) {
				messageId = id;
				message = publicityMessageService.get(messageId);
			}

			int result = 0;

			int channel = messagePush.getChannel();
			
			// TODO 发送信息
//			if (channel == PublicityMessagePush.CHANNEL_APP) {
//				logger.info("发送消息给APP[" + id + "]");
//				result = PublicityMessagePush.STATUS_SEND_SUCCESS;
//			} else if (channel == PublicityMessagePush.CHANNEL_CELLPHONE) {
//				logger.info("发送消息给短信[" + id + "]");
//				result = PublicityMessagePush.STATUS_SEND_SUCCESS;
//			} else if (channel == PublicityMessagePush.CHANNEL_WEIXIN) {
//				logger.info("发送消息给微信[" + id + "]");
//				result = PublicityMessagePush.STATUS_SEND_SUCCESS;
//			} else {
//				result = 99;
//			}
			
			//TODO 发送信息

			Integer times = messagePush.getTryTimes();
			if (times == null)
				times = 0;

			messagePush.setTryTimes(times + 1);
			messagePush.setStatus(result);

			publicityMessagePushService.update(messagePush);
		}
	}

}
