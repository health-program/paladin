package com.paladin.health.mapper.videomanage;

import java.util.List;
import com.paladin.health.service.videomanage.dto.VideoPlayCountQueryDTO;
import com.paladin.health.service.videomanage.vo.VideoPlayCountVO;

public interface VideoPlayCountMapper {
	List<VideoPlayCountVO> getStatisticsByAgency(VideoPlayCountQueryDTO query);
	List<VideoPlayCountVO> getStatisticsByYear(VideoPlayCountQueryDTO query);
}
