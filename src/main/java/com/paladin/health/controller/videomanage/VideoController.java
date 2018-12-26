package com.paladin.health.controller.videomanage;

import com.paladin.health.model.videomanage.Video;
import com.paladin.health.service.videomanage.VideoService;
import com.paladin.health.service.videomanage.dto.VideoQueryDTO;
import com.paladin.health.service.videomanage.dto.VideoDTO;
import com.paladin.health.service.videomanage.vo.VideoVO;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.framework.utils.uuid.UUIDUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 视频管理
 * 
 * @author  jisanjie
 * @version  [版本号, 2018年12月26日]
 */


@Controller
@RequestMapping("/health/video")
public class VideoController extends ControllerSupport {

    @Autowired
    private VideoService videoService;

    @RequestMapping("/index")
    @QueryInputMethod(queryClass = VideoQueryDTO.class)
    public String index() {
        return "/health/videomanage/video_index";
    }
    
    /**
     * 视屏播放统计首页
     * 
     */
    @RequestMapping("/play/index")
    @QueryInputMethod(queryClass = VideoQueryDTO.class)
    public String palyIndex() {
        return "/health/videomanage/video_play_index";
    }

    @RequestMapping("/find/page")
    @ResponseBody
    @QueryOutputMethod(queryClass = VideoQueryDTO.class, paramIndex = 0)
    public Object findPage(VideoQueryDTO query) {
        return CommonResponse.getSuccessResponse(videoService.searchPageList(query));
    }
    
    @RequestMapping("/get")
    @ResponseBody
    public Object getDetail(@RequestParam String id, Model model) {   	
        return CommonResponse.getSuccessResponse(beanCopy(videoService.get(id), new VideoVO()));
    }
    
    @RequestMapping("/add")
    public String addInput() {
        return "/health/videomanage/video_add";
    }

    @RequestMapping("/detail")
    public String detailInput(@RequestParam String id, Model model) {
    	model.addAttribute("id", id);
        return "/health/videomanage/video_detail";
    }
    
    @RequestMapping("/save")
	@ResponseBody
    public Object save(@Valid VideoDTO videoDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
        Video model = beanCopy(videoDTO, new Video());
		String id = UUIDUtil.createUUID();
		model.setId(id);
		if (videoService.save(model) > 0) {
			return CommonResponse.getSuccessResponse(videoService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/update")
	@ResponseBody
    public Object update(@Valid VideoDTO videoDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = videoDTO.getId();
		Video model = beanCopy(videoDTO, videoService.get(id));
		if (videoService.update(model) > 0) {
			return CommonResponse.getSuccessResponse(videoService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(videoService.removeByPrimaryKey(id));
    }
    
    @RequestMapping("/find/all")
    @ResponseBody
    public Object findAll(){
      return CommonResponse.getSuccessResponse(videoService.findLabelList());
    }
    
}