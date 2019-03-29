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

    List<PublicityMessageVO> findDisplay();
    
    List<PublicityMessageVO> findPublishedMessage(PublicityMessageQueryDTO query);

	PublicityMessageVO findPreMessage(@Param("id")String id, @Param("publishTime")Date publishTime);


	PublicityMessageVO findNextMessage(@Param("id")String id, @Param("publishTime")Date publishTime);
}