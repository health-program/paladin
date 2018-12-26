package com.paladin.health.mapper.publicity;

import com.paladin.health.model.publicity.PublicityMessage;
import com.paladin.health.service.publicity.dto.PublicityMessageQueryDTO;
import com.paladin.health.service.publicity.vo.PublicityMessageVO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paladin.framework.mybatis.CustomMapper;

public interface PublicityMessageMapper extends CustomMapper<PublicityMessage> {

	public int updateExamineStatus(@Param("id") String id, @Param("status") Integer status, @Param("examinerId") String examinerId);

	public List<PublicityMessageVO> findMessage(PublicityMessageQueryDTO query);

	public PublicityMessageVO getMessage(@Param("id") String id);
}