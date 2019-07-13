package com.paladin.health.mapper.videomanage;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.health.controller.videomanage.VideoExamineQueryVO;
import com.paladin.health.model.videomanage.Video;
import com.paladin.health.service.videomanage.dto.VideoMessageQuery;
import com.paladin.health.service.videomanage.dto.VideoQueryDTO;
import com.paladin.health.service.videomanage.vo.VideoShowVO;
import com.paladin.health.service.videomanage.vo.VideoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper extends CustomMapper<Video> {

      public List<VideoVO> findSelfVideo(VideoQueryDTO query);

      public List<VideoShowVO> findTopPlayVideo();

      public List<VideoShowVO> findPlayVideo(VideoQueryDTO query);

      public List<VideoVO> findExamineVideo(VideoExamineQueryVO query);

      public int updateExamineStatus(@Param("id") String id, @Param("status") Integer status, @Param("examinerId") String examinerId);

      public VideoVO getVideo(@Param("id") String id);

      List<VideoShowVO> findSearchVideo(@Param("query") VideoMessageQuery query);

	public int upVideo(@Param("id") String id);
	
	public int offVideo(@Param("id") String id);
}