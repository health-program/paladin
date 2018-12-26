package com.paladin.health.service.videomanage;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.health.mapper.videomanage.VideoMapper;
import com.paladin.health.model.videomanage.Video;
import com.paladin.health.service.videomanage.dto.VideoQueryDTO;
import com.paladin.health.service.videomanage.vo.VideoVO;
import com.paladin.framework.core.ServiceSupport;

@Service
public class VideoService extends ServiceSupport<Video> {
      
      @Autowired
      private VideoMapper videoMapper;

      public List<VideoVO> searchPageList(VideoQueryDTO query) {
            return videoMapper.searchPageList(query);
      }

      public List<Video> findLabelList() {
            return videoMapper.findLabelList();
      }


}