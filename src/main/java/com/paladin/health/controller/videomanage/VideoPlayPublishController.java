package com.paladin.health.controller.videomanage;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.model.videomanage.VideoPlayPublish;
import com.paladin.health.service.videomanage.VideoPlayPublishService;
import com.paladin.health.service.videomanage.dto.VideoPlayPublishDTO;
import com.paladin.health.service.videomanage.dto.VideoPlayPublishQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/health/video/play/publish")
public class VideoPlayPublishController extends ControllerSupport {

    @Autowired
    private VideoPlayPublishService videoPlayPublishService;
    
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        return "/health/videomanage/video_play_publish_index";
    }	
    
    @RequestMapping("/find/page")
    @ResponseBody
    public Object findPage(VideoPlayPublishQueryDTO query) {
        return CommonResponse.getSuccessResponse(videoPlayPublishService.searchPublishedVideo(query));
    }
    
    /**
     * 外部统计播放量展示柱状图
     */
    @RequestMapping("/getStatistics")
    @ResponseBody
    public Object getStatistics(VideoPlayPublishDTO videoPlayPublishDTO, Model model) {   	
        return CommonResponse.getSuccessResponse(videoPlayPublishService.getStatistics(videoPlayPublishDTO));
    }

    @RequestMapping("/detail")
    public String detailInput(@RequestParam String id,@RequestParam String name, Model model) {
    	model.addAttribute("id", id);
    	model.addAttribute("name", name);
        return "/health/videomanage/video_play_publish_detail";
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Object save(@Valid VideoPlayPublishDTO videoPlayPublishDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
        VideoPlayPublish model = beanCopy(videoPlayPublishDTO, new VideoPlayPublish());
		String id = UUIDUtil.createUUID();
		model.setVideoId(id);
		if (videoPlayPublishService.save(model) > 0) {
			return CommonResponse.getSuccessResponse(videoPlayPublishService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/updateCount")
	@ResponseBody
    public Object update(@RequestParam String videoId) {
		if (videoPlayPublishService.updateOrSave(videoId) > 0) {
			return CommonResponse.getSuccessResponse("");
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(videoPlayPublishService.removeByPrimaryKey(id));
    }
}