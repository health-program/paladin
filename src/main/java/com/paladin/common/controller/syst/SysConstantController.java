package com.paladin.common.controller.syst;

import com.paladin.common.controller.syst.pojo.SysConstantQuery;
import com.paladin.common.model.syst.SysConstant;
import com.paladin.common.service.syst.SysConstantService;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.web.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/common/sys/constant")
public class SysConstantController extends ControllerSupport {

    @Autowired
    private SysConstantService sysConstantService;

    @RequestMapping("/index")
    public String index() {
        return "common/sys/sys_constant_index";
    }

    @RequestMapping("/find")
    @ResponseBody
    public Object find(SysConstantQuery query) {
        return CommonResponse.getSuccessResponse(sysConstantService.searchAll(query));
    }

    @RequestMapping("/find/page")
    @ResponseBody
    public Object findPage(SysConstantQuery query) {
        return CommonResponse.getSuccessResponse(sysConstantService.searchPage(query));
    }

    @RequestMapping("/view")
    public String view(@RequestParam String id, Model model) {
        SysConstant sysConstant = sysConstantService.get(id);
        if (sysConstant == null)
            sysConstant = new SysConstant();
        model.addAttribute("sysConstant", sysConstant);
        return "common/sys/sys_constant_view";
    }

    @RequestMapping("/add/input")
    public String addInput(Model model) {
        model.addAttribute("sysConstant", new SysConstant());
        return "common/sys/sys_constant_add";
    }

    @RequestMapping("/edit/input")
    public String editInput(@RequestParam String id, Model model) {
        SysConstant sysConstant = sysConstantService.get(id);
        if (null == sysConstant) {
            sysConstant = new SysConstant();
        }
        model.addAttribute("sysConstant", sysConstant);
        return "common/sys/sys_constant_edit";
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(@Valid SysConstant sysConstant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return validErrorHandler(bindingResult);
        return CommonResponse.getResponse(sysConstantService.saveOrUpdate(sysConstant));
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        return CommonResponse.getResponse(sysConstantService.removeByPrimaryKey(id));
    }
}