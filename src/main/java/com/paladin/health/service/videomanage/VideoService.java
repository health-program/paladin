package com.paladin.health.service.videomanage;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.Condition;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.health.controller.videomanage.VideoExamineQueryVO;
import com.paladin.health.core.HealthUserSession;
import com.paladin.health.mapper.videomanage.VideoMapper;
import com.paladin.health.model.videomanage.Video;
import com.paladin.health.service.videomanage.dto.VideoQueryDTO;
import com.paladin.health.service.videomanage.vo.VideoShowVO;
import com.paladin.health.service.videomanage.vo.VideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService extends ServiceSupport<Video> {

	@Autowired
	private VideoMapper videoMapper;

	public PageResult<VideoVO> findSelfVideoPage(VideoQueryDTO query) {
		Page<VideoVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		query.setCreateUser(HealthUserSession.getCurrentUserSession().getUserId());
		videoMapper.findSelfVideo(query);
		return new PageResult<>(page);
	}

	public PageResult<VideoShowVO> findPlayVideoPage(VideoQueryDTO pages) {
		Page<VideoShowVO> page = PageHelper.offsetPage(pages.getOffset(), pages.getLimit());
		videoMapper.findPlayVideo(pages);
		return new PageResult<>(page);
	}
	
	public List<VideoShowVO> findTopPlayVideo() {		
		return videoMapper.findTopPlayVideo();
	}

	public PageResult<VideoVO> findExamineVideoPage(VideoExamineQueryVO query) {
		Page<VideoVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		videoMapper.findExamineVideo(query);
		return new PageResult<>(page);
	}
	

	public VideoVO getVideo(String id) {
		return videoMapper.getVideo(id);
	}

	public boolean examine(String id, boolean success) {
		Video video = get(id);
		if (video == null) {
			throw new BusinessException("找不到需要审核的信息数据");
		}

		Integer status = video.getStatus();

		if (status != Video.STATUS_SUBMIT_EXAMINE) {
			throw new BusinessException("当前视频不是待审核状态！");
		}

		status = success ? Video.STATUS_EXAMINE_SUCCESS : Video.STATUS_EXAMINE_FAIL;

		if (success) {
			// TODO 成功推送
		}

		if (videoMapper.updateExamineStatus(id, status, HealthUserSession.getCurrentUserSession().getUserId()) > 0) {
			return true;
		} else {
			throw new BusinessException("审核失败");
		}
	}
	
	public int updateByOrderNo(String[] ids) {
		int staus = 0;
		for (int i = 0; i < ids.length; i++) {
			String idAndNo = ids[i];
			String[] strings = idAndNo.split(":");
			String id = strings[0];
			String topNo = strings[1];
			List<Video> oldVideos = searchAll(new Condition(Video.COLUMN_FIELD_TOP_ORDER_NO, QueryType.EQUAL, topNo));
			Video video = new Video();
			video.setTop(1);
			video.setId(id);
			video.setTopOrderNo(Integer.valueOf(topNo.trim()));
			if (oldVideos != null && oldVideos.size() > 0) {
				Video oldVideo = oldVideos.get(0);
				if (!id.equals(oldVideo.getId())) {
					int j = cancelTopById(oldVideo.getId());
					if (j > 0) {
						staus += updateSelective(video);
					}
				}
			} else {
				staus += updateSelective(video);
			}
		}
		return staus;
	}
	
	public int cancelTopById(String id) {
		Video model = new Video();
		model.setId(id);
		model.setTop(0);
		model.setTopOrderNo(Video.TOP_NUMBER);
		return updateSelective(model);
	}

}