package com.paladin.health.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.framework.spring.SpringContainer;
import com.paladin.framework.utils.EnumUtil;
import com.paladin.health.library.index.Item;
import com.paladin.health.library.index.ItemType;
import com.paladin.health.library.index.item.CategoryItem;
import com.paladin.health.library.index.item.ItemStandard;
import com.paladin.health.library.index.item.ItemValueDefinition;
import com.paladin.health.library.index.item.ItemValueDefinition.InputType;
import com.paladin.health.library.index.item.ItemValueDefinition.ValueType;
import com.paladin.health.library.index.item.StandardItem;
import com.paladin.health.model.index.IndexItem;
import com.paladin.health.model.index.IndexItemStandard;
import com.paladin.health.model.index.IndexItemValueDefinition;
import com.paladin.health.service.index.IndexItemService;
import com.paladin.health.service.index.IndexItemStandardService;
import com.paladin.health.service.index.IndexItemValueDefinitionService;

@Component
public class IndexContainer implements SpringContainer {

	@Autowired
	private IndexItemService itemService;
	@Autowired
	private IndexItemValueDefinitionService itemValueDefinitionService;
	@Autowired
	private IndexItemStandardService itemStandardService;

	private List<Item> roots;
	private Map<String, StandardItem> standardItemMap;
	private List<StandardItem> standardItemList;

	@Override
	public boolean initialize() {

		List<IndexItemValueDefinition> indexItemValueDefinitions = itemValueDefinitionService.findAll();
		List<IndexItemStandard> indexItemStandards = itemStandardService.findAll();
		List<IndexItem> indexItems = itemService.findAll();

		HashMap<String, List<ItemStandard>> indexItemStandardMap = new HashMap<>();

		// 初始化值定义与标准值得Map关系
		for (IndexItemStandard indexItemStandard : indexItemStandards) {
			ItemStandard itemStandard = new ItemStandard();
			itemStandard.setKey(indexItemStandard.getStandardKey());
			itemStandard.setName(indexItemStandard.getName());

			String valueDefinitionId = indexItemStandard.getValueDefinitionId();
			List<ItemStandard> itemStandardList = indexItemStandardMap.get(valueDefinitionId);
			if (itemStandardList == null) {
				itemStandardList = new ArrayList<>();
				indexItemStandardMap.put(valueDefinitionId, itemStandardList);
			}
			itemStandardList.add(itemStandard);
		}

		HashMap<String, ItemValueDefinition> itemValueDefinitionMap = new HashMap<>();

		// 初始化值定义
		for (IndexItemValueDefinition indexItemValueDefinition : indexItemValueDefinitions) {

			String type = indexItemValueDefinition.getInputType();

			ItemValueDefinition itemValueDefinition = null;
			if (EnumUtil.equals(type, InputType.INPUT)) {
				itemValueDefinition = new ItemValueDefinition(InputType.INPUT);
				itemValueDefinition.setUnit(indexItemValueDefinition.getUnit());
				itemValueDefinition.setTemplate(indexItemValueDefinition.getTemplate());
				itemValueDefinition.setValueType(Enum.valueOf(ValueType.class, indexItemValueDefinition.getValueType()));

			} else if (EnumUtil.equals(type, InputType.SELECT)) {
				itemValueDefinition = new ItemValueDefinition(InputType.SELECT);
				itemValueDefinition.setSingle(indexItemValueDefinition.getIsSingle() == 1);
				itemValueDefinition.addStandards(indexItemStandardMap.get(indexItemValueDefinition.getId()));
			}

			if (itemValueDefinition != null) {
				itemValueDefinitionMap.put(indexItemValueDefinition.getItemId(), itemValueDefinition);
			}
		}

		Map<String, Item> itemMap = new HashMap<>();
		List<Item> itemList = new ArrayList<>();
		standardItemMap = new HashMap<>();

		for (IndexItem indexItem : indexItems) {

			String type = indexItem.getItemType();
			Item item = null;
			if (EnumUtil.equals(type, ItemType.CATEGORY)) {
				item = new WrapCategoryItem(indexItem);
			} else if (EnumUtil.equals(type, ItemType.STANDARD)) {
				String key = indexItem.getItemKey();
				ItemValueDefinition itemValueDefinition = itemValueDefinitionMap.get(indexItem.getId());
				StandardItem standardItem = standardItemMap.get(key);
				if (standardItem == null) {
					standardItem = new WrapStandardItem(indexItem);
					standardItem.setItemValueDefinition(itemValueDefinition);
					standardItemMap.put(standardItem.getKey(), standardItem);

					item = standardItem;
				} else {
					// 相同key的选择标准项可以合并，但是输入标准项不能合并，也不覆盖，默认第一个
					if (itemValueDefinition.getInputType() == InputType.SELECT) {
						standardItem.getItemValueDefinition().addStandards(itemValueDefinition.getStandards());
					}
				}
			}

			if (item != null) {
				itemMap.put(indexItem.getId(), item);
				itemList.add(item);
			}
		}

		roots = new ArrayList<>();

		for (Item item : itemList) {
			String parentId = null;
			if (item instanceof WrapCategoryItem) {
				parentId = ((WrapCategoryItem) item).parentId;
			} else if (item instanceof WrapStandardItem) {
				parentId = ((WrapStandardItem) item).parentId;
			}

			if (parentId != null) {
				Item parentItem = itemMap.get(parentId);
				parentItem.addChild(item);
				item.setParent(parentItem);
			} else {
				roots.add(item);
			}
		}

		standardItemList = new ArrayList<>();
		for (IndexItem indexItem : indexItems) {
			StandardItem item = standardItemMap.get(indexItem.getItemKey());
			if (item != null && !standardItemList.contains(item)) {
				standardItemList.add(item);
			}
		}

		return true;
	}

	/**
	 * 获取指标标准项
	 * 
	 * @param key
	 * @return
	 */
	public StandardItem getStandardItem(String key) {
		return standardItemMap.get(key);
	}

	/**
	 * 获取指标标准项
	 * 
	 * @return
	 */
	public List<StandardItem> getStandardItems() {
		return new ArrayList<StandardItem>(standardItemList);
	}

	private static class WrapCategoryItem extends CategoryItem {
		private String parentId;

		private WrapCategoryItem(IndexItem indexItem) {
			this.id = indexItem.getId();
			this.name = indexItem.getName();
			this.parentId = indexItem.getParentId();
		}
	}

	private static class WrapStandardItem extends StandardItem {
		private String parentId;

		private WrapStandardItem(IndexItem indexItem) {
			super(indexItem.getItemKey());
			this.id = indexItem.getId();
			this.name = indexItem.getName();
			this.parentId = indexItem.getParentId();
		}
	}

	public int order() {
		return 0;
	}

}
