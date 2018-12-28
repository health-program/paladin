package com.paladin.health.service.videomanage.vo;

import java.util.Date;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2018/12/26 16:18
 */
public class VideoShowVo {

    private String id;

    private  String url;

    // 视频名称
    private String name;

    // 展示图片(附件ID)
    private String showImage;

    // 视频简介
    private String summary;

    // 标签
    private String label;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowImage() {
        return showImage;
    }

    public void setShowImage(String showImage) {
        this.showImage = showImage;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
