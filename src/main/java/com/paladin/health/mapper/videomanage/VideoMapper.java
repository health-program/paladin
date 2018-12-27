package com.paladin.health.mapper.videomanage;

import com.paladin.health.model.videomanage.Video;
import com.paladin.health.service.videomanage.dto.VideoQueryDTO;
import com.paladin.health.service.videomanage.vo.VideoVO;

import java.util.List;

import com.paladin.framework.mybatis.CustomMapper;

public interface VideoMapper extends CustomMapper<Video>{

      List<VideoVO> searchPageList(VideoQueryDTO query);

      List<Video> findLabelList();

}