package com.paladin.health.service.videomanage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.health.mapper.videomanage.VideoPlayPublishMapper;
import com.paladin.health.model.videomanage.VideoPlayPublish;
import com.paladin.health.service.videomanage.dto.VideoPlayPublishDTO;
import com.paladin.health.service.videomanage.dto.VideoPlayPublishQueryDTO;
import com.paladin.health.service.videomanage.vo.PublishedVideoVO;
import com.paladin.health.service.videomanage.vo.VideoPlayPublishVO;
import com.paladin.framework.core.ServiceSupport;

@Service
public class VideoPlayPublishService extends ServiceSupport<VideoPlayPublish> {
	
	@Autowired
	private VideoPlayPublishMapper videoPlayPublishMapper;
	
	public List<PublishedVideoVO> searchPublishedVideo(VideoPlayPublishQueryDTO query) {
		return videoPlayPublishMapper.searchPublishedVideo(query);
	}
	
	public int updateOrSave (String videoId){
		VideoPlayPublish videoPlayPublish = new VideoPlayPublish();
		videoPlayPublish.setVideoId(videoId);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		videoPlayPublish.setDate(sdf.format(calendar.getTime()));
		int row=videoPlayPublishMapper.updateCount(videoPlayPublish);
		if(row>0){
			return row;
		}
		videoPlayPublish.setCount(0);
		videoPlayPublish.setYear(calendar.get(Calendar.YEAR));
		videoPlayPublish.setMonth(calendar.get(Calendar.MONTH));
		videoPlayPublish.setDay(calendar.get(Calendar.DAY_OF_MONTH));
		return videoPlayPublishMapper.insert(videoPlayPublish);
	}
	
	public List<VideoPlayPublishVO> getStatistics(VideoPlayPublishDTO videoPlayPublishDTO){
		return videoPlayPublishMapper.getStatistics(videoPlayPublishDTO);
		
	}
}