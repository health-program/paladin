package com.paladin.health.mapper.publicity;

import com.paladin.health.model.publicity.PublicityMessagePush;

import java.util.List;

import com.paladin.framework.mybatis.CustomMapper;

public interface PublicityMessagePushMapper extends CustomMapper<PublicityMessagePush> {
	/**
	 * 查找发送的消息
	 * 
	 * @return
	 */
	public List<PublicityMessagePush> findSendMessage();
}