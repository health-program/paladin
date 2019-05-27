package com.paladin.health.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.framework.core.VersionContainer;
import com.paladin.framework.core.VersionContainerManager;
import com.paladin.health.model.prescription.PrescriptionInterfaceManage;
import com.paladin.health.service.prescription.PrescriptionInterfaceManageService;

@Component
public class AuthKeyContainer implements VersionContainer {

	@Autowired
	private PrescriptionInterfaceManageService prescriptionInterfaceManageService;

	private Map<String, PrescriptionInterfaceManage> interfaceMap;
	
	private void initData() {
		Map<String, PrescriptionInterfaceManage> interfaceMap = new HashMap<>();
		List<PrescriptionInterfaceManage> results = prescriptionInterfaceManageService.findAll();
		for(PrescriptionInterfaceManage item: results) {
			interfaceMap.put(item.getAppKey(), item);
		}	
		this.interfaceMap = interfaceMap;
	}
	
	public boolean hasAccessKey(String accessKey) {
		return interfaceMap.containsKey(accessKey);
	}

	public void updateData() {
		VersionContainerManager.versionChanged(getId());
	}
	
	@Override
	public String getId() {
		return "auth_key_container";
	}

	@Override
	public boolean versionChangedHandle(long version) {
		initData();
		return true;
	}

}
