package com.paladin.health.library.index.item;

import java.util.List;

import com.paladin.health.library.Relation;
import com.paladin.health.library.index.Item;
import com.paladin.health.library.index.ItemType;
import com.paladin.health.library.index.item.ItemValueDefinition.InputType;

/**
 * 标准项目，实际作用项目
 * 
 * @author TontoZhou
 * @since 2018年4月20日
 */
public class StandardItem extends Item {

	protected String key;

	protected ItemValueDefinition itemValueDefinition;

	public StandardItem(String key) {
		this.key = key;
	}

	@Override
	public final ItemType getItemType() {
		return ItemType.STANDARD;
	}

	public String getKey() {
		return key;
	}

	public ItemValueDefinition getItemValueDefinition() {
		return itemValueDefinition;
	}

	public void setItemValueDefinition(ItemValueDefinition itemValueDefinition) {
		this.itemValueDefinition = itemValueDefinition;
	}

	public String toContent(Relation relation, String... values) {
		StringBuilder sb = new StringBuilder();
		if (itemValueDefinition.inputType == InputType.INPUT) {

			if (itemValueDefinition.template != null) {
				if (values != null && values.length > 0) {
					String temp = itemValueDefinition.template;
					for (int i = 0; i < values.length; i++) {
						temp = temp.replaceAll("\\{" + i + "\\}", values[0]);
					}
					sb.append(temp);
				} else {
					sb.append(itemValueDefinition.template.replaceAll("\\{\\d{1}\\}", "______"));
				}
			} else {
				String unit = itemValueDefinition.unit;
				if (unit == null) {
					unit = "";
				}

				if (values != null && values.length > 0) {
					for (int i = 0; i < values.length; i++) {
						sb.append(values[i]).append(unit).append(",");
					}
					sb.deleteCharAt(sb.length() - 1);
				} else {
					sb.append("______").append(unit);
				}
			}

		} else if (itemValueDefinition.inputType == InputType.SELECT) {

			List<ItemStandard> standards = itemValueDefinition.getStandards();
			if (standards.size() > 0) {
				if (values == null || values.length == 0) {

					for (ItemStandard standard : standards) {
						sb.append(standard.name).append(",");
					}

					sb.deleteCharAt(sb.length() - 1);
				} else {
					int c = 0;
					for (ItemStandard standard : standards) {
						String skey = standard.key;
						for (String v : values) {
							if (skey.equals(v)) {
								sb.append(standard.name).append(",");
								c++;
								break;
							}
						}
					}

					if (c > 0) {
						sb.deleteCharAt(sb.length() - 1);
					}
				}
			}
		}

		String vs = sb.toString();

		if (relation == null) {
			return getName() + ":" + vs;
		} else {
			if (relation == Relation.EQUAL) {
				return getName() + "=" + vs;
			} else if (relation == Relation.NOT_EQUAL) {
				return getName() + "!=" + vs;
			} else if (relation == Relation.IN) {
				return getName() + "在[" + vs + "]之中";
			} else if (relation == Relation.HAVE) {
				return getName() + "包含[" + vs + "]";
			} else if (relation == Relation.BETWEEN) {
				return getName() + "在[" + vs + "]之中（不包含边界）";
			} else if (relation == Relation.BETWEEN_EQUAL) {
				return getName() + "在[" + vs + "]之中（包含边界）";
			} else if (relation == Relation.GREAT) {
				return getName() + ">" + vs;
			} else if (relation == Relation.GREAT_EQUAL) {
				return getName() + ">=" + vs;
			} else if (relation == Relation.LESS) {
				return getName() + "<" + vs;
			} else if (relation == Relation.LESS_EQUAL) {
				return getName() + "<=" + vs;
			}
		}

		return null;
	}

	public String toRelationContent(Relation relation, String... values) {

		StringBuilder sb = new StringBuilder(getName());

		sb.append(":");

		if (itemValueDefinition.inputType == InputType.INPUT) {

			for (int i = 0; i < values.length; i++) {

				if (relation == Relation.EQUAL) {
					// TODO
				}
			}

			if (itemValueDefinition.template != null) {

				if (values != null && values.length > 0) {
					sb.append(itemValueDefinition.template.replace("{?}", values[0]));
				} else {
					sb.append(itemValueDefinition.template.replace("{?}", "______"));
				}
			}

			if (itemValueDefinition.unit != null) {
				sb.append(itemValueDefinition.unit);
			}

		} else if (itemValueDefinition.inputType == InputType.SELECT) {

			List<ItemStandard> standards = itemValueDefinition.getStandards();
			if (standards.size() > 0) {
				if (values == null || values.length == 0) {

					for (ItemStandard standard : standards) {
						sb.append(standard.name).append(",");
					}

					sb.deleteCharAt(sb.length() - 1);
				} else {

					int c = 0;
					for (ItemStandard standard : standards) {
						String skey = standard.key;
						for (String v : values) {
							if (skey.equals(v)) {
								sb.append(standard.name).append(",");
								c++;
								break;
							}
						}
					}

					if (c > 0) {
						sb.deleteCharAt(sb.length() - 1);
					}
				}
			}
		}

		return sb.toString();
	}
}
