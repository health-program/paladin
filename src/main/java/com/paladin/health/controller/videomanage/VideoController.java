package com.paladin.health.controller.videomanage;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.common.service.syst.SysAttachmentService;
import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.model.videomanage.Video;
import com.paladin.health.service.publicity.PublicityMessageService;
import com.paladin.health.service.publicity.vo.PublicityMessageVO;
import com.paladin.health.service.videomanage.VideoService;
import com.paladin.health.service.videomanage.dto.VideoDTO;
import com.paladin.health.service.videomanage.dto.VideoQueryDTO;
import com.paladin.health.service.videomanage.vo.VideoShowVo;
import com.paladin.health.service.videomanage.vo.VideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.util.List;

/**
 * 视频管理
 * 
 * @author jisanjie
 * @version [版本号, 2018年12月26日]
 */

@Controller
@RequestMapping("/health/video")
public class VideoController extends ControllerSupport {

	@Autowired
	private VideoService videoService;

	@Autowired
	private SysAttachmentService attachmentService;

	@Autowired
	private PublicityMessageService publicityMessageService;

	@RequestMapping("/index")
	@QueryInputMethod(queryClass = VideoQueryDTO.class)
	public String index() {
		return "/health/videomanage/video_index";
	}

	/**
	 * 视屏播放统计首页
	 * 
	 */
	@RequestMapping("/play/list")
	@QueryInputMethod(queryClass = VideoQueryDTO.class)
	public String palyIndex1() {
		return "/health/videomanage/video_play_list";
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
	public Object save(@Valid VideoDTO videoDTO, BindingResult bindingResult, @RequestParam(required = false) MultipartFile pictureFile) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}

		if (pictureFile != null) {
			SysAttachment picture = attachmentService.createPictureAndCompress(pictureFile, "视频展示图", SysAttachment.USE_TYPE_COLUMN_RELATION, 200, 200);
			videoDTO.setShowImage(picture.getId());
		}

		Video model = beanCopy(videoDTO, new Video());
		model.setTop(0);
		model.setTopOrderNo(Video.TOP_NUMBER);
		String id = UUIDUtil.createUUID();
		model.setId(id);
		if (videoService.save(model) > 0) {
			return CommonResponse.getSuccessResponse(videoService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

	@RequestMapping("/update")
	@ResponseBody
	public Object update(@Valid VideoDTO videoDTO, BindingResult bindingResult, @RequestParam(required = false) MultipartFile pictureFile) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}

		if (pictureFile != null) {
			SysAttachment picture = attachmentService.createPictureAndCompress(pictureFile, "视频展示图", SysAttachment.USE_TYPE_COLUMN_RELATION, 200, 200);
			videoDTO.setShowImage(picture.getId());
		}

		String id = videoDTO.getId();
		Video oldVideo = videoService.get(id);
		if ( videoDTO.getTop() == 0 && oldVideo.getTop() == 1){
			oldVideo.setTopOrderNo(Video.TOP_NUMBER);
		}else if (videoDTO.getTop() == 1 && oldVideo.getTop() == 0){
			oldVideo.setTopOrderNo(Video.TOP_NOT_SORT_NUMBER);
		}
		Video model = beanCopy(videoDTO, oldVideo);
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
	public Object findAll() {
		return CommonResponse.getSuccessResponse(videoService.findLabelList());
	}

	/**
	 * 功能描述: <br>
	 * 〈轮播页面展示〉
	 * 
	 * @param model
	 * @return java.lang.String
	 * @author Huangguochen
	 * @date 2018/12/28
	 */
	@RequestMapping("/top")
	public String top(Model model) {
		OffsetPage page = new OffsetPage();
		page.setLimit(5);
		page.setOffset(0);
		PageResult<VideoShowVo> pages = videoService.findVideoPage(page);
		VideoQueryDTO queryDTO = new VideoQueryDTO();
		queryDTO.setLimit(8);
		queryDTO.setOffset(0);
		PageResult<VideoVO> videosAll = videoService.searchPageList(queryDTO);
		List<PublicityMessageVO> messages = publicityMessageService.showDisplayMessage();
		model.addAttribute("videos", pages.getData());
		model.addAttribute("videosAll", videosAll.getData());
		model.addAttribute("messages", messages);
		return "/health/videomanage/video_top_index";
	}

	@RequestMapping("/topVideo")
	public String topVideo(Model model) {
		OffsetPage page = new OffsetPage();
		page.setLimit(5);
		page.setOffset(0);
		PageResult<VideoShowVo> pages = videoService.findVideoPage(page);
		VideoQueryDTO queryDTO = new VideoQueryDTO();
		queryDTO.setLimit(8);
		queryDTO.setOffset(0);
		PageResult<VideoVO> videosAll = videoService.searchPageList(queryDTO);
		List<PublicityMessageVO> messages = publicityMessageService.showDisplayMessage();
		model.addAttribute("videos", pages.getData());
		model.addAttribute("videosAll", videosAll.getData());
		model.addAttribute("messages", messages);
		return "/health/open/video_top_video_index";
	}

	/**
	 * 功能描述: <br>
	 * 〈视频播放页面〉
	 * 
	 * @param id
	 * @param model
	 * @return java.lang.String
	 * @author Huangguochen
	 * @date 2018/12/28
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
		return "/health/open/video_play";
	}

	/**
	 * 功能描述: <br>
	 * 〈更换排序顺序〉
	 * 
	 * @param ids
	 * @return java.lang.Object
	 * @author Huangguochen
	 * @date 2018/12/27
	 */
	@RequestMapping("/sort")
	@ResponseBody
	public Object sort(@RequestParam("ids[]") String[] ids) {
		return CommonResponse.getResponse(videoService.updateByOrderNo(ids));
	}

	/**
	 * 视频审核首页
	 * 
	 * @author jisanjie
	 */
	@RequestMapping("/examine/index")
	public String videoExamineIndex() {
		return "/health/videomanage/video_examine_index";
	}

	/**
	 * 加载首页待审核的数据
	 * 
	 * @author jisanjie
	 */
	@RequestMapping("/find/to/examine")
	@ResponseBody
	public Object findToExamine(VideoExamineQueryVo vo) {
		return CommonResponse.getSuccessResponse(videoService.findToExamine(vo));

	}

	/**
	 * 审核
	 * 
	 * @author jisanjie
	 */
	@RequestMapping("/examine")
	public String examine(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "/health/videomanage/video_examine";
	}

	/**
	 * 审核成功
	 * 
	 * @author jisanjie
	 */
	@RequestMapping("/examine/success")
	@ResponseBody
	public Object examineSuccess(@RequestParam String id) {
		return CommonResponse.getResponse(videoService.examine(id, true));
	}

	/**
	 * 审核失败
	 * 
	 * @author jisanjie
	 */
	@RequestMapping("/examine/fail")
	@ResponseBody
	public Object examineFail(@RequestParam String id) {
		return CommonResponse.getResponse(videoService.examine(id, false));
	}

}