package com.paladin.health.controller.open;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.publicity.PublicityMaterialGrantCountService;
import com.paladin.health.service.publicity.dto.PublicityMaterialGrantQueryCountDTO;
import com.paladin.health.service.videomanage.VideoPlayCountService;
import com.paladin.health.service.videomanage.dto.VideoPlayCountQueryDTO;
import com.paladin.health.service.videomanage.vo.VideoPlayCountShowVO;

@Controller
@RequestMapping("/platform")
public class PlatformController extends ControllerSupport {

	@Autowired
	private PublicityMaterialGrantCountService publicityMaterialGrantCountService;

	@Autowired
	private VideoPlayCountService videoPlayCountService;

	@RequestMapping("/grant/index")
	public String grantIndex() {
		return "/health/publicity/publicity_material_grant_agency_index_open";
	}

	@RequestMapping("/grant/year")
	@ResponseBody
	public Object publictyCount(PublicityMaterialGrantQueryCountDTO query) {
		return CommonResponse.getSuccessResponse(publicityMaterialGrantCountService.publictyCount(query));
	}

	@RequestMapping("/grant/agency")
	@ResponseBody
	public Object publictyAgencyCount(PublicityMaterialGrantQueryCountDTO query) {
		if (query.getId() == null) {
			return CommonResponse.getSuccessResponse(publicityMaterialGrantCountService.publictyAgencyPageCount(query));
		} else {
			return CommonResponse.getSuccessResponse(publicityMaterialGrantCountService.publictyAgencyChildsCount(query.getId()));
		}
	}

	@RequestMapping("/video/play/index")
	public String videoPalyIndex() {
		return "/health/videomanage/video_play_count_agency_index_open";
	}

	@RequestMapping("/video/play/year")
	@ResponseBody
	public Object publictyCount(VideoPlayCountQueryDTO query) {
		List<VideoPlayCountShowVO> list = beanCopyList(videoPlayCountService.getStatisticsByYear(query), VideoPlayCountShowVO.class);
		if (!list.isEmpty()) {
			for (VideoPlayCountShowVO videoPlayCountShowVO : list) {
				videoPlayCountShowVO.setDurations((videoPlayCountShowVO.getDuration() / (1000 * 3600.0)));
			}
		}

		return CommonResponse.getSuccessResponse(list);
	}

	@RequestMapping("/video/play/agency")
	@ResponseBody
	public Object publictyAgencyCount(VideoPlayCountQueryDTO query) {
		if (query.getId() == null) {
			return CommonResponse.getSuccessResponse(videoPlayCountService.getStatisticsByAgency(query));
		} else {
			return CommonResponse.getSuccessResponse(videoPlayCountService.videoAgencyChildsCount(query.getId()));
		}
	}

}
