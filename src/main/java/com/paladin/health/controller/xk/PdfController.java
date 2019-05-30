package com.paladin.health.controller.xk;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.util.StringUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.health.model.diagnose.DiagnoseRecord;
import com.paladin.health.service.diagnose.DiagnoseRecordService;

/**   
 * @author 黄伟华
 * @version 2019年5月29日 下午3:30:11 
 */
@Controller
@RequestMapping("/")
public class PdfController {
    
    @Autowired
    private DiagnoseRecordService diagnoseRecordService;
    
    @GetMapping("/download")
    public void downloadCheckInfo(
    HttpServletRequest request, HttpServletResponse response) throws Exception {
	//@RequestParam("searchId") String searchId, @RequestParam String accessKey,
	String searchId="1111";
	String accessKey="demo";
	DiagnoseRecord record = diagnoseRecordService.getRecordBySearchId(searchId, accessKey);
      //1.创建PDF文件
      Document document = new Document();
      //横向，这个可以自己根据实际情况看需不需要，我的是竖着放不下，只能横向展示
      Rectangle pageSize = new Rectangle(PageSize.A4);
      //pageSize.setBackgroundColor(new BaseColor(245,245,245));//设置背景颜色
      document.setPageSize(pageSize);
      document.setMargins(36,36,30,30);//边距
      //2.创建书写器（Writer）对象
      response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
      setDownLoadDisposition(request, response, "熙康健康评估记录.pdf"); // 设置文件名
      PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
      writer.setViewerPreferences(PdfWriter.PageModeFullScreen);
      document.open();//3.打开文档。
      //4.向文档中添加内容。
      //中文字体,解决中文不能显示问题
      BaseFont titleChinese = BaseFont.createFont("/ttf/arialuni.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        Font tTont =new Font(titleChinese,16);  
        
        Paragraph title = new Paragraph("熙康健康评估记录 ",tTont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.add(Chunk.NEWLINE); // 好用的
        document.add(title); 
        
        /*--------------------------------正文---------------------------------*/
        if(record != null){
        BaseFont sfTTF = BaseFont.createFont("/ttf/STKAITI.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        Font font = new Font(sfTTF);
        BaseFont sfTTFF = BaseFont.createFont("/ttf/arialuni.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        Font fontt = new Font(sfTTFF,14);
        JSONObject objecta=new JSONObject(record.getTargetCondition());
       
        Paragraph info = new Paragraph();  
        info.add(Chunk.NEWLINE);
        info.add(new Phrase("姓名：",fontt));
        info.add(new Phrase(objecta.get("name").toString(),font));
        info.add("          ");
        info.add(new Phrase("性别：",fontt));
        info.add(new Phrase(objecta.get("sex")=="1"?"男":"女",font));
        info.add("          ");
        info.add(new Phrase("出生年月：",fontt));
        info.add(new Phrase(objecta.get("birthday").toString()==null?"":"",font)); 
        info.add("          ");
        info.add(new Phrase("评估时间：",fontt));
        info.add(new Phrase(getStringDate(record.getCreateTime()),font));  
        info.add(Chunk.NEWLINE);
        info.add(Chunk.NEWLINE);
        info.setAlignment(Element.ALIGN_CENTER);
        document.add(info);
        
        String prescription1 = record.getCorrectPrescription();
        if(prescription1 == null){
            prescription1 = record.getPrescription();
        }
        
        JSONArray jsonArray = new JSONArray(prescription1);
        for (int i=0; i < jsonArray.length(); i++){ 
            BaseColor color = null;
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int riskLevel = jsonObject.getInt("riskLevel");
           if(riskLevel == 4)
               color =new BaseColor(255,0,0);
           if(riskLevel == 3)
               color =new BaseColor(255,165,0);
           if(riskLevel == 2)
               color =new BaseColor(0,166,90);
            BaseFont sfTTF1 = BaseFont.createFont("/ttf/STKAITI.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
            Font fontColor = new Font(sfTTF1,13,Font.NORMAL, color);
            Font font1 = new Font(sfTTF1,13);
            Paragraph info1 = new Paragraph(); 
            info1.add(new Phrase("评估名称：",font1));
            info1.add(new Phrase(jsonObject.getString("name"),font1));
            info1.add(Chunk.NEWLINE);
            info1.add(new Phrase("风险等级：",font1));
            info1.add(new Phrase(jsonObject.getString("riskLevelName"),fontColor));
            info1.add(Chunk.NEWLINE);
            info1.add(new Phrase("分析建议：",font1));
            document.add(info1); 
            BaseFont sfTTF11 = BaseFont.createFont("/ttf/STKAITI.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
            Font font11 = new Font(sfTTF11,13);
            Paragraph info11 = new Paragraph(); 
            info11.add(new Phrase(jsonObject.getString("suggest"),font11));
            info11.setIndentationLeft(62);
            info11.setLeading(20f);
            info11.setSpacingAfter(20f);
            document.add(info11); 
        }
        }
        
        document.close();
    }

    private void setDownLoadDisposition(HttpServletRequest request,HttpServletResponse response, String fileName) {
	if (request == null || response == null || StringUtil.isEmpty(fileName)) {
	    throw new BusinessException("文件下载出错！");
	}try {
	    if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
		response.setHeader("Content-Disposition", "attachment;"+ "filename="+ new String(fileName.getBytes("GBK"), "ISO8859-1"));
	    } else {
		response.setHeader("Content-Disposition", "attachment;"+ "filename="+ new String(fileName.getBytes("UTF8"), "ISO8859-1"));
	    }
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	}
    }
    
    private static String getStringDate(Date date) {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	String dateString = formatter.format(date);
	return dateString;
    }

}
