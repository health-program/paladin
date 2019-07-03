package com.paladin.health.controller.home;

import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.health.model.publicity.PublicityMessage;
import com.paladin.health.service.publicity.PublicityMessageService;
import com.paladin.health.service.publicity.vo.PublicityMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <首页>
 * @author Huangguochen
 * @create 2019/7/3 10:41
 */
@Controller
@RequestMapping("/health/home/page")
public class HomePageController extends ControllerSupport {

    @Autowired
    private PublicityMessageService publicityMessageService;

    @GetMapping("/index")
    public String index(Model model) {
        List<PublicityMessageVO> notices = publicityMessageService.searchHomePagNotices();
        model.addAttribute("notices",notices);
        return "/health/index_content";
    }

    @GetMapping("/notice/view")
    public String view(@RequestParam String id, @RequestParam(required = false) boolean isHomePage, Model model) {
        PublicityMessage message = publicityMessageService.get(id);
        if (message == null) {
            throw new BusinessException("查看的内容不存在");
        }
        PublicityMessageVO publicityMessageVO = beanCopy(message, new PublicityMessageVO());
        model.addAttribute("object",publicityMessageVO);
        model.addAttribute("isHomePage",isHomePage);
        return "/health/publicity/message_home_detail";
    }

}
