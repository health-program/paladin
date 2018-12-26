package com.paladin.health.service.publicity;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.health.mapper.publicity.PublicityMessagePushMapper;
import com.paladin.health.model.publicity.PublicityMessagePush;
import com.paladin.framework.core.ServiceSupport;

@Service
public class PublicityMessagePushService extends ServiceSupport<PublicityMessagePush>{

	@Autowired
	private PublicityMessagePushMapper publicityMessagePushMapper;
	
	/**
	 * 查找需要发送的消息
	 * @return
	 */
	public List<PublicityMessagePush> findSendMessage() {
		return publicityMessagePushMapper.findSendMessage();
	}
	
	
	/**
	 * 推送消息进入发送流程
	 * 
	 * @param messageId
	 * @param channel
	 * @param publishTime
	 */
	public void pushMessage(String messageId, int channel, Date publishTime) {
		if (publishTime == null || publishTime.getTime() < System.currentTimeMillis()) {
			pushMessage(messageId, channel, PublicityMessagePush.STATUS_SENDING);
		} else {
			pushMessage(messageId, channel, PublicityMessagePush.STATUS_WAITING);
		}
	}

	/**
	 * 推送消息进入发送流程
	 * 
	 * @param messageId
	 * @param channel
	 * @param status
	 */
	public void pushMessage(String messageId, int channel, int status) {
		PublicityMessagePush push = new PublicityMessagePush();
		push.setMessageId(messageId);
		push.setChannel(channel);
		push.setStatus(status);
		push.setTryTimes(0);
		save(push);
	}
	
}