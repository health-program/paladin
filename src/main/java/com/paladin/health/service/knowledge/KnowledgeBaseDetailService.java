package com.paladin.health.service.knowledge;

import com.google.common.base.Strings;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.JsonUtil;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.health.mapper.knowledge.KnowledgeBaseDetailMapper;
import com.paladin.health.model.knowledge.KnowledgeBaseDetail;
import com.paladin.health.service.knowledge.dto.KnowledgeBaseDetailDTO;
import com.paladin.health.service.knowledge.dto.KnowledgeBaseDetailQuery;
import com.paladin.health.service.knowledge.dto.KnowledgeDetailInfo;
import com.paladin.health.service.knowledge.vo.KnowledgeBaseDetailVO;
import com.paladin.health.service.knowledge.vo.KnowledgeBaseSimpleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class KnowledgeBaseDetailService extends ServiceSupport<KnowledgeBaseDetail> {

    @Autowired
    KnowledgeBaseDetailMapper knowledgeBaseDetailMapper;

    public KnowledgeBaseDetailVO getKnowledgeDetailById(String id) throws IOException {
        KnowledgeBaseDetail detail1 = get(id);
        if (detail1 == null) {
            throw new BusinessException("疾病或指标的详细信息不存在");
        }
        KnowledgeBaseDetailVO vo  = new KnowledgeBaseDetailVO();
            vo.setId(detail1.getId());
            vo.setKnowledgeId(detail1.getKnowledgeId());
            vo.setTitle(detail1.getTitle());
            vo.setCause(parseContent(detail1.getCause()));
            vo.setSymptom(parseContent(detail1.getSymptom()));
            vo.setRisk(parseContent(detail1.getRisk()));
            vo.setLifestyle(parseContent(detail1.getLifestyle()));
            vo.setDietaryAdvice(parseContent(detail1.getDietaryAdvice()));
            vo.setDietaryShouldEat(parseContent(detail1.getDietaryShouldEat()));
            vo.setDietaryAvoidEat(parseContent(detail1.getDietaryAvoidEat()));
            vo.setSportsAdvice(parseContent(detail1.getSportsAdvice()));
            vo.setSportsShouldDo(parseContent(detail1.getSportsShouldDo()));
            vo.setSportsAvoidDo(parseContent(detail1.getSportsAvoidDo()));
            vo.setMedicalInsurance(parseContent(detail1.getMedicalInsurance()));
            vo.setMedicalReviewGuide(parseContent(detail1.getMedicalReviewGuide()));
            vo.setLifeCommonSense(parseContent(detail1.getLifeCommonSense()));
        return vo;
    }

    private List<KnowledgeDetailInfo> parseContent(String content) throws IOException {
        if (!Strings.isNullOrEmpty(content)) {
            return  JsonUtil.parseJsonList(content, KnowledgeDetailInfo.class);
        }else {
            return null;
        }
    }

    private String writeContent(List<KnowledgeDetailInfo> lists){
        if (lists != null && lists.size() > 0) {
            return JsonUtil.getJson(lists);
        }else {
            return null;
        }
    }

    public List<KnowledgeBaseSimpleVO> searchknowledgeSimpleInfo(KnowledgeBaseDetailQuery query) {
        return knowledgeBaseDetailMapper.searchknowledgeSimpleInfo(query);
    }

    @Transactional
    public int saveOrUpdateKnowledgeBaseDetail(KnowledgeBaseDetailDTO knowledgeBaseDetailDTO) {
        if (Strings.isNullOrEmpty(knowledgeBaseDetailDTO.getKnowledgeId())) {
            throw new BusinessException("疾病或指标的基本信息不存在");
        }
        KnowledgeBaseDetail detail = new KnowledgeBaseDetail();
        detail.setId(knowledgeBaseDetailDTO.getId());
        detail.setKnowledgeId(knowledgeBaseDetailDTO.getKnowledgeId());
        detail.setTitle(knowledgeBaseDetailDTO.getTitle());
        detail.setCause(writeContent(knowledgeBaseDetailDTO.getCause()));
        detail.setSymptom(writeContent(knowledgeBaseDetailDTO.getSymptom()));
        detail.setRisk(writeContent(knowledgeBaseDetailDTO.getRisk()));
        detail.setLifestyle(writeContent(knowledgeBaseDetailDTO.getLifestyle()));
        detail.setDietaryAdvice(writeContent(knowledgeBaseDetailDTO.getDietaryAdvice()));
        detail.setDietaryShouldEat(writeContent(knowledgeBaseDetailDTO.getDietaryShouldEat()));
        detail.setDietaryAvoidEat(writeContent(knowledgeBaseDetailDTO.getDietaryAvoidEat()));
        detail.setSportsAdvice(writeContent(knowledgeBaseDetailDTO.getSportsAdvice()));
        detail.setSportsShouldDo(writeContent(knowledgeBaseDetailDTO.getSportsShouldDo()));
        detail.setSportsAvoidDo(writeContent(knowledgeBaseDetailDTO.getSportsAvoidDo()));
        detail.setMedicalInsurance(writeContent(knowledgeBaseDetailDTO.getMedicalInsurance()));
        detail.setMedicalReviewGuide(writeContent(knowledgeBaseDetailDTO.getMedicalReviewGuide()));
        detail.setLifeCommonSense(writeContent(knowledgeBaseDetailDTO.getLifeCommonSense()));
        if (Strings.isNullOrEmpty(detail.getId())) {
            detail.setId(UUIDUtil.createUUID());
           return save(detail);
        }else {
            return updateKnowledge(detail);
        }
    }

    @Transactional
    public int updateKnowledge(KnowledgeBaseDetail detail) {
        if (Strings.isNullOrEmpty(detail.getId())) {
            throw new BusinessException("疾病或指标的详细信息不存在");
        }
        int i = removeByPrimaryKey(detail.getId());
        if (i > 0) {
           return save(detail);
        } else {
            return  0;
        }

    }
}