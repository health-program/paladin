package com.paladin.health.service.publicity;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier.SimpleBeanCopyUtil;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.health.core.HealthUserSession;
import com.paladin.health.mapper.publicity.PublicityMessageMapper;
import com.paladin.health.model.publicity.PublicityMessage;
import com.paladin.health.service.publicity.dto.PublicityMessageDTO;
import com.paladin.health.service.publicity.dto.PublicityMessageQueryDTO;
import com.paladin.health.service.publicity.vo.PublicityMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PublicityMessageService extends ServiceSupport<PublicityMessage> {

	@Autowired
	private PublicityMessageMapper publicityMessageMapper;

	/**
	 * 保存信息
	 * 
	 * @param publicityMessage
	 * @return
	 */
	public boolean saveMessage(PublicityMessageDTO publicityMessage) {
		Integer statu = publicityMessage.getStatus();
		if (statu != null && (statu == PublicityMessage.STATUS_TEMP || statu == PublicityMessage.STATUS_SUBMIT_EXAMINE)) {
			PublicityMessage model = new PublicityMessage();
			SimpleBeanCopyUtil.simpleCopy(publicityMessage, model);
			if (model.getPublishTime() == null) {
				model.setPublishTime(new Date());
			}
			return save(model) > 0;
		} else {
			throw new BusinessException("信息状态不正确");
		}
	}

	/**
	 * 更新信息
	 * 
	 * @param publicityMessage
	 * @return
	 */
	public boolean updateMessage(PublicityMessageDTO publicityMessage) {
		String id = publicityMessage.getId();
		if (id != null && id.length() != 0) {
			PublicityMessage model = get(id);
			if (model == null) {
				throw new BusinessException("找不到需要编辑的信息");
			}

			Integer currentStatus = model.getStatus();
			if (currentStatus == PublicityMessage.STATUS_SUBMIT_EXAMINE) {
				throw new BusinessException("已经提交待审核的信息无法修改");
			}

			if (currentStatus == PublicityMessage.STATUS_EXAMINE_SUCCESS) {
				throw new BusinessException("已经审核成功的信息无法修改");
			}

			Integer statu = publicityMessage.getStatus();
			if (statu != null && (statu == PublicityMessage.STATUS_TEMP || statu == PublicityMessage.STATUS_SUBMIT_EXAMINE)) {
				SimpleBeanCopyUtil.simpleCopy(publicityMessage, model);
				if (model.getPublishTime() == null) {
					model.setPublishTime(new Date());
				}
				return update(model) > 0;
			} else {
				throw new BusinessException("信息状态不正确");
			}
		} else {
			throw new BusinessException("找不到需要编辑的信息");
		}
	}

	/**
	 * 审核
	 * 
	 * @param id
	 * @param success
	 * @return
	 */
	@Transactional
	public boolean examine(String id, boolean success) {
		PublicityMessage message = get(id);
		if (message == null) {
			throw new BusinessException("找不到需要审核的信息数据");
		}

		Integer status = message.getStatus();

		if (status != PublicityMessage.STATUS_SUBMIT_EXAMINE) {
			throw new BusinessException("只有提交审核状态才能审核");
		}

		status = success ? PublicityMessage.STATUS_EXAMINE_SUCCESS : PublicityMessage.STATUS_EXAMINE_FAIL;

		if (success) {
			// TODO 成功推送
		}

		if (publicityMessageMapper.updateExamineStatus(id, status, HealthUserSession.getCurrentUserSession().getUserId()) > 0) {
			return true;
		} else {
			throw new BusinessException("审核失败");
		}

	}

	/**
	 * 删除公告信息
	 * 
	 * @param id
	 * @return
	 */
	public boolean removeMessage(String id) {
		HealthUserSession session = HealthUserSession.getCurrentUserSession();
		if (session.isAdminRoleLevel()) {
			return removeByPrimaryKey(id) > 0;
		}

		PublicityMessage message = get(id);
		if (message == null) {
			throw new BusinessException("找不到需要删除的公告消息");
		}

		Integer status = message.getStatus();
		if (status == PublicityMessage.STATUS_EXAMINE_FAIL || status == PublicityMessage.STATUS_TEMP) {
			if (session.getUserId().equals(message.getCreateUserId())) {
				return removeByPrimaryKey(id) > 0;
			} else {
				throw new BusinessException("您不能删除别人的公告信息");
			}
		} else {
			throw new BusinessException("已经提交或审核成功的公告消息无法删除");
		}
	}

	/**
	 * 获取信息
	 * 
	 * @param id
	 * @return
	 */
	public PublicityMessageVO getMessage(String id) {
		return publicityMessageMapper.getMessage(id);
	}

	/**
	 * 分页查询自己发布的信息
	 * 
	 * @param query
	 * @return
	 */
	public PageResult<PublicityMessageVO> findSelfMessage(PublicityMessageQueryDTO query) {
		HealthUserSession session = HealthUserSession.getCurrentUserSession();
		if (!session.isAdminRoleLevel()) {
			if (query != null) {
				query = new PublicityMessageQueryDTO();
			}
			query.setCreateUserId(session.getUserId());
		}

		return findMessage(query);
	}

	/**
	 * 对外展示的信息列表
	 * 
	 * @param query
	 * @return
	 */
	public PageResult<PublicityMessageVO> findPublishedMessages(PublicityMessageQueryDTO query) {
		return findPublishedMessage(query);
	}

	/**
	 * 
	 * 分页查询
	 * 
	 * @param query
	 * @return
	 */
	public PageResult<PublicityMessageVO> findExamineMessage(PublicityMessageQueryDTO query) {
		// query.setStatus(PublicityMessage.STATUS_SUBMIT_EXAMINE);
		query.setCreateUserId(null);
		query.setStatuses(null);

		return findMessage(query);
	}

	/**
	 * 对外查询信息
	 * 
	 * @param query
	 * @return
	 */
	private PageResult<PublicityMessageVO> findPublishedMessage(PublicityMessageQueryDTO query) {
		Page<PublicityMessageVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		publicityMessageMapper.findPublishedMessage(query);
		return new PageResult<>(page);
	}

	/**
	 * 查询信息
	 * 
	 * @param query
	 * @return
	 */
	private PageResult<PublicityMessageVO> findMessage(PublicityMessageQueryDTO query) {
		Page<PublicityMessageVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		publicityMessageMapper.findMessage(query);
		return new PageResult<>(page);
	}

	public List<PublicityMessageVO> showDisplayMessage() {
		return publicityMessageMapper.findDisplay();
	}

	public PublicityMessageVO showPreMessage(String id, Date publishTime) {
		return publicityMessageMapper.findPreMessage(id, publishTime);
	}

	public PublicityMessageVO showNextMessage(String id, Date publishTime) {
		return publicityMessageMapper.findNextMessage(id, publishTime);
	}

	public List<PublicityMessageVO> findSendMessage(int hour) {
		return publicityMessageMapper.findSendMessage(hour);
	}

	public boolean updateToSended(String id, int count) {
		return publicityMessageMapper.updateToSended(id, count) > 0;
	}
}