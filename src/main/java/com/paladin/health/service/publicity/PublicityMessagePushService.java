package com.paladin.health.service.publicity;

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
	
}