package com.paladin.framework.excel.write;

import com.paladin.common.core.ConstantsContainer;
import com.paladin.framework.utils.reflect.EntityField;

/**
 * 默认写Excel的列
 * 
 * @author TontZhou
 * 
 */
public class DefaultWriteColumn extends WriteColumn {

	private EntityField[] parentFields;
	private EntityField entityField;

	protected DefaultWriteColumn(EntityField field) {
		this(field, null);
	}

	protected DefaultWriteColumn(EntityField entityField, EntityField[] parentFields) {
		setId(entityField.getName());
		this.entityField = entityField;
		this.parentFields = parentFields;
	}

	@Override
	public Object peelData(Object data) {
		if (parentFields != null) {
			for (EntityField field : parentFields) {
				data = field.getValue(data);
			}
		}
		return entityField == null ? data : entityField.getValue(data);
	}

	@Override
	public String getEnumName(Object value) {
		if (value == null)
			return "";
		return ConstantsContainer.getTypeValue(getEnumType(), value.toString());
	}
}
