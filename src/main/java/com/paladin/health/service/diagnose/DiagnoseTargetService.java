package com.paladin.health.service.diagnose;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.health.mapper.diagnose.DiagnoseTargetMapper;
import com.paladin.health.model.diagnose.DiagnoseTarget;
import com.paladin.health.service.diagnose.dto.MessageTargetQuery;
import com.paladin.health.service.diagnose.vo.DiagnoseTargetSimpleVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;

@Service
public class DiagnoseTargetService extends ServiceSupport<DiagnoseTarget> {

	@Autowired
	private DiagnoseTargetMapper diagnoseTargetMapper;

	private final int oldAge = 60;

	public PageResult<DiagnoseTargetSimpleVO> findTarget2SendMessage(String publishTarget, int offset, int limit) {
		Page<DiagnoseTargetSimpleVO> page = PageHelper.offsetPage(offset, limit);

		MessageTargetQuery query = new MessageTargetQuery();
		if (publishTarget != null && publishTarget.length() > 0) {
			String[] targets = publishTarget.split(",");
			for (String target : targets) {
				if ("lnr".equals(target)) {
					query.setOld("lnr");
					query.setBirthday(getBackDate(oldAge));
				} else if ("af".equals(target)) {
					query.setAf("af");
				} else if ("gxb".equals(target)) {
					query.setGxb("gxb");
				} else if ("tnb".equals(target)) {
					query.setTnb("tnb");
				} else if ("gxy".equals(target)) {
					query.setGxy("gxy");
				} else {
					throw new BusinessException("未知发布目标：" + publishTarget);
				}
			}
			
			diagnoseTargetMapper.findTargetForMessage(query);
		} else {
			diagnoseTargetMapper.findAllTargetForMessage();
		}

		return new PageResult<>(page);
	}

	public Date getBackDate(int backYear) {
		Calendar now = Calendar.getInstance();
		now.set(Calendar.YEAR, now.get(Calendar.YEAR) - backYear);
		return now.getTime();
	}



}