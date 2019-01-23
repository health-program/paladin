package com.paladin.common.service.syst;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import net.coobird.thumbnailator.Thumbnails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.common.mapper.syst.SysAttachmentMapper;
import com.paladin.common.model.syst.SysAttachment;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.common.GeneralCriteriaBuilder.Condition;
import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.exception.SystemException;
import com.paladin.framework.utils.Base64Util;
import com.paladin.framework.utils.uuid.UUIDUtil;

@Service
public class SysAttachmentService extends ServiceSupport<SysAttachment> {

	@Autowired
	private SysAttachmentMapper sysAttachmentMapper;
	
	@Value("${attachment.path}")
	private String attachmentPath;

	@Value("${attachment.maxSize}")
	private int maxFileSize;

	private long maxFileByteSize;

	private int maxFileNameSize = 50;

	@PostConstruct
	protected void initialize() {
		attachmentPath = attachmentPath.replaceAll("\\\\", "/");

		if (!attachmentPath.endsWith("/")) {
			attachmentPath += "/";
		}

		if (maxFileSize <= 0) {
			maxFileSize = 10;
		}

		maxFileByteSize = maxFileSize * 1024 * 1024;

		// 创建目录
		Path root = Paths.get(attachmentPath);
		try {
			Files.createDirectories(root);
		} catch (IOException e) {
			throw new RuntimeException("创建附件存放目录异常", e);
		}
	}

	/**
	 * 创建附件（MultipartFile格式）
	 * 
	 * @param file
	 * @param attachmentName
	 * @return
	 */
	public SysAttachment createAttachment(MultipartFile file, String attachmentName ,Boolean isCompress) {
		return createAttachment(file, attachmentName, null ,isCompress);
	}

	/**
	 * 创建附件（MultipartFile格式）
	 * 
	 * @param file
	 * @param attachmentName
	 * @param userType
	 * @return
	 */
	public SysAttachment createAttachment(MultipartFile file, String attachmentName, Integer userType ,Boolean isCompress) {
		String id = UUIDUtil.createUUID();
		String name = file.getOriginalFilename();
		long size = file.getSize();
		if (size > maxFileByteSize) {
			throw new BusinessException("上传附件不能大于" + maxFileSize + "MB");
		}

		SysAttachment attachment = new SysAttachment();
		attachment.setId(id);
		attachment.setSize(size);
		attachment.setType(file.getContentType());

		if (name != null && name.length() > 0) {
			int i = name.lastIndexOf(".");
			if (i >= 0) {
				String suffix = name.substring(i);
				attachment.setSuffix(suffix);
			}

			if (attachmentName == null || attachmentName.length() == 0) {
				attachmentName = i >= 0 ? name.substring(0, i) : name;
			}
		}

		attachment.setName(attachmentName);

		if (userType == null || (userType != SysAttachment.USE_TYPE_COLUMN_RELATION && userType != SysAttachment.USE_TYPE_RESOURCE)) {
			userType = SysAttachment.USE_TYPE_COLUMN_RELATION;
		}
		
		attachment.setUseType(userType);

		try {
			if(isCompress){
				//压缩图片
				saveAndCompressImage(file ,attachment,null);
			}else{
			saveFile(file.getBytes(), attachment);
			}
		} catch (IOException e) {
			throw new SystemException("保存附件文件失败", e);
		}

		save(attachment);
		return attachment;
	}

	/**
	 * 创建附件（base64格式）
	 * 
	 * @param base64String
	 * @param filename
	 * @return
	 */
	public SysAttachment createAttachment(String base64String, String filename, String type) {
		return createAttachment(base64String, filename, null);
	}

	/**
	 * 创建附件（base64格式）
	 * 
	 * @param base64String
	 * @param filename
	 * @param userType
	 * @return
	 */
	public SysAttachment createAttachment(String base64String, String filename, String type, Integer userType) {
		String id = UUIDUtil.createUUID();
		long size = Base64Util.getFileSize(base64String);
		if (size > maxFileByteSize) {
			throw new BusinessException("上传附件不能大于" + maxFileSize + "MB");
		}

		SysAttachment attachment = new SysAttachment();
		attachment.setId(id);
		attachment.setSize(size);
		attachment.setType(type);

		if (filename != null && filename.length() > 0) {
			int i = filename.lastIndexOf(".");
			if (i >= 0) {
				String suffix = filename.substring(i);
				attachment.setSuffix(suffix);
				attachment.setName(filename.substring(0, i));
			} else {
				attachment.setName(filename);
			}
		} else {
			throw new BusinessException("文件名不能为空");
		}

		if (userType == null || (userType != SysAttachment.USE_TYPE_COLUMN_RELATION && userType != SysAttachment.USE_TYPE_RESOURCE)) {
			userType = SysAttachment.USE_TYPE_COLUMN_RELATION;
		}
		
		attachment.setUseType(userType);
		
		try {
			saveFile(Base64Util.decode(base64String), attachment);
		} catch (IOException e) {
			throw new SystemException("保存附件文件失败", e);
		}
		save(attachment);
		return attachment;
	}

	/**
	 * 创建附件（字节格式）
	 * 
	 * @param data
	 * @param filename
	 * @param type
	 * @return
	 */
	public SysAttachment createAttachment(byte[] data, String filename, String type) {
		return createAttachment(data, filename, type, null, null);
	}

