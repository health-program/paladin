package com.paladin.health.service.videomanage;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.health.mapper.videomanage.VideoMapper;
import com.paladin.health.model.videomanage.Video;
import com.paladin.health.service.videomanage.dto.VideoQueryDTO;
import com.paladin.health.service.videomanage.vo.VideoVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;

@Service
public class VideoService extends ServiceSupport<Video> {
      
      @Autowired
      private VideoMapper videoMapper;

      public PageResult<VideoVO> searchPageList(VideoQueryDTO query) {
            Page<VideoVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
            videoMapper.searchPageList(query);
            return new PageResult<>(page);
      }

      public List<Video> findLabelList() {
            return videoMapper.findLabelList();
      }


}