package com.paladin.health.controller.publicity;

import com.paladin.health.model.publicity.PublicityVideo;
import com.paladin.health.service.publicity.PublicityVideoService;
import com.paladin.health.service.publicity.dto.PublicityVideoQueryDTO;
import com.paladin.health.service.publicity.dto.PublicityVideoDTO;
import com.paladin.health.service.publicity.vo.PublicityVideoVO;

import com.paladin.framework.core.ControllerSupport;
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

@Controller
@RequestMapping("/health/publicity/video")
public class PublicityVideoController extends ControllerSupport {

	@Autowired
	private PublicityVideoService publicityVideoService;

	@RequestMapping("/index")
	public String index() {
		return "/health/publicity/publicity_video_index";
	}

	@RequestMapping("/find/page")
	@ResponseBody
	public Object findPage(PublicityVideoQueryDTO query) {
		return CommonResponse.getSuccessResponse(publicityVideoService.searchPage(query));
	}

	@RequestMapping("/get")
	@ResponseBody
	public Object getDetail(@RequestParam String id, Model model) {
		return CommonResponse.getSuccessResponse(beanCopy(publicityVideoService.get(id), new PublicityVideoVO()));
	}

	@RequestMapping("/add")
	public String addInput() {
		return "/health/publicity/publicity_video_add";
	}

	@RequestMapping("/detail")
	public String detailInput(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "/health/publicity/publicity_video_detail";
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(@Valid PublicityVideoDTO publicityVideoDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		PublicityVideo model = beanCopy(publicityVideoDTO, new PublicityVideo());
		String id = UUIDUtil.createUUID();
		model.setId(id);
		if (publicityVideoService.save(model) > 0) {
			return CommonResponse.getSuccessResponse(publicityVideoService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

	@RequestMapping("/update")
	@ResponseBody
	public Object update(@Valid PublicityVideoDTO publicityVideoDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = publicityVideoDTO.getId();
		PublicityVideo model = beanCopy(publicityVideoDTO, publicityVideoService.get(id));
		if (publicityVideoService.update(model) > 0) {
			return CommonResponse.getSuccessResponse(publicityVideoService.get(id));
		}
		return CommonResponse.getFailResponse();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(@RequestParam String id) {
		return CommonResponse.getResponse(publicityVideoService.removeByPrimaryKey(id));
	}
}