	/**
	 * 创建附件（字节格式）
	 * 
	 * @param base64String
	 * @param filename
	 * @param subFilePath
	 *            子路径
	 * @return
	 */
	public SysAttachment createAttachment(byte[] data, String filename, String type, String subFilePath, Integer userType) {
		String id = UUIDUtil.createUUID();
		long size = data.length;
		if (size > maxFileByteSize) {
			throw new BusinessException("上传附件不能大于" + maxFileSize + "MB");
		}

		SysAttachment attachment = new SysAttachment();
		attachment.setId(id);
		attachment.setSize(size);
		attachment.setType(type);

		if (filename != null && filename.length() > 0) {
			int i = filename.lastIndexOf(".");
			if (i >= 0) {
				String suffix = filename.substring(i);
				attachment.setSuffix(suffix);
				attachment.setName(filename.substring(0, i));
			} else {
				attachment.setName(filename);
			}
		} else {
			throw new BusinessException("文件名不能为空");
		}
		
		if (userType == null || (userType != SysAttachment.USE_TYPE_COLUMN_RELATION && userType != SysAttachment.USE_TYPE_RESOURCE)) {
			userType = SysAttachment.USE_TYPE_COLUMN_RELATION;
		}
		attachment.setUseType(userType);
		
		try {
			saveFile(data, attachment, subFilePath);
		} catch (IOException e) {
			throw new SystemException("保存附件文件失败", e);
		}
		save(attachment);
		return attachment;
	}

	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 保存文件，并生成相对路径到附件数据中，子路径为当前日期
	 * 
	 * @param data
	 * @param attachment
	 * @throws IOException
	 */
	private void saveFile(byte[] data, SysAttachment attachment) throws IOException {
		saveFile(data, attachment, null);
	}

	/**
	 * 保存文件，并生成相对路径到附件数据中
	 * 
	 * @param data
	 * @param attachment
	 * @param subPath
	 * @throws IOException
	 */
	private void saveFile(byte[] data, SysAttachment attachment, String subPath) throws IOException {
		if (data == null || data.length == 0) {
			throw new SystemException("文件为空");
		}

		if (subPath == null || subPath.length() == 0) {
			subPath = format.format(new Date());
		}

		Path path = Paths.get(attachmentPath, subPath);
		if (!Files.exists(path)) {
			try {
				Files.createDirectory(path);
			} catch (FileAlreadyExistsException e1) {
				// 继续
			}
		}

		String filename = attachment.getId();
		String suffix = attachment.getSuffix();
		if (suffix != null) {
			filename += suffix;
		}

		String pelativePath = subPath + "/" + filename;
		Files.write(Paths.get(attachmentPath + pelativePath), data);
		
		attachment.setPelativePath(pelativePath);
		attachment.setCreateTime(new Date());

		// 附件名称太长则截取
		String attachmentName = attachment.getName();
		if (attachmentName != null && attachmentName.length() > maxFileNameSize) {
			attachmentName = attachmentName.substring(0, maxFileNameSize);
		}
	}
	/**
	 * 压缩并保存图片，缩略图
	 * @param file
	 * @param attachment
	 * @param subPath
	 * @throws IOException
	 */
	private void saveAndCompressImage(MultipartFile file, SysAttachment attachment, String subPath) throws IOException {
		if (file.isEmpty() || file.getBytes().length == 0) {
			throw new SystemException("文件为空");
		}

		if (subPath == null || subPath.length() == 0) {
			subPath = format.format(new Date());
		}

		Path path = Paths.get(attachmentPath, subPath);
		if (!Files.exists(path)) {
			try {
				Files.createDirectory(path);
			} catch (FileAlreadyExistsException e1) {
				// 继续
			}
		}

		String filename = attachment.getId();
		String suffix = attachment.getSuffix();
		if (suffix != null) {
			filename += suffix;
		}

		String pelativePath = subPath + "/" + filename;
		Thumbnails.of(file.getInputStream()).size(200,200).toFile(attachmentPath + pelativePath);
		//获取压缩之后图片的大小
		attachment.setSize( new File(attachmentPath + pelativePath).length());
		attachment.setPelativePath(pelativePath);
		attachment.setCreateTime(new Date());

		// 附件名称太长则截取
		String attachmentName = attachment.getName();
		if (attachmentName != null && attachmentName.length() > maxFileNameSize) {
			attachmentName = attachmentName.substring(0, maxFileNameSize);
		}
	}
	/**
	 * 获取文件附件记录
	 * 
	 * @param ids
	 * @return
	 */
	public List<SysAttachment> getAttachment(String... ids) {
		return searchAll(new Condition(SysAttachment.COLUMN_FIELD_ID, QueryType.IN, Arrays.asList(ids)));
	}
	
	/**
	 * 获取文件附件记录
	 * 
	 * @param ids
	 * @return
	 */
	public PageResult<SysAttachment> getResourceImagePage(OffsetPage query) {
		Page<SysAttachment> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		sysAttachmentMapper.findImageResource();
		return new PageResult<>(page);
	}

	/**
	 * 通过拼接字符串查出附件或者创建新的附件
	 * @param isCompress 是否压缩图片
	 * @param idString
	 * @param attachmentFiles
	 * @return
	 */
	public List<SysAttachment> checkOrCreateAttachment(String idString, MultipartFile[] attachmentFiles, Boolean isCompress) {

		List<SysAttachment> attIds = null;

		if (idString != null && idString.length() != 0) {
			attIds = getAttachment(idString.split(","));

		}

		if (attachmentFiles != null) {
			if (attIds == null) {
				attIds = new ArrayList<>(attachmentFiles.length);
			}
			for (MultipartFile file : attachmentFiles) {
				SysAttachment a = createAttachment(file, null, isCompress);
				attIds.add(a);
			}
		}

		return attIds;
	}

	/**
	 * 拼接附件ID字符串
	 * 
	 * @param attachments
	 * @return
	 */
	public String splicingAttachmentId(List<SysAttachment> attachments) {
		if (attachments != null && attachments.size() > 0) {
			String str = "";
			for (SysAttachment attachment : attachments) {
				str += attachment.getId() + ",";
			}
			return str;
		}
		return null;
	}

}