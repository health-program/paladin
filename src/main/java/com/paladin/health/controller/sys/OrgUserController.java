package com.paladin.health.controller.sys;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.core.query.QueryOutputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.health.controller.sys.pojo.OrgUserDTO;
import com.paladin.health.controller.sys.pojo.OrgUserQuery;
import com.paladin.health.core.HealthUserSession;
import com.paladin.health.model.sys.OrgUser;
import com.paladin.health.service.sys.OrgUserService;
import com.paladin.health.service.sys.SysUserService;


@Controller
@RequestMapping("/health/system/user")
public class OrgUserController extends ControllerSupport{
	
	@Autowired
	private OrgUserService userService;
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	@QueryInputMethod(queryClass = OrgUserQuery.class)
	public String index() {
		return "/health/sys/user_index";
	}

	/**
	 * 查询
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping("/find")
	@ResponseBody
	@QueryOutputMethod(queryClass = OrgUserQuery.class, paramIndex = 0)
	public Object findAll(OrgUserQuery query) {
		return CommonResponse.getSuccessResponse(userService.searchPage(query));
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
		OrgUser user = userService.get(id);
		if (user == null) {
			throw new BusinessException("查看的用户不存在");
		}
		model.addAttribute("object", user);
		return "/health/sys/user_view";
	}

	/**
	 * 新增
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("object", new OrgUser());
		model.addAttribute("isAdd", true);
		model.addAttribute("isSystemAdmin", HealthUserSession.getCurrentUserSession().isSystemAdmin());
		return "/health/sys/user_edit";
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
		OrgUser user = userService.get(id);
		if (user == null) {
			throw new BusinessException("编辑的用户不存在");
		}
		model.addAttribute("isSystemAdmin", HealthUserSession.getCurrentUserSession().isSystemAdmin());
		model.addAttribute("object", user);
		return "/health/sys/user_edit";
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
	public Object addSave(@Valid OrgUserDTO userDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validErrorHandler(bindingResult);
		}
		String id = userDTO.getId();
		if(id != null && id.length() != 0) {			
			OrgUser user = userService.get(id);
			
			if (user == null) {
				throw new BusinessException("编辑的用户不存在");
			}
			
			beanIncompleteCopy(userDTO, user);
			return CommonResponse.getResponse(userService.updateUser(user));
		} else {
			OrgUser user = beanCompleteCopy(userDTO, new OrgUser());
			return CommonResponse.getResponse(userService.addUser(user));
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
		return CommonResponse.getResponse(userService.removeUser(id));
	}

	
	/**
	 * 验证账号是否可行
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping("/validate/account")
	@ResponseBody
	public Object validateAccount(@RequestParam(required = true) String account) {
		return CommonResponse.getSuccessResponse(sysUserService.validateAccount(account));
	}

	
}
