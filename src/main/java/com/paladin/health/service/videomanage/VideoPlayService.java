package com.paladin.health.service.videomanage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.health.mapper.videomanage.VideoPlayMapper;
import com.paladin.health.model.videomanage.VideoPlay;
import com.paladin.health.service.videomanage.dto.VideoPlayQueryDTO;
import com.paladin.health.service.videomanage.vo.VideoPlayVO;
import com.paladin.framework.core.ServiceSupport;

@Service
public class VideoPlayService extends ServiceSupport<VideoPlay> {
	
	@Autowired
	private VideoPlayMapper videoPlayMapper;
	
	public List<VideoPlayVO>selectByQuery(VideoPlayQueryDTO videoPlayQueryDTO){
		return videoPlayMapper.selectByQuery(videoPlayQueryDTO);
	}
	
	public VideoPlayVO getOne(String id){
		return videoPlayMapper.getOne(id);
	}
}