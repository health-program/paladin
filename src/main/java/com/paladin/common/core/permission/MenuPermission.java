package com.paladin.common.core.permission;

import com.paladin.common.model.org.OrgPermission;
import com.paladin.framework.common.BaseModel;

public class MenuPermission {

	private String id;
	// 菜单名称
	private String name;
	// URL
	private String url;
	// 图标
	private String icon;
	// 父ID
	private String parentId;
	// 列表顺序
	private int listOrder;
	// 是否系统管理员权限
	private boolean isAdmin;
	// 是否拥有
	private boolean owned;
	
	public MenuPermission(OrgPermission orgPermission, boolean owned) {
		if (orgPermission.getIsMenu() == BaseModel.BOOLEAN_YES) {
			this.id = orgPermission.getId();
			this.name = orgPermission.getName();
			this.url = orgPermission.getExpressionContent();
			this.isAdmin = orgPermission.getIsAdmin() == BaseModel.BOOLEAN_YES;
			this.icon = orgPermission.getMenuIcon();
			this.parentId = orgPermission.getParentId();
			this.owned = owned;
		} else {
			throw new RuntimeException("非菜单权限");
		}
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getIcon() {
		return icon;
	}

	public String getParentId() {
		return parentId;
	}

	public int getListOrder() {
		return listOrder;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public boolean isOwned() {
		return owned;
	}

	public void setOwned(boolean owned) {
		this.owned = owned;
	}

}
