package com.paladin.health.controller.publicity;

import java.util.List;
import javax.validation.Valid;
import com.paladin.health.service.publicity.vo.PublicityMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.paladin.common.model.syst.SysAttachment;
import com.paladin.common.service.syst.SysAttachmentService;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.model.publicity.PublicityMessage;
import com.paladin.health.service.publicity.PublicityMessageService;
import com.paladin.health.service.publicity.dto.PublicityMessageDTO;
import com.paladin.health.service.publicity.dto.PublicityMessageQueryDTO;

@Controller
@RequestMapping("/health/publicity/message")
public class PublicityMessageController extends ControllerSupport {
	
	@Autowired
	private SysAttachmentService attachmentService;
	
	@Autowired
	private PublicityMessageService publicityMessageService;

	/**
	 * 发布信息页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "/health/publicity/message_index";
	}

	/**
	 * 查询自己能看到信息
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping("/find")
	@ResponseBody
	public Object findAll(PublicityMessageQueryDTO query) {
		return CommonResponse.getSuccessResponse(publicityMessageService.findSelfMessage(query));
	}

	/**
	 * 审核消息页面
	 * 
	 * @return
	 */
	@RequestMapping("/examine/index")
	@QueryInputMethod(queryClass = PublicityMessageQueryDTO.class)
	public String examineIndex() {
		return "/health/publicity/message_examine_index";
	}

	/**
	 * 查询审核信息
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping("/examine/find")
	@ResponseBody
	@QueryOutputMethod(queryClass = PublicityMessageQueryDTO.class, paramIndex = 0)
	public Object findExamineAll(PublicityMessageQueryDTO query) {
		return CommonResponse.getSuccessResponse(publicityMessageService.findExamineMessage(query));
	}

	/**
	 * 详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/get")
	@ResponseBody
	public Object view(@RequestParam String id) {
		return CommonResponse.getSuccessResponse(publicityMessageService.getMessage(id));
	}

    /**
     * 功能描述: <br>
     * 〈发布信息前台展示页面〉
     * @param id
     * @param model
     * @return  java.lang.Object
     * @author  Huangguochen
     * @date  2018/12/28
     */
    @RequestMapping("/display/index")
    public Object display(@RequestParam String id,Model model) {
        PublicityMessageVO message = publicityMessageService.getMessage(id);
        model.addAttribute("message",message);
        return "/health/publicity/message_display_index";
    }

	/**
	 * 新增
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("object", new PublicityMessage());
		return "/health/publicity/message_add";
	}

	/**
	 * 编辑
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "/health/publicity/message_detail";
	}

	/**
	 * 保存
	 * 
	 * @param publicityMessage
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Object save(@Valid PublicityMessageDTO publicityMessage, BindingResult bindingResult,
			@RequestParam(required = false) MultipartFile[] attachmentFiles,
			@RequestParam(required = false) MultipartFile[] thumbnailImage) {
		
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		
		List<SysAttachment> thumbnail = attachmentService.checkOrCreateAttachment(publicityMessage.getAttachments(), thumbnailImage,true);
		if (thumbnail != null && thumbnail.size() > 1) {
			return CommonResponse.getErrorResponse("缩略图只能有一张");
		}
		
    	List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(publicityMessage.getAttachments(), attachmentFiles,false);
		if (attachments != null && attachments.size() > 4) {
			return CommonResponse.getErrorResponse("附件数量不能超过4张");
		}
		publicityMessage.setThumbnail(attachmentService.splicingAttachmentId(thumbnail));
		publicityMessage.setAttachments(attachmentService.splicingAttachmentId(attachments));
		return CommonResponse.getResponse(publicityMessageService.saveMessage(publicityMessage));
	}

	/**
	 * 保存
	 * 
	 * @param publicityMessage
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Object update(@Valid PublicityMessageDTO publicityMessage, BindingResult bindingResult,
			@RequestParam(required = false) MultipartFile[] attachmentFiles,
			@RequestParam(required = false) MultipartFile[] thumbnailImage) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		List<SysAttachment> thumbnail = attachmentService.checkOrCreateAttachment(publicityMessage.getAttachments(), thumbnailImage,true);
		if (thumbnail != null && thumbnail.size() > 1) {
			return CommonResponse.getErrorResponse("缩略图只能有一张");
		}
		
    	List<SysAttachment> attachments = attachmentService.checkOrCreateAttachment(publicityMessage.getAttachments(), attachmentFiles,false);
		if (attachments != null && attachments.size() > 4) {
			return CommonResponse.getErrorResponse("附件数量不能超过4张");
		}
		publicityMessage.setThumbnail(attachmentService.splicingAttachmentId(thumbnail));
		publicityMessage.setAttachments(attachmentService.splicingAttachmentId(attachments));
		if(publicityMessageService.updateMessage(publicityMessage)) {
			return CommonResponse.getSuccessResponse(publicityMessageService.getMessage(publicityMessage.getId()));
		} else {
			return CommonResponse.getFailResponse();
		}	
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove")
	@ResponseBody
	public Object remove(@RequestParam String id) {
		return CommonResponse.getResponse(publicityMessageService.removeMessage(id));
	}

	/**
	 * 审核
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/examine")
	public String examine(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "/health/publicity/message_examine";
	}

	/**
	 * 审核成功
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/examine/success")
	@ResponseBody
	public Object examineSuccess(@RequestParam String id) {
		return CommonResponse.getResponse(publicityMessageService.examine(id, true));
	}

	/**
	 * 审核驳回
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/examine/fail")
	@ResponseBody
	public Object examineFail(@RequestParam String id) {
		return CommonResponse.getResponse(publicityMessageService.examine(id, false));
	}
}
