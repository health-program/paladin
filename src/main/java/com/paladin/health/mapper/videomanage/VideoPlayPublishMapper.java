package com.paladin.health.mapper.videomanage;

import java.util.List;
import com.paladin.health.model.videomanage.VideoPlayPublish;
import com.paladin.health.service.videomanage.dto.VideoPlayPublishDTO;
import com.paladin.health.service.videomanage.dto.VideoPlayPublishQueryDTO;
import com.paladin.health.service.videomanage.vo.PublishedVideoVO;
import com.paladin.health.service.videomanage.vo.VideoPlayPublishVO;
import com.paladin.framework.core.configuration.mybatis.CustomMapper;

public interface VideoPlayPublishMapper extends CustomMapper<VideoPlayPublish>{
	public int updateCount (VideoPlayPublish videoPlayPublish);
	public List<PublishedVideoVO> searchPublishedVideo(VideoPlayPublishQueryDTO query);
	public List<VideoPlayPublishVO> getStatistics(VideoPlayPublishDTO videoPlayPublishDTO);
}