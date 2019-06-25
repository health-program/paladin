package com.paladin.health.service.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.health.mapper.org.OrgAgencyMapper;
import com.paladin.health.model.org.OrgAgency;
import com.paladin.framework.core.ServiceSupport;

@Service
public class OrgAgencyService extends ServiceSupport<OrgAgency> {

	@Autowired
	private OrgAgencyMapper agencyMapper;

	public boolean removeAgency(String id) {
		return agencyMapper.removeAgency(id) > 0;
	}

}