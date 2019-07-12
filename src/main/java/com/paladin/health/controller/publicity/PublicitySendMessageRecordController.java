package com.paladin.health.controller.publicity;

import com.paladin.health.controller.publicity.dto.PublicitySendMessageRecordExportCondition;
import com.paladin.health.model.publicity.PublicityMessage;
import com.paladin.health.model.publicity.PublicitySendMessageRecord;
import com.paladin.health.service.publicity.PublicityMessageService;
import com.paladin.health.service.publicity.PublicitySendMessageRecordService;
import com.paladin.health.service.publicity.dto.PublicitySendMessageRecordQuery;
import com.paladin.health.service.publicity.dto.PublicityShortMessageQueryDTO;
import com.paladin.health.service.publicity.vo.PublicityMessageVO;
import com.paladin.common.core.export.ExportUtil;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.excel.write.ExcelWriteException;
import com.paladin.framework.web.response.CommonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/health/publicity/send/message/record")
public class PublicitySendMessageRecordController extends ControllerSupport {

	@Autowired
	private PublicitySendMessageRecordService publicitySendMessageRecordService;
	
	@Autowired
	private PublicityMessageService publicityMessageService;

	@GetMapping("/main/index")
	public String mainIndex() {
		return "/health/publicity/publicity_send_message_record_main";
	}
	
	@RequestMapping(value = "/main/find/page", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object mainFindPage(PublicityShortMessageQueryDTO query) {	
		query.setType(PublicityMessage.TYPE_SHORT_MESSAGE);
		query.setStatus(PublicityMessage.STATUS_EXAMINE_SUCCESS);
		return CommonResponse.getSuccessResponse(publicityMessageService.searchPage(query).convert(PublicityMessageVO.class));
	}
	
	@GetMapping("/index")
	public String index(String messageId, String messageTitle, Model model) {
		model.addAttribute("messageId", messageId);
		model.addAttribute("messageTitle", messageTitle);
		return "/health/publicity/publicity_send_message_record_index";
	}

	@RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object findPage(PublicitySendMessageRecordQuery query) {
		query.setOrder("DESC");
		query.setSort(PublicitySendMessageRecord.COLUMN_FIELD_CREATE_TIME);
		return CommonResponse.getSuccessResponse(publicitySendMessageRecordService.searchPage(query));
	}

	@PostMapping(value = "/export")
	@ResponseBody
	public Object export(@RequestBody PublicitySendMessageRecordExportCondition condition) {
		if (condition == null) {
			return CommonResponse.getFailResponse("导出失败：请求参数异常");
		}
		condition.sortCellIndex();
		PublicitySendMessageRecordQuery query = condition.getQuery();
		try {
			if (query != null) {
				if (condition.isExportAll()) {
					return CommonResponse.getSuccessResponse("success",
							ExportUtil.export(condition, publicitySendMessageRecordService.searchAll(query), PublicitySendMessageRecord.class));
				} else if (condition.isExportPage()) {
					return CommonResponse.getSuccessResponse("success",
							ExportUtil.export(condition, publicitySendMessageRecordService.searchPage(query).getData(), PublicitySendMessageRecord.class));
				}
			}
			return CommonResponse.getFailResponse("导出数据失败：请求参数错误");
		} catch (IOException | ExcelWriteException e) {
			return CommonResponse.getFailResponse("导出数据失败：" + e.getMessage());
		}
	}
}