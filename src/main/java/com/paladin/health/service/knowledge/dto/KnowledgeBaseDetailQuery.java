package com.paladin.health.service.knowledge.dto;

import com.paladin.framework.common.OffsetPage;

public class KnowledgeBaseDetailQuery extends OffsetPage {

    private String knowledgeId;

    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }
}