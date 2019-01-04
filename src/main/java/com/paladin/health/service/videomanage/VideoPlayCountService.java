package com.paladin.health.service.videomanage;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paladin.health.mapper.videomanage.VideoPlayCountMapper;
import com.paladin.health.service.videomanage.dto.VideoPlayCountQueryDTO;
import com.paladin.health.service.videomanage.vo.VideoPlayCountVO;

@Service
public class VideoPlayCountService {

	@Autowired
	private VideoPlayCountMapper videoPlayCountMapper;

	public List<VideoPlayCountVO> getStatisticsByAgency(VideoPlayCountQueryDTO query) {
		return videoPlayCountMapper.getStatisticsByAgency(query);

	}

	public List<VideoPlayCountVO> getStatisticsByYear(VideoPlayCountQueryDTO query) {
		return videoPlayCountMapper.getStatisticsByYear(query);

	}

}
