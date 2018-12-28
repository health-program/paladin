package com.paladin.health.service.videomanage.dto;

import com.paladin.framework.common.OffsetPage;

public class VideoQueryDTO extends OffsetPage {
      
      private String name;
      
      private String label;
      
      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getLabel() {
            return label;
      }

      public void setLabel(String label) {
            this.label = label;
      }
}