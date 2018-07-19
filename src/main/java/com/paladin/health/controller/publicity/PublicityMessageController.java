package com.paladin.health.controller.publicity;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.controller.publicity.pojo.MessageExamineQuery;
import com.paladin.health.controller.publicity.pojo.MessageQuery;
import com.paladin.health.controller.publicity.pojo.PublicityMessageDTO;
import com.paladin.health.model.publicity.PublicityMessage;
import com.paladin.health.model.publicity.PublicityMessageMore;
import com.paladin.health.service.publicity.PublicityMessageService;

@Controller
@RequestMapping("/health/publicity")
public class PublicityMessageController extends ControllerSupport {

	@Autowired
	PublicityMessageService publicityMessageService;

	/**
	 * 发布信息页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	@QueryInputMethod(queryClass = MessageQuery.class)
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
	@QueryOutputMethod(queryClass = MessageQuery.class, paramIndex = 0)
	public Object findAll(MessageQuery query) {
		return CommonResponse.getSuccessResponse(publicityMessageService.findSelfMessage(query));
	}

	/**
	 * 审核消息页面
	 * 
	 * @return
	 */
	@RequestMapping("/examine/index")
	@QueryInputMethod(queryClass = MessageExamineQuery.class)
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
	@QueryOutputMethod(queryClass = MessageExamineQuery.class, paramIndex = 0)
	public Object findExamineAll(MessageExamineQuery query) {
		return CommonResponse.getSuccessResponse(publicityMessageService.findExamineMessage(query));
	}

	/**
	 * 详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/view")
	public String view(@RequestParam String id, Model model) {
		PublicityMessage message = publicityMessageService.get(id);
		if (message == null) {
			message = new PublicityMessage();
		}
		model.addAttribute("object", message);
		return "/health/publicity/message_view";
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
		return "/health/publicity/message_edit";
	}

	/**
	 * 编辑
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(@RequestParam String id, Model model) {
		PublicityMessage message = publicityMessageService.get(id);
		if (message == null) {
			throw new BusinessException("找不到需要编辑的公告消息");
		}
		model.addAttribute("object", message);
		return "/health/publicity/message_edit";
	}

	/**
	 * 保存，status：temp -> 暂存; submit -> 提交;
	 * 
	 * @param publicityMessage
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping("/save/{status}")
	@ResponseBody
	public Object save(@Valid PublicityMessageDTO publicityMessage, @PathVariable("status") String status, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}

		int statu = PublicityMessage.STATUS_TEMP;
		if (status != null && status.length() > 0) {
			if ("temp".equals(status)) {
				statu = PublicityMessage.STATUS_TEMP;
			} else if ("submit".equals(status)) {
				statu = PublicityMessage.STATUS_SUBMIT_EXAMINE;
			} else {
				throw new BusinessException("错误的请求");
			}
		}

		String id = publicityMessage.getId();
		if (id != null && id.length() != 0) {
			PublicityMessage model = publicityMessageService.get(id);
			if (model == null) {
				throw new BusinessException("找不到需要编辑的公告消息");
			}

			Integer currentStatus = model.getStatus();
			if (currentStatus == PublicityMessage.STATUS_SUBMIT_EXAMINE) {
				throw new BusinessException("已经提交待审核的信息无法修改");
			}

			if (currentStatus == PublicityMessage.STATUS_EXAMINE_SUCCESS) {
				throw new BusinessException("已经审核成功的信息无法修改");
			}

			beanCompleteCopy(publicityMessage, model);
			model.setStatus(statu);
			return CommonResponse.getResponse(publicityMessageService.update(model));
		} else {
			PublicityMessage model = new PublicityMessage();
			beanCompleteCopy(publicityMessage, model);
			model.setStatus(statu);
			return CommonResponse.getResponse(publicityMessageService.save(model));
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
		PublicityMessageMore message = publicityMessageService.getJoin(id);
		if (message == null) {
			message = new PublicityMessageMore();
		}
		model.addAttribute("object", message);
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
