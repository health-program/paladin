package com.paladin.health.service.publicity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.health.controller.publicity.pojo.MessageExamineQuery;
import com.paladin.health.controller.publicity.pojo.MessageQuery;
import com.paladin.health.core.HealthUserSession;
import com.paladin.health.mapper.publicity.PublicityMessageMoreMapper;
import com.paladin.health.model.publicity.PublicityMessage;
import com.paladin.health.model.publicity.PublicityMessageMore;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.GeneralCriteriaBuilder;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;

@Service
public class PublicityMessageService extends ServiceSupport<PublicityMessage> {

	@Autowired
	private PublicityMessageMoreMapper publicityMessageMoreMapper;

	/**
	 * 审核
	 * 
	 * @param id
	 * @param success
	 * @return
	 */
	public boolean examine(String id, boolean success) {
		PublicityMessage message = get(id);
		if (message == null) {
			throw new BusinessException("找不到需要审核的公告消息");
		}

		Integer status = message.getStatus();

		if (status != PublicityMessage.STATUS_SUBMIT_EXAMINE) {
			throw new BusinessException("只有提交审核状态才能审核");
		}

		PublicityMessage updateModel = new PublicityMessage();
		updateModel.setStatus(success ? PublicityMessage.STATUS_EXAMINE_SUCCESS : PublicityMessage.STATUS_EXAMINE_FAIL);
		return updateSelective(updateModel) > 0;
	}

	public int removeMessage(String id) {

		HealthUserSession session = HealthUserSession.getCurrentUserSession();
		if (session.isSystemAdmin()) {
			return removeByPrimaryKey(id);
		}

		PublicityMessage message = get(id);
		if (message == null) {
			throw new BusinessException("找不到需要删除的公告消息");
		}

		Integer status = message.getStatus();
		if (status == PublicityMessage.STATUS_EXAMINE_FAIL || status == PublicityMessage.STATUS_TEMP) {
			if (session.getUserId().equals(message.getCreateUserId())) {
				return removeByPrimaryKey(id);

			} else {
				throw new BusinessException("您不能删除别人的公告信息");
			}
		} else {
			throw new BusinessException("已经提交或审核成功的公告消息无法删除");
		}
	}

	/**
	 * 分页查询自己发布的信息
	 * 
	 * @param query
	 * @return
	 */
	public Page<PublicityMessage> findSelfMessage(MessageQuery query) {
		HealthUserSession session = HealthUserSession.getCurrentUserSession();
		if (!session.isSystemAdmin()) {
			if (query != null) {
				query = new MessageQuery();
			}

			query.setCreateUserId(session.getUserId());
		}
		return searchPage(query);
	}

	
	public Page<PublicityMessageMore> findExamineMessage(MessageExamineQuery query) {
		Page<PublicityMessageMore> pager = getPage(query);
		
		try {
			List<PublicityMessageMore> result = publicityMessageMoreMapper.selectJoinByExample(GeneralCriteriaBuilder.buildQuery(PublicityMessage.class, query));
			if (result == null || result.size() == 0) {
				pager.setTotal(0L);
			}
			return pager;
		} finally {
			PageHelper.clearPage();
		}
	}

}