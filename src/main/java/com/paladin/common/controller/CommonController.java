package com.paladin.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.paladin.common.model.syst.SysAttachment;
import com.paladin.common.service.syst.SysAttachmentService;
import com.paladin.framework.core.container.ConstantsContainer;
import com.paladin.framework.web.response.CommonResponse;

@Controller
@RequestMapping("/common")
public class CommonController {

	@Autowired
	private SysAttachmentService attachmentService;

	@RequestMapping("/constant")
	@ResponseBody
	public Object enumConstants(@RequestParam("code") String[] code) {
		return CommonResponse.getSuccessResponse(ConstantsContainer.getTypeChildren(code));
	}

	@RequestMapping("/attachment/{id}")
	@ResponseBody
	public Object getAttachment(@PathVariable("id") String id) {
		return CommonResponse.getSuccessResponse(attachmentService.get(id));
	}

	@RequestMapping("/attachment")
	@ResponseBody
	public Object getAttachments(@RequestParam("id[]") String[] ids) {
		return CommonResponse.getSuccessResponse(attachmentService.getAttachment(ids));
	}

	@RequestMapping("/upload/files")
	@ResponseBody
	public Object uploadFiles(@RequestParam("files") MultipartFile[] files, @RequestParam(value = "filenames", required = false) String[] names) {
		SysAttachment[] result = new SysAttachment[files.length];
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String filename = (names != null && names.length > i) ? names[i] : null;
			result[i] = attachmentService.createAttachment(file, filename);
		}
		return CommonResponse.getSuccessResponse(result);
	}

	/**
	 * 这是处理base64格式文件的方法，app使用，暂时不改名称了（注意方法名称和资源路径要表达出核心内容和与其他方法的不同）
	 * 比较合理命名如下
	 * @param imageStr
	 * @param imageName
	 * @return
	 */
	@RequestMapping("/upload/images")
	@ResponseBody
	public Object uploadImageByBase64(@RequestParam("imageStr") String imageStr, @RequestParam(value = "imageName", required = false) String imageName) {
		if (imageStr == null || imageStr.length() == 0) {
			return CommonResponse.getErrorResponse("上传图片的图片为空");
		}
		SysAttachment result = attachmentService.createAttachment(imageStr, imageName == null || imageName.length() == 0 ? "附件" : imageName, "image/jpeg");
		return CommonResponse.getSuccessResponse(result);
	}

	/**
	 * 上传base64
	 * 
	 * @param fileContent
	 * @param fileName
	 * @return
	 */
	@RequestMapping("/upload/file/base64")
	@ResponseBody
	public Object uploadBase64File(@RequestParam String fileContent, @RequestParam(required = false) String fileName,
			@RequestParam(required = false) String fileType) {
		if (fileContent == null || fileContent.length() == 0) {
			return CommonResponse.getErrorResponse("上传文件为空");
		}
		SysAttachment result = attachmentService.createAttachment(fileContent, fileName == null || fileName.length() == 0 ? "附件" : fileName, fileType);
		return CommonResponse.getSuccessResponse(result);
	}

	@RequestMapping("/upload/file")
	@ResponseBody
	public Object uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(name = "filename", required = false) String name) {
		return CommonResponse.getSuccessResponse(attachmentService.createAttachment(file, name));
	}

}
