package com.paladin.health.controller.prescription;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.core.AuthKeyContainer;
import com.paladin.health.model.prescription.PrescriptionInterfaceManage;
import com.paladin.health.service.prescription.PrescriptionInterfaceManageService;
import com.paladin.health.service.prescription.dto.PrescriptionInterfaceManageDTO;
import com.paladin.health.service.prescription.dto.PrescriptionInterfaceManageQueryDTO;
import com.paladin.health.service.prescription.vo.PrescriptionInterfaceManageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/health/prescription/interface/manage")
public class PrescriptionInterfaceManageController extends ControllerSupport {

	@Autowired
	private PrescriptionInterfaceManageService prescriptionInterfaceManageService;

	@Autowired
	private AuthKeyContainer authKeyContainer;

	@GetMapping("/index")
	public String index() {
		return "/health/prescription/prescription_interface_manage_index";
	}

	@RequestMapping(value = "/find/page", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object findPage(PrescriptionInterfaceManageQueryDTO query) {
		return CommonResponse.getSuccessResponse(prescriptionInterfaceManageService.searchPage(query));
	}

	@GetMapping("/get")
	@ResponseBody
	public Object getDetail(@RequestParam String id) {
		return CommonResponse.getSuccessResponse(beanCopy(prescriptionInterfaceManageService.get(id), new PrescriptionInterfaceManageVO()));
	}

	@GetMapping("/add")
	public String addInput(Model model) {
		model.addAttribute("appKey", UUIDUtil.createUUID());
		return "/health/prescription/prescription_interface_manage_add";
	}

	@GetMapping("/detail")
	public String detailInput(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "/health/prescription/prescription_interface_manage_detail";
	}

	@PostMapping("/save")
	@ResponseBody
	public Object save(@Valid PrescriptionInterfaceManageDTO prescriptionInterfaceManageDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		PrescriptionInterfaceManage model = beanCopy(prescriptionInterfaceManageDTO, new PrescriptionInterfaceManage());
		model.setCreateTime(new Date());
		if (prescriptionInterfaceManageService.save(model) > 0) {
			authKeyContainer.updateData();
			return CommonResponse.getSuccessResponse(
					beanCopy(prescriptionInterfaceManageService.get(prescriptionInterfaceManageDTO.getAppKey()), new PrescriptionInterfaceManageVO()));
		}
		return CommonResponse.getFailResponse();
	}

	@PostMapping("/update")
	@ResponseBody
	public Object update(@Valid PrescriptionInterfaceManageDTO prescriptionInterfaceManageDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = prescriptionInterfaceManageDTO.getAppKey();
		PrescriptionInterfaceManage model = beanCopy(prescriptionInterfaceManageDTO, prescriptionInterfaceManageService.get(id));
		model.setCreateTime(new Date());
		if (prescriptionInterfaceManageService.update(model) > 0) {
			authKeyContainer.updateData();
			return CommonResponse.getSuccessResponse(beanCopy(prescriptionInterfaceManageService.get(id), new PrescriptionInterfaceManageVO()));
		}
		return CommonResponse.getFailResponse();
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object delete(@RequestParam String id) {
		int effect = prescriptionInterfaceManageService.removeByPrimaryKey(id);
		if (effect > 0) {
			authKeyContainer.updateData();
		}

		return CommonResponse.getResponse(effect);
	}

	@RequestMapping(value = "/start", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object start(@RequestParam String id) {
		boolean effect = prescriptionInterfaceManageService.start(id);
		if (effect) {
			authKeyContainer.updateData();
		}

		return CommonResponse.getResponse(effect);
	}

	@RequestMapping(value = "/stop", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object stop(@RequestParam String id) {
		boolean effect = prescriptionInterfaceManageService.stop(id);
		if (effect) {
			authKeyContainer.updateData();
		}

		return CommonResponse.getResponse(effect);
	}

}