package com.paladin.health.service.videomanage;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.health.mapper.videomanage.VideoPlayPublishMapper;
import com.paladin.health.model.videomanage.VideoPlayPublish;
import com.paladin.health.service.videomanage.dto.VideoPlayPublishDTO;
import com.paladin.health.service.videomanage.dto.VideoPlayPublishQueryDTO;
import com.paladin.health.service.videomanage.vo.PublishedVideoVO;
import com.paladin.health.service.videomanage.vo.VideoPlayPublishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class VideoPlayPublishService extends ServiceSupport<VideoPlayPublish> {
	
	@Autowired
	private VideoPlayPublishMapper videoPlayPublishMapper;
	
	public PageResult<PublishedVideoVO> searchPublishedVideo(VideoPlayPublishQueryDTO query) {
		Page<PublishedVideoVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		videoPlayPublishMapper.searchPublishedVideo(query);
		return new PageResult<>(page);
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
		videoPlayPublish.setMonth(calendar.get(Calendar.MONTH) + 1 );
		videoPlayPublish.setDay(calendar.get(Calendar.DAY_OF_MONTH));
		return save(videoPlayPublish);
	}
	
	public List<VideoPlayPublishVO> getStatistics(VideoPlayPublishDTO videoPlayPublishDTO){
		return videoPlayPublishMapper.getStatistics(videoPlayPublishDTO);
		
	}
	
}