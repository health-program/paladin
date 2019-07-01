package com.paladin.health.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paladin.framework.core.VersionContainer;
import com.paladin.framework.core.VersionContainerManager;
import com.paladin.health.model.org.OrgAgency;
import com.paladin.health.service.org.OrgAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AgencyContainer implements VersionContainer {

	@Autowired
	private OrgAgencyService agencyService;

	private static AgencyContainer container;
	private static volatile Map<String, Agency> agencyMap;
	private static volatile List<Agency> roots;

	private synchronized void initData() {

		List<OrgAgency> agencys = agencyService.findAll();

		Map<String, Agency> agencyMap = new HashMap<>();
		List<Agency> roots = new ArrayList<>();

		for (OrgAgency orgAgency : agencys) {
			Agency agency = new Agency(orgAgency);
			agencyMap.put(orgAgency.getId(), agency);
		}

		for (Agency agency : agencyMap.values()) {
			if (agency.parentId == null) {
				roots.add(agency);
			} else {
				Agency parentAgency = agencyMap.get(agency.parentId);
				if (parentAgency == null) {
					roots.add(agency);
				} else {
					agency.parent = parentAgency;
					parentAgency.children.add(agency);
				}
			}
		}

		AgencyContainer.agencyMap = Collections.unmodifiableMap(agencyMap);
		AgencyContainer.roots = Collections.unmodifiableList(roots);

	}

	public static class Agency {

		@JsonIgnore
		OrgAgency orgAgency;

		private String id;
		private String name;
		private String parentId;

		@JsonIgnore
		private Agency parent;
		private List<Agency> children;

		public Agency(OrgAgency orgAgency) {
			this.id = orgAgency.getId();
			this.name = orgAgency.getName();
			this.parentId = orgAgency.getParentId();
			this.orgAgency = orgAgency;
			children = new ArrayList<>();
		}

		public OrgAgency getOrgAgency() {
			return orgAgency;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public Agency getParent() {
			return parent;
		}

		public List<Agency> getChildren() {
			return children;
		}

		public String getParentId() {
			return parentId;
		}

		public boolean equals(Object obj) {

			if (obj == null)
				return false;

			if (obj == this)
				return true;

			if (obj instanceof Agency) {
				return id.equals(((Agency) obj).id);
			}

			return false;
		}

		public int hashCode() {
			return id.hashCode();
		}

		public String toString() {
			if (parent != null) {
				return parent.toString() + "-" + name;
			} else {
				return name;
			}
		}

	}

	// ----------------------------------------------
	// 为了省力，不使用锁方式去持久化单个UNIT，而是每当数据变更则重新读取数据库
	// 如果使用分布式，则这里需要改为REDIS等方式
	// ----------------------------------------------

	public static void updateData() {
		VersionContainerManager.versionChanged(container.getId());
	}

	// ----------------------------------------------
	// 获取方法
	// ----------------------------------------------

	/**
	 * 判断是否父子关系
	 * @param parentId
	 * @param childId
	 * @return
	 */
	public static boolean isChild(String parentId, String childId) {
		return isChild(parentId, getAgency(childId));
	}

	/**
	 * 判断是否父子关系
	 * @param parentId
	 * @param childAgency
	 * @return
	 */
	public static boolean isChild(String parentId, Agency childAgency) {
		if (parentId == null || parentId.length() == 0 || childAgency == null)
			throw new IllegalArgumentException("参数不能为空");

		Agency pa = childAgency;
		while (pa != null) {
			if (parentId.equals(pa.id)) {
				return true;
			}
			pa = pa.getParent();
		}
		return false;
	}

	/**
	 * 获取根单位数组
	 * 
	 * @return
	 */
	public static List<Agency> getRoots() {
		return roots;
	}

	/**
	 * 根据ID获取单位
	 * 
	 * @param id
	 * @return
	 */
	public static Agency getAgency(String id) {
		return agencyMap.get(id);
	}

	/**
	 * 根据ID获取单位名称
	 * 
	 * @param id
	 * @return
	 */
	public static String getAgencyName(String id) {
		Agency agency = getAgency(id);
		return agency != null ? agency.getName() : null;
	}

	/**
	 * 根据ID获取根单位，未找到会抛出异常
	 * 
	 * @param id
	 * @return
	 */
	public static Agency getRootAgency(String id) {
		if (id != null) {
			Agency agency = agencyMap.get(id);
			if (agency != null) {
				while (agency.parent != null)
					agency = agency.parent;
				return agency;
			}
		}

		return null;
	}

	/**
	 * 获取根单位
	 * 
	 * @param agency
	 * @return
	 */
	public static Agency getRootAgency(Agency agency) {
		if (agency != null) {
			while (agency.parent != null)
				agency = agency.parent;
			return agency;
		}
		throw new IllegalArgumentException("单位参数不能为空");
	}

	/**
	 * 根据ID获取根单位名
	 * 
	 * @param id
	 * @return
	 */
	public static String getRootAgencyName(String id) {
		Agency agency = getRootAgency(id);
		return agency != null ? agency.getName() : null;
	}

	/**
	 * 根据ID获取单位，并且返回该单位下所有子单位集合
	 * 
	 * @param id
	 * @return
	 */
	public static List<Agency> getAgencyChildren(String id) {
		Agency agency = getAgency(id);
		return getAgencyChildren(agency);
	}


	/**
	 * 根据ID获取单位，并且返回该单位下子单位Id集合（非所有子单位）
	 *
	 * @param id
	 * @return
	 */
	public static List<String> getAgencyChildrenIds(String id) {
		Agency agency = getAgency(id);
		return getAgencyChildrenIds(agency);
	}


    public static List<String> getAgencyAllChildrenIds(String id) {
        Agency agency = getAgency(id);
        return getAgencyAllChildrenIds(agency);
    }


	/**
	 * 返回该单位下所有子单位集合，
	 * 
	 * @param agency
	 * @return
	 */
	public static List<Agency> getAgencyChildren(Agency agency) {
		List<Agency> agencys = new ArrayList<>();
		if (agency != null) {
			for (Agency child : agency.children) {
				getAgencyAndChildren(child, agencys);
			}
		}
		return agencys;
	}



	public static List<String> getAgencyChildrenIds(Agency agency) {
		List<String> ids = new ArrayList<>();
		if (agency != null) {
			for (Agency child : agency.children) {
				ids.add(child.getId());
			}
		}
		return ids;
	}

    public static List<String> getAgencyAllChildrenIds(Agency agency) {
        List<String> ids = new ArrayList<>();
        if (agency != null) {
            for (Agency child : agency.children) {
                getAgencyAndChildrenIds(child,ids);
            }
        }
        return ids;
    }

	/**
	 * 获取该单位以及子单位的ID集合
	 * 
	 * @param id
	 * @return
	 */
	public static List<String> getAgencyAndChildrenIds(String id) {
		return getAgencyAndChildrenIds(getAgency(id));
	}

	/**
	 * 获取该单位以及子单位的ID集合
	 * 
	 * @param agency
	 * @return
	 */
	public static List<String> getAgencyAndChildrenIds(Agency agency) {
		List<String> ids = new ArrayList<>();
		if (agency != null) {
			getAgencyAndChildrenIds(agency, ids);
		}
		return ids;
	}

	private static void getAgencyAndChildrenIds(Agency agency, List<String> ids) {
		if (agency == null)
			return;
		ids.add(agency.id);
		for (Agency child : agency.children) {
			getAgencyAndChildrenIds(child, ids);
		}
	}

	/**
	 * 获取该单位以及子单位集合
	 * 
	 * @param id
	 * @return
	 */
	public static List<Agency> getAgencyAndChildren(String id) {
		Agency agency = getAgency(id);
		return getAgencyAndChildren(agency);
	}

	/**
	 * 获取该单位以及子单位集合
	 * 
	 * @param agency
	 * @return
	 */
	public static List<Agency> getAgencyAndChildren(Agency agency) {
		List<Agency> agencys = new ArrayList<>();
		if (agency != null) {
			getAgencyAndChildren(agency, agencys);
		}
		return agencys;
	}

	private static void getAgencyAndChildren(Agency agency, List<Agency> agencys) {
		if (agency == null)
			return;
		agencys.add(agency);
		for (Agency child : agency.children) {
			getAgencyAndChildren(child, agencys);
		}
	}

	@Override
	public boolean versionChangedHandle(long version) {
		initData();
		container = this;
		return true;
	}

	@Override
	public String getId() {
		return "agency_container";
	}

}
