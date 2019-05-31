package com.paladin.health.mapper.diagnose;

import org.apache.ibatis.annotations.Param;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.health.model.diagnose.DiagnoseRecord;

public interface DiagnoseRecordMapper extends CustomMapper<DiagnoseRecord> {

	int updateCorrectPrescription(@Param("id") String id, @Param("correctPrescription") String correctPrescription, @Param("sendMessage") String sendMessage);

	DiagnoseRecord findRecordBySearchId(@Param("searchId") String searchId, @Param("accessKey") String accessKey);

}