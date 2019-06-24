package com.paladin.health.mapper.publicity;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.health.model.publicity.PublicityMessage;
import com.paladin.health.service.publicity.dto.PublicityMessageQueryDTO;
import com.paladin.health.service.publicity.vo.PublicityMessageVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PublicityMessageMapper extends CustomMapper<PublicityMessage> {

	public int updateExamineStatus(@Param("id") String id, @Param("status") Integer status, @Param("examinerId") String examinerId);

	public List<PublicityMessageVO> findMessage(PublicityMessageQueryDTO query);

	public PublicityMessageVO getMessage(@Param("id") String id);

	public List<PublicityMessageVO> findDisplay();

	public List<PublicityMessageVO> findPublishedMessage(PublicityMessageQueryDTO query);

	public PublicityMessageVO findPreMessage(@Param("id") String id, @Param("publishTime") Date publishTime);

	public PublicityMessageVO findNextMessage(@Param("id") String id, @Param("publishTime") Date publishTime);

	public List<PublicityMessageVO> findSendMessage(@Param("hour") Integer hour);

	public int updateToSended(@Param("id") String id, @Param("count") Integer count);
	public List<PublicityMessageVO> findSendMessage();

	public int updateToSended(@Param("id") String id);

	List<PublicityMessageVO> findSearchMessage(@Param("query") PublicityMessageQueryDTO query);
}