package com.paladin.health.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2019/1/23 16:25
 */
@Controller
@RequestMapping("/health/")
public class OutDisPlayController {

    @RequestMapping("/gxb")
    public String gxb() { return "/health/gxb"; }

    @RequestMapping("/gxy")
    public String gxy() { return "/health/gxy"; }
}
