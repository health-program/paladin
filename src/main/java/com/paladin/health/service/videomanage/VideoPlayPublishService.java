package com.paladin.health.service.videomanage;

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
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		videoPlayPublish.setDate(calendar.getTime());
		int row=videoPlayPublishMapper.updateCount(videoPlayPublish);
		if(row>0){
			return row;
		}
		videoPlayPublish.setCount(1);
		videoPlayPublish.setYear(calendar.get(Calendar.YEAR));
		videoPlayPublish.setMonth(calendar.get(Calendar.MONTH));
		videoPlayPublish.setDay(calendar.get(Calendar.DAY_OF_MONTH));
		return save(videoPlayPublish);
	}
	
	public List<VideoPlayPublishVO> getStatistics(VideoPlayPublishDTO videoPlayPublishDTO){
		return videoPlayPublishMapper.getStatistics(videoPlayPublishDTO);
		
	}
	
}