package com.paladin.common.service.syst;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.paladin.common.model.syst.SysAttachment;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.common.GeneralCriteriaBuilder.Condition;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.core.exception.SystemException;
import com.paladin.framework.utils.Base64Util;
import com.paladin.framework.utils.uuid.UUIDUtil;

@Service
public class SysAttachmentService extends ServiceSupport<SysAttachment> {

	@Value("${attachment.path}")
	private String attachmentPath;

	@Value("${attachment.maxSize}")
	private int maxFileSize;

	private long maxFileByteSize;

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
	public SysAttachment createAttachment(MultipartFile file, String attachmentName) {
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

		try {
			saveFile(file.getBytes(), attachment);
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

		try {
			saveFile(Base64Util.decode(base64String), attachment);
		} catch (IOException e) {
			throw new SystemException("保存附件文件失败", e);
		}
		save(attachment);
		return attachment;
	}

	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

	private void saveFile(byte[] data, SysAttachment attachment) throws IOException {
		if (data == null || data.length == 0) {
			throw new SystemException("文件为空");
		}

		String date = format.format(new Date());
		Path path = Paths.get(attachmentPath, date);
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

		String pelativePath = date + "/" + filename;
		Files.write(Paths.get(attachmentPath + pelativePath), data);

		attachment.setPelativePath(pelativePath);
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
	 * 通过拼接字符串查出附件或者创建新的附件
	 * 
	 * @param idString
	 * @param attachmentFiles
	 * @return
	 */
	public List<SysAttachment> checkOrCreateAttachment(String idString, MultipartFile[] attachmentFiles) {

		List<SysAttachment> attIds = null;

		if (idString != null && idString.length() != 0) {
			attIds = getAttachment(idString.split(","));

		}

		if (attachmentFiles != null) {
			if (attIds == null) {
				attIds = new ArrayList<>(attachmentFiles.length);
			}
			for (MultipartFile file : attachmentFiles) {
				SysAttachment a = createAttachment(file, null);
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