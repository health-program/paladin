package com.paladin.health.service.knowledge.vo;

/**
 * <知识库详细信息列表简单展示>
 *
 * @author Huangguochen
 * @create 2019/7/18 8:49
 */
public class KnowledgeBaseSimpleVO {

    private String id;

    private String knowledgeId;

    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
