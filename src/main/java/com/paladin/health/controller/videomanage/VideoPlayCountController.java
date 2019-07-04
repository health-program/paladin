package com.paladin.health.controller.videomanage;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.videomanage.VideoPlayCountService;
import com.paladin.health.service.videomanage.dto.VideoPlayCountQueryDTO;
import com.paladin.health.service.videomanage.vo.VideoPlayCountShowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/health/video/play/count")
public class VideoPlayCountController extends ControllerSupport {

    @Autowired
    private VideoPlayCountService videoPlayCountService;

    @RequestMapping("/index")
    public String index(){return "/health/videomanage/video_play_count_index";}
    
    @RequestMapping("/agency/index")
    public String agencyIndex(){return "/health/videomanage/video_play_count_agency_index";}
    
    @RequestMapping("/year")
    @ResponseBody
    @QueryOutputMethod(queryClass = VideoPlayCountQueryDTO.class, paramIndex = 0)
    public Object publictyCount(VideoPlayCountQueryDTO query) {
    	List<VideoPlayCountShowVO>list=beanCopyList(videoPlayCountService.getStatisticsByYear(query),VideoPlayCountShowVO.class);
    	if(!list.isEmpty()){
    		for(VideoPlayCountShowVO videoPlayCountShowVO :list){
    			videoPlayCountShowVO.setDurations((videoPlayCountShowVO.getDuration()/(1000*3600.0)));
        	}
    	}
    	
        return CommonResponse.getSuccessResponse(list);
    }
    
    @RequestMapping("/agency")
    @ResponseBody
    @QueryOutputMethod(queryClass = VideoPlayCountQueryDTO.class, paramIndex = 0)
    public Object publictyAgencyCount(VideoPlayCountQueryDTO query) {
        if (query.getId() == null) {
            return CommonResponse.getSuccessResponse(videoPlayCountService.getStatisticsByAgency(query));
        }else {
            return CommonResponse.getSuccessResponse(videoPlayCountService.videoAgencyChildsCount(query.getId()));
        }
    }
}