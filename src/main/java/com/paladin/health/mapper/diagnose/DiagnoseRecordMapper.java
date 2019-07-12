package com.paladin.health.mapper.diagnose;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.health.model.diagnose.DiagnoseRecord;
import com.paladin.health.service.diagnose.vo.DiagnoseRecordCountVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface DiagnoseRecordMapper extends CustomMapper<DiagnoseRecord> {

	int updateCorrectPrescription(@Param("id") String id, @Param("correctPrescription") String correctPrescription, @Param("sendMessage") String sendMessage,
			@Param("sendStatus") int sendStatus, @Param("sendError") String sendError, @Param("sendCellphone") String sendCellphone,
			@Param("confirmer") String confirmer);

	DiagnoseRecord findRecordBySearchId(@Param("searchId") String searchId, @Param("accessKey") String accessKey);

	DiagnoseRecord findLastRecordByIdentificationId(@Param("identificationId") String identificationId);

	List<DiagnoseRecordCountVO> countRecordByHospitalName(@Param("hospitalName") String hospitalName, @Param("bgTime") Date bgTime,
			@Param("endTime") Date endTime);

	List<DiagnoseRecordCountVO> countSendedRecordByHospitalName(@Param("hospitalName") String hospitalName, @Param("bgTime") Date bgTime,
			@Param("endTime") Date endTime);

	
}