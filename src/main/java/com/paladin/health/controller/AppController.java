package com.paladin.health.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paladin.framework.utils.qrcode.QRCodeException;
import com.paladin.framework.utils.qrcode.QRCodeUtil;

/**   
 * @author 黄伟华
 * @version 2019年6月5日 上午10:35:51 
 */
@Controller
@RequestMapping(value = "/app")
public class AppController {
    
	@Value("${paladin.app.index-url}")
	private String appIndexUrl;
	
    @RequestMapping("/url")
    public Object iosIndex(HttpServletRequest request, HttpServletResponse response){ 
        try{
            QRCodeUtil.createQRCode(appIndexUrl, response.getOutputStream());// 生成二维码
        }
        catch (QRCodeException | IOException e){
        }
        return null;
    }

}
