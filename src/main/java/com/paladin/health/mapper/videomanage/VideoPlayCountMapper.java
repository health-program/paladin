package com.paladin.health.mapper.videomanage;

import com.paladin.health.service.videomanage.dto.VideoPlayCountQueryDTO;
import com.paladin.health.service.videomanage.vo.VideoPlayCountVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoPlayCountMapper {
	List<VideoPlayCountVO> getStatisticsByAgency(@Param("query") VideoPlayCountQueryDTO query);

	List<VideoPlayCountVO> getStatisticsByYear(@Param("query") VideoPlayCountQueryDTO query);
}
