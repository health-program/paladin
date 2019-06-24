package com.paladin.health.service.videomanage.dto;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.utils.StringUtil;

/**
 * <>
 *
 * @author Huangguochen
 * @create 2019/6/21 14:09
 */
public class VideoMessageQuery extends OffsetPage {

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = StringUtil.isEmpty(label) ? null : label.trim();
    }
}
