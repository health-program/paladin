package com.paladin.health.controller.videomanage;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.videomanage.VideoPlayCountService;
import com.paladin.health.service.videomanage.dto.VideoPlayCountQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return CommonResponse.getSuccessResponse(videoPlayCountService.getStatisticsByYear(query));
    }
    
    @RequestMapping("/agency")
    @ResponseBody
    @QueryOutputMethod(queryClass = VideoPlayCountQueryDTO.class, paramIndex = 0)
    public Object publictyAgencyCount(VideoPlayCountQueryDTO query) {
        return CommonResponse.getSuccessResponse(videoPlayCountService.getStatisticsByAgency(query));
    }
}