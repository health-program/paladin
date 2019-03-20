package com.paladin.health.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paladin.common.core.permission.MenuPermission;
import com.paladin.common.model.org.OrgPermission;
import com.paladin.health.core.HealthUserSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.paladin.common.service.syst.SysUserService;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.session.UserSession;
import com.paladin.framework.web.response.CommonResponse;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Collection;

@Controller
@RequestMapping("/health/")
public class LoginController extends ControllerSupport {

	@Autowired
	private SysUserService sysUserService;

	@ApiOperation(value = "主页面")
	@GetMapping(value = "/index")
	public Object main(HttpServletRequest request) {
		HealthUserSession userSession = HealthUserSession.getCurrentUserSession();
		ModelAndView model = new ModelAndView("/health/index");
		model.addObject("user", userSession.getUserForView());
		Collection<MenuPermission> menus = userSession.getMenuResources();
		StringBuilder sb = new StringBuilder("<li class=\"header\">菜单</li>");
		createMenuHtml(menus, sb);
		model.addObject("menuHtml", sb.toString());
		return model;
	}

	private void createMenuHtml(Collection<MenuPermission> menus, StringBuilder sb) {
		for (MenuPermission menu : menus) {
			OrgPermission op = menu.getSource();
			Collection<MenuPermission> children = menu.getChildren();

			String href = menu.isMenu() && menu.isOwned() ? op.getUrl() : null;

			String icon = op.getMenuIcon();
			if (icon != null && icon.length() > 0) {
				icon = "fa iconfont icon-" + icon;
			} else {
				icon = "fa fa-circle-o";
			}

			if (children.size() > 0) {
				sb.append("<li class=\"treeview\"><a class=\"nav-link\"");
				if (href != null) {
					sb.append(" onclick=\"addTabs({id:'").append(op.getId()).append("',title: '").append(op.getName()).append("',close: true,url: '")
							.append(href).append("',urlType: 'relative'});\"");
				}

				sb.append("><i class=\"").append(icon).append("\"></i><span>").append(op.getName()).append(
						"</span><span class=\"pull-right-container\"><i class=\"fa fa-angle-left pull-right\"></i></span></a><ul class=\"treeview-menu\">");
				createMenuHtml(children, sb);
				sb.append("</ul></li>");
			} else {
				sb.append("<li><a class=\"nav-link\"");
				if (href != null) {
					sb.append(" onclick=\"addTabs({id:'").append(op.getId()).append("',title: '").append(op.getName()).append("',close: true,url: '")
							.append(href).append("',urlType: 'relative'});\"");
				}
				sb.append("><i class=\"").append(icon).append("\"></i> <span>").append(op.getName()).append("</span></a></li>");
			}
		}
	}

	@ApiOperation(value = "登录页面")
	@GetMapping("/login")
	public Object loginInput(HttpServletRequest request, HttpServletResponse response) {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			return main(request);
		}
		return "/health/login";
	}

	@ApiOperation(value = "用户认证")
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
	public Object login(HttpServletRequest request, HttpServletResponse response, Model model) {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (username != null && username.length() != 0 && password != null && password.length() != 0) {
				subject.login(new UsernamePasswordToken(username, password));
			}
		}

		if (subject.isAuthenticated()) {
			return main(request);
		} else {
			model.addAttribute("isError", true);
			return "/health/login";
		}
	}

	@ApiOperation(value = "用户认证")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxLogin(HttpServletRequest request, HttpServletResponse response, Model model) {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (username != null && username.length() != 0 && password != null && password.length() != 0) {
				subject.login(new UsernamePasswordToken(username, password));
			}
		}

		if (subject.isAuthenticated()) {
			return CommonResponse.getSuccessResponse("登录成功", UserSession.getCurrentUserSession().getUserForView());
		} else {
			return CommonResponse.getFailResponse("登录失败");
		}
	}

	@ApiOperation(value = "修改密码", response = CommonResponse.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "newPassword", value = "新密码", required = true), @ApiImplicitParam(name = "oldPassword", value = "旧密码") })
	@RequestMapping(value = "/update/password", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Object updatePassword(@RequestParam String newPassword, @RequestParam String oldPassword) {
		return CommonResponse.getResponse(sysUserService.updateSelfPassword(newPassword, oldPassword));
	}

	
}
