package com.paladin.health.controller.open;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.service.publicity.PublicityMessageService;
import com.paladin.health.service.publicity.dto.PublicityMessageQueryDTO;
import com.paladin.health.service.publicity.vo.PublicityMessageVO;
import com.paladin.health.service.videomanage.VideoPlayPublishService;
import com.paladin.health.service.videomanage.VideoService;
import com.paladin.health.service.videomanage.dto.VideoPlayPublishQueryDTO;
import com.paladin.health.service.videomanage.dto.VideoQueryDTO;
import com.paladin.health.service.videomanage.vo.VideoShowVo;
import com.paladin.health.service.videomanage.vo.VideoVO;

@Controller
@RequestMapping("/open/health/message/center")
public class PublicityMessageCenterController extends ControllerSupport {

	@Autowired
	private VideoService videoService;

	@Autowired
	private PublicityMessageService publicityMessageService;

	@Autowired
	private VideoPlayPublishService videoPlayPublishService;

	/**
	 * 对外首页展示
	 * 
	 * @return
	 */
	@RequestMapping("/topVideo")
	public String topVideo(Model model) {
		OffsetPage page = new OffsetPage();
		page.setLimit(5);
		page.setOffset(0);
		PageResult<VideoShowVo> pages = videoService.findVideoPage(page);
		VideoQueryDTO queryDTO = new VideoQueryDTO();
		queryDTO.setLimit(9);
		queryDTO.setOffset(0);
		PageResult<VideoVO> videosAll = videoService.searchPageList(queryDTO);
		List<PublicityMessageVO> messages = publicityMessageService.showDisplayMessage();
		model.addAttribute("videos", pages.getData());
		model.addAttribute("videosAll", videosAll.getData());
		model.addAttribute("messages", messages);
		return "/health/videomanage/video_top_video_index";
	}

	/**
	 * 对外视频首页列表展示
	 * 
	 * @return
	 */
	
	@RequestMapping("/app/videoList")
	@ResponseBody
	public Object appVideo(VideoQueryDTO queryDTO) {
		queryDTO.setLimit(8);
		PageResult<VideoVO> videosAll = videoService.searchPageList(queryDTO);
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("videosAll", videosAll);
		return CommonResponse.getSuccessResponse(map);
	}
	
	
	/**
	 * 对外移动端视频图片轮播展示
	 * 
	 * @return
	 */
	
	@RequestMapping("/app/videoSlip")
	@ResponseBody
	public Object videoSlip() {
		OffsetPage page = new OffsetPage();
		page.setLimit(5);
		page.setOffset(0);
		PageResult<VideoShowVo> pages = videoService.findVideoPage(page);
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("videos", pages.getData());
		return CommonResponse.getSuccessResponse(map);
	}
	
	
	/**
	 * 对外信息首页展示
	 * 
	 * @return
	 */
	
	@RequestMapping("/app/messages")
	@ResponseBody
	public Object appMessage() {
		PublicityMessageQueryDTO queryDTO = new PublicityMessageQueryDTO();
		queryDTO.setLimit(5);
		queryDTO.setOffset(0);
		PageResult<PublicityMessageVO> slipMessages = publicityMessageService.findPublishedMessages(queryDTO);
		queryDTO.setLimit(8);
		queryDTO.setOffset(0);
		PageResult<PublicityMessageVO> messages = publicityMessageService.findPublishedMessages(queryDTO);
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("slipMessages", slipMessages.getData());
		map.put("messages", messages.getData());
		return CommonResponse.getSuccessResponse(map);
	}
	
	
	/**
	 * 对外信息首页图片轮播展示
	 * 
	 * @return
	 */
	
	@RequestMapping("/app/messageSlip")
	@ResponseBody
	public Object messageSlip() {
		PublicityMessageQueryDTO queryDTO = new PublicityMessageQueryDTO();
		queryDTO.setLimit(5);
		queryDTO.setOffset(0);
		PageResult<PublicityMessageVO> slipMessages = publicityMessageService.findPublishedMessages(queryDTO);
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("slipMessages", slipMessages.getData());
		return CommonResponse.getSuccessResponse(map);
	}
	
	/**
	 * 对外信息首页展示
	 * 
	 * @return
	 */
	
	@RequestMapping("/app/messageList")
	@ResponseBody
	public Object messageList(PublicityMessageQueryDTO queryDTO) {
		queryDTO.setLimit(8);
		PageResult<PublicityMessageVO> messages = publicityMessageService.findPublishedMessages(queryDTO);
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("messages", messages);
		return CommonResponse.getSuccessResponse(map);
	}
	
