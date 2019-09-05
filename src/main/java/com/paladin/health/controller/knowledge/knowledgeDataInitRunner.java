package com.paladin.health.controller.knowledge;

import com.paladin.framework.utils.JsonUtil;
import com.paladin.health.service.core.xk.XKHealthPrescriptionService;
import com.paladin.health.service.knowledge.KnowledgeBaseDetailService;
import com.paladin.health.service.knowledge.KnowledgeBaseService;
import com.paladin.health.service.knowledge.dto.KnowledgeDetailInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2019/7/17 8:29
 */
@Component
public class knowledgeDataInitRunner implements CommandLineRunner {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(knowledgeDataInitRunner.class);

    @Autowired
    private XKHealthPrescriptionService healthPrescriptionService;

    @Autowired
    private KnowledgeBaseService knowledgeBaseService;
    @Autowired
    private KnowledgeBaseDetailService knowledgeBaseDetailService;

    @Override
    public void run(String... args){
        LOGGER.info("<------------------暂不需要存数据------------------------->");
/*        List<ConstantsContainer.KeyValue> keyValues = ConstantsContainer.getType(XKHealthPrescriptionService.CONSTANT_DISEASE_TYPE);
        int total = 0;
        List<String> lists = Collections.singletonList("XK.5103_1001.0053");
        LOGGER.info("<--------------开始存知识库数据------------------>");
            for (ConstantsContainer.KeyValue keyValue : keyValues) {
        try {
            total += save(keyValue.getKey());
        } catch (Exception e) {
            LOGGER.error("<----------存" + keyValue.getKey()+ "知识数据时出错:------------>",e.getCause());
            continue;
        }
    }
        LOGGER.info("<----------一共存了" + total+ "条知识库数据------------>");*/
    }

/*    public int save(String code) throws Exception {
        int count = 0;
        XKDiseaseKnowledge data = healthPrescriptionService.getKnowledge(code);
        KnowledgeBase knowledgeBase = new KnowledgeBase();
        String uuid = UUIDUtil.createUUID();
        knowledgeBase.setId(uuid);
        knowledgeBase.setCode(data.getCode());
        knowledgeBase.setType(2);
        knowledgeBase.setName(data.getName());
        List knowledge = data.getKnowledge();
        String json = JsonUtil.getJson(knowledge);
            List<Map> lists = JsonUtil.parseJsonList(json, Map.class);
            if (lists != null) {
                knowledgeBase.setDepartment( lists.get(0).get("ad").toString().replaceAll("\\s*", ""));
                knowledgeBase.setDiseaseOverview( lists.get(0).get("dm").toString().replaceAll("\\s*", ""));
                knowledgeBase.setDiseaseClassification( lists.get(0).get("dc").toString().replaceAll("\\s*", ""));
                knowledgeBase.setCreateTime(new Date());
                knowledgeBase.setCreateUserId("admin");
                knowledgeBase.setUpdateTime(new Date());
                knowledgeBase.setUpdateUserId("admin");
                int save = knowledgeBaseService.save(knowledgeBase);
                if (save > 0) {
                    int i = 0;
                    KnowledgeBaseDetail knowledgeBaseDetail = new KnowledgeBaseDetail();
                    knowledgeBaseDetail.setKnowledgeId(uuid);
                    knowledgeBaseDetail.setCreateTime(new Date());
                    knowledgeBaseDetail.setCreateUserId("admin");
                    knowledgeBaseDetail.setUpdateTime(new Date());
                    knowledgeBaseDetail.setUpdateUserId("admin");
                    for (Map map : lists) {
                        knowledgeBaseDetail.setId(UUIDUtil.createUUID());
                        knowledgeBaseDetail.setTitle(map.get("cr").toString());
                        knowledgeBaseDetail.setCause( parseContent(map.get("dd_c").toString()));
                        knowledgeBaseDetail.setSymptom( parseContent(map.get("dd_s").toString()));
                        knowledgeBaseDetail.setRisk( parseContent(map.get("dd_r").toString()));
                        knowledgeBaseDetail.setLifestyle( parseContent(map.get("dd_l").toString()));
                        knowledgeBaseDetail.setDietaryAdvice( parseContent(map.get("dd_d").toString()));
                        knowledgeBaseDetail.setDietaryShouldEat( parseContent(map.get("dd_d_s").toString()));
                        knowledgeBaseDetail.setDietaryAvoidEat( parseContent(map.get("dd_d_a").toString()));
                        knowledgeBaseDetail.setSportsAdvice( parseContent(map.get("dd_sa").toString()));
                        knowledgeBaseDetail.setSportsShouldDo( parseContent(map.get("dd_sa_s").toString()));
                        knowledgeBaseDetail.setSportsAvoidDo( parseContent(map.get("dd_sa_a").toString()));
                        knowledgeBaseDetail.setMedicalInsurance( parseContent(map.get("dd_m").toString()));
                        knowledgeBaseDetail.setMedicalReviewGuide( parseContent(map.get("dd_g").toString()));
                        knowledgeBaseDetail.setLifeCommonSense( parseContent(map.get("dd_n").toString()));
                        i += knowledgeBaseDetailService.save(knowledgeBaseDetail);
                    }
                    if (i == lists.size()) {
                        count = 1;
                    }
                }
            }
        return  count;
    }*/

    public String parseContent(String content) {
        if (content.length() > 1) {
          List<KnowledgeDetailInfo> lists = new ArrayList<>();
          String[] keyValues = content.split("@#");
                if (keyValues.length > 0) {
                    for (String keyValue : keyValues) {
                      String[] kvs = keyValue.split("#@");
                        KnowledgeDetailInfo knowledgeDetailDTO;
                      if (kvs.length > 1) {
                        knowledgeDetailDTO = new KnowledgeDetailInfo();
                        knowledgeDetailDTO.setKey(kvs[0].replaceAll("\\s*", ""));
                        knowledgeDetailDTO.setValue(kvs[1].replaceAll("\\s*", ""));
                        lists.add(knowledgeDetailDTO);
                      } else {
                        knowledgeDetailDTO = new KnowledgeDetailInfo();
                        knowledgeDetailDTO.setKey(kvs[0].replaceAll("\\s*", ""));
                        lists.add(knowledgeDetailDTO);
                      }
                    }
                }
          return JsonUtil.getJson(lists);
        } else {
          return null;
        }
    }
}
