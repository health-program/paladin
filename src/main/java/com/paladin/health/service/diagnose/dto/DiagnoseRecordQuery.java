package com.paladin.health.service.diagnose.dto;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2019/3/20 14:46
 */
public class DiagnoseRecordQuery extends OffsetPage {

	private String targetId;

	@QueryCondition(type = QueryType.EQUAL)
	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

}
