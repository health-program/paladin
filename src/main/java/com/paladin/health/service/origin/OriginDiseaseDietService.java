package com.paladin.health.service.origin;

import com.paladin.framework.common.GeneralCriteriaBuilder;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.health.model.origin.OriginDiseaseDiet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OriginDiseaseDietService extends ServiceSupport<OriginDiseaseDiet>{


    public List<OriginDiseaseDiet> findDiseaseDiet(String diseaseKey) {
        return searchAll(new GeneralCriteriaBuilder.Condition(OriginDiseaseDiet.COLUMN_FIELD_DISEASE_KEY, QueryType.EQUAL, diseaseKey));
    }

}