	/**
	 * 功能描述: <信息详情展示页面>
	 * 
	 * @param id
	 * @param model
	 * @return java.lang.Object
	 * @author jiangheng
	 * @date 2018/2/14
	 */
	@RequestMapping("/display/index")
	public Object display(@RequestParam String id, Model model) {
		PublicityMessageQueryDTO queryDTO = new PublicityMessageQueryDTO();
		PublicityMessageVO message = publicityMessageService.getMessage(id);
		List<PublicityMessageVO> messageList = publicityMessageService.showDisplayMessage();
		queryDTO.setLimit(100);
		PageResult<PublicityMessageVO> messagesResult = publicityMessageService.findPublishedMessages(queryDTO);
		List<PublicityMessageVO>messages = messagesResult.getData();
		PublicityMessageVO preMessage = new PublicityMessageVO();
		PublicityMessageVO nextMessage = new PublicityMessageVO();
		for (int i = 0; i < messages.size(); i++) {
			if (messages.get(i).getId().equals(message.getId())) {
				if (messages.size() > 1) {
					if(i > 0 && i <  messages.size() - 1){
						preMessage = messages.get(i - 1);
						nextMessage = messages.get(i + 1);
					}
					if (i == 0) {
						nextMessage = messages.get(i + 1);
						
					} 
					if (i == messages.size() - 1) {
						preMessage = messages.get(i - 1);
					}
				}
			}
		};
		model.addAttribute("message", message);
		model.addAttribute("preMessage", preMessage);
		model.addAttribute("nextMessage", nextMessage);
		model.addAttribute("messageList", messageList);
		return "/health/publicity/message_display_index";
	}

	/**
	 * 功能描述: app对外信息详情
	 * <信息详情展示页面>
	 * 
	 * @param id
	 * @param model
	 * @return java.lang.Object
	 * @author jiangheng
	 * @date 2018/2/14
	 */
	@RequestMapping("/app/message/detail")
	@ResponseBody
	public Object messageDitail(@RequestParam String id, Model model) {
		PublicityMessageVO message = publicityMessageService.getMessage(id);
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("message",message);
		return CommonResponse.getSuccessResponse(map);
	}
	
	
	/**
	 * 功能描述:
	 * 〈视频播放页面〉
	 * 
	 * @param id
	 * @param model
	 * @return java.lang.String
	 * @author jiangheng
	 * @date 2018/2/14
	 */
	@RequestMapping("/play")
	public String play(@RequestParam String id, Model model) {
		VideoVO videoVO = beanCopy(videoService.get(id), new VideoVO());
		VideoQueryDTO queryDTO = new VideoQueryDTO();
		queryDTO.setLimit(5);
		queryDTO.setOffset(0);
		PageResult<VideoVO> videosAll = videoService.searchPageList(queryDTO);
		model.addAttribute("video", videoVO);
		model.addAttribute("videosAll", videosAll.getData());
		return "/health/videomanage/video_play";
	}
	
	/**
	 * 功能描述:
	 * 〈视频播放页面〉
	 * 
	 * @param id
	 * @param model
	 * @return java.lang.String
	 * @author jiangheng
	 * @date 2018/2/14
	 */
	@RequestMapping("/app/play")
	@ResponseBody
	public Object appPlay(@RequestParam String id, Model model) {
		VideoVO videoVO = beanCopy(videoService.get(id), new VideoVO());
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("video", videoVO);
		return CommonResponse.getSuccessResponse(map);
	}

	/**
	 * 信息列表
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping("/find")
	@ResponseBody
	public Object findAll(PublicityMessageQueryDTO query) {
		return CommonResponse.getSuccessResponse(publicityMessageService.findPublishedMessages(query));
	}
	
	/**
	 * 信息展示页面
	 * 
	 * @return
	 */
	@RequestMapping("/message/index")
	public String messageIndex() {
		return "/health/publicity/message_center";
	}
	
	/**
	 * 视频列表
	 * 
	 * @return
	 */
	@RequestMapping("/find/page")
    @ResponseBody
    public Object findPage(VideoPlayPublishQueryDTO query) {
        return CommonResponse.getSuccessResponse(videoPlayPublishService.searchPublishedVideo(query));
    }

	/**
	 * 视频展示页面
	 * 
	 * @return
	 */
	@RequestMapping("/video/index")
	public String videoIndex() {
		return "/health/videomanage/video_center";
	}
	

    @RequestMapping("/updateCount")
	@ResponseBody
    public Object update(@RequestParam String videoId) {
		if (videoPlayPublishService.updateOrSave(videoId) > 0) {
			return CommonResponse.getSuccessResponse("");
		}
		return CommonResponse.getFailResponse();
	}
    
}
