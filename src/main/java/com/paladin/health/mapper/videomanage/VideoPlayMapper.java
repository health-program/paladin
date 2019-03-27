package com.paladin.health.mapper.videomanage;

import java.util.List;
import com.paladin.health.model.videomanage.VideoPlay;
import com.paladin.health.service.videomanage.dto.VideoPlayQueryDTO;
import com.paladin.health.service.videomanage.vo.VideoPlayVO;
import com.paladin.framework.core.configuration.mybatis.CustomMapper;

public interface VideoPlayMapper extends CustomMapper<VideoPlay> {
	public List<VideoPlayVO> findVideoPlayByVideo(VideoPlayQueryDTO videoPlayQueryDTO);
	public VideoPlayVO getOne(String id);
}