package com.paladin.health.controller.publicity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.health.service.publicity.PublicityMessageService;
import com.paladin.health.service.publicity.dto.PublicityMessageQueryDTO;
import com.paladin.health.service.videomanage.VideoPlayPublishService;
import com.paladin.health.service.videomanage.dto.VideoPlayPublishQueryDTO;

@Controller
@RequestMapping("/health/publicity/public/center")
public class PublicityMessageCenterController extends ControllerSupport {	
	@Autowired
	private PublicityMessageService publicityMessageService;
	
	@Autowired
	private VideoPlayPublishService videoPlayPublishService;
	 
	/**
	 * 信息展示页面
	 * 
	 * @return
	 */
	@RequestMapping("/message/index")
	public ModelAndView messageIndex(PublicityMessageQueryDTO query) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("publicityMessage", publicityMessageService.findSelfMessage(query));
		mav.setViewName( "/health/publicity/message_center");
		return mav;
	}
	
	/**
	 * 视频展示页面
	 * 
	 * @return
	 */
	@RequestMapping("/video/index")
	public ModelAndView videoIndex(VideoPlayPublishQueryDTO query) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("publicityVideo", videoPlayPublishService.searchPublishedVideo(query));
		mav.setViewName( "/health/videomanage/video_center");
		return mav;
	} 
}
