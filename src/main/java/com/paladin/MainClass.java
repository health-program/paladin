package com.paladin;

import com.paladin.framework.utils.SelectUtil;
import com.paladin.health.model.prescription.PrescriptionItem;

public class MainClass {
	
	public static void main(String[] args) {
		System.out.println(SelectUtil.getSelectSql(PrescriptionItem.class, "b", true));
	}
	
}
