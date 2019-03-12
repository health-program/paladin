package tk.mybatis.mapper.provider;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultFlag;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeException;
import org.apache.ibatis.type.TypeHandler;

import com.paladin.framework.core.configuration.mybatis.CustomJoinMapper;
import com.paladin.framework.utils.reflect.ReflectUtil;

import tk.mybatis.mapper.MapperException;
import tk.mybatis.mapper.annotation.IgnoreInMultipleResult;
import tk.mybatis.mapper.annotation.JoinColumn;
import tk.mybatis.mapper.annotation.JoinTable;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.EntityTable;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.util.MsUtil;

/**
 * 连接查询实现类
 * <p>
 * 修改思路：按照EntityColumn，EntityTable思路构造字段-列关系类
 * </p>
 * 
 * @author TontoZhou
 * @since 2018年3月23日
 */
public class JoinSelectProvider extends MapperTemplate {

	public JoinSelectProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}

	/**
	 * 查询全部结果
	 *
	 * @param ms
	 * @return
	 */
	public String getJoin(MappedStatement ms) {
		final Class<?> entityClass = getEntityClass(ms);
		// 修改返回值类型为实体类型
		setResultType(ms, entityClass);
		return getBaseSql(entityClass, 1);
	}

	/**
	 * 查询全部结果
	 *
	 * @param ms
	 * @return
	 */
	public String selectJoinAll(MappedStatement ms) {
		final Class<?> entityClass = getEntityClass(ms);
		// 修改返回值类型为实体类型
		setResultType(ms, entityClass);
		return getBaseSql(entityClass, 2);
	}

	/**
	 * 根据Example查询
	 *
	 * @param ms
	 * @return
	 */
	public String selectJoinByExample(MappedStatement ms) {
		Class<?> entityClass = getEntityClass(ms);
		// 将返回值修改为实体类型
		setResultType(ms, entityClass);
		return getBaseSql(entityClass, 3);
	}

	/**
	 * 拼接基础SQL; SELECT .... FROM .... LEFT JOIN ... ON
	 * 
	 * @param entityClass
	 * @return
	 */
	private String getBaseSql(Class<?> entityClass, int type) {

		JoinEntityTable entityTable = entityTableMap.get(entityClass);
		StringBuilder sql = new StringBuilder("SELECT ");

		Set<EntityColumn> columnList = entityTable.baseEntityTable.getEntityClassColumns();
		EntityTable baseEntityTable = entityTable.baseEntityTable;
		String baseTableAlias = entityTable.baseTableAlias;
		for (EntityColumn entityColumn : columnList) {
			if (type == 1 || !entityColumn.isIgnoreInMultipleResult()) {
				sql.append(baseTableAlias).append(".").append(entityColumn.getColumn()).append(",");
			}
		}

		for (JoinEntityColumn joinEntityColumn : entityTable.joinEntityColumns) {
			if (type == 1 || !joinEntityColumn.ignoreInMultipleResult) {
				sql.append(joinEntityColumn.joinCondition.alias).append(".").append(joinEntityColumn.entityColumn.getColumn()).append(",");
			}
		}

		sql.deleteCharAt(sql.length() - 1);

		// 去除了动态表名
		sql.append(" FROM ").append(baseEntityTable.getName()).append(" ").append(baseTableAlias).append(" ");

		// 添加连接sql部分（已考虑多主键情况的连接）
		for (JoinCondition joinCondition : entityTable.joinConditions) {
			Class<?> joinClass = joinCondition.joinClass;

			// 基础表外键，如果只有一个外键指向joinClass，则可以为空，通过查找获取
			EntityColumn[] foreignColumns = joinCondition.foreignColumns;
			if (foreignColumns == null) {
				Set<EntityColumn> foreignColumnSet = baseEntityTable.getForeignColumn(joinClass);
				if (foreignColumnSet == null) {
					throw new RuntimeException("主表[class:" + entityClass + "]找不到对应连接表[class:" + joinClass + "]的外键");
				}
				foreignColumns = foreignColumnSet.toArray(new EntityColumn[foreignColumnSet.size()]);
			}

			EntityTable toJoinEntityTable = EntityHelper.getEntityTable(joinClass);
			Set<EntityColumn> pkColumns = toJoinEntityTable.getEntityClassPKColumns();

			if (pkColumns.size() != foreignColumns.length) {
				throw new RuntimeException("主表[class:" + entityClass + "]的外键与对应连接表[class:" + joinClass + "]的主键数目不一致");
			}

			String joinTableAlias = joinCondition.alias;
			sql.append(" LEFT JOIN ").append(toJoinEntityTable.getName()).append(" ").append(joinTableAlias).append(" ON ");

			EntityColumn[][] pkForeigns = null;
			if (foreignColumns.length == 1) {
				// 只有一个主键时
				pkForeigns = new EntityColumn[][] { { foreignColumns[0], pkColumns.iterator().next() } };
			} else {
				// 多个主键时，需要对应关系
				pkForeigns = new EntityColumn[pkColumns.size()][2];
				for (int i = 0; i < foreignColumns.length; i++) {
					EntityColumn foreignColumn = foreignColumns[i];
					String property = foreignColumn.getForeignProperty();
					if (property == null) {
						throw new RuntimeException("主表[class:" + entityClass + "]与对应连接表[class:" + joinClass + "]存在多列连接需要在外键属性中正确设置对应连接列属性名");
					}

					boolean find = false;
					for (EntityColumn pkColumn : pkColumns) {
						if (pkColumn.getProperty().equals(property)) {
							pkForeigns[i] = new EntityColumn[] { foreignColumn, pkColumn };
							find = true;
							break;
						}
					}

					if (!find) {
						throw new RuntimeException("主表[class:" + entityClass + "]与对应连接表[class:" + joinClass + "]存在多列连接需要在外键属性中正确设置对应连接列属性名");
					}
				}
			}

			for (EntityColumn[] pkForeign : pkForeigns) {
				sql.append(baseTableAlias).append(".").append(pkForeign[0].getColumn()).append(" = ").append(joinTableAlias).append(".")
						.append(pkForeign[1].getColumn()).append(" AND ");
			}

			sql.delete(sql.length() - 4, sql.length());
		}

		if (type == 1) {
			sql.append("<where>");
			// 当某个列有主键策略时，不需要考虑他的属性是否为空，因为如果为空，一定会根据主键策略给他生成一个值
			for (EntityColumn column : baseEntityTable.getEntityClassPKColumns()) {
				sql.append(" AND ").append(baseTableAlias).append(".").append(column.getColumnEqualsHolder());
			}
			sql.append("</where>");
		}

		if (type == 2) {
			String orderByClause = EntityHelper.getOrderByClause(baseEntityTable.getEntityClass());
			if (orderByClause.length() > 0) {
				sql.append(" ORDER BY ");
				sql.append(orderByClause);
			}
		}

		if (type == 3) {

			// 只支持基础表字段查询（example必须与entity相对应，而连接表的entity不符合设定，可后续修改）

			String exampleWhereClause = "<if test=\"_parameter != null\">" + "<where>\n" + "  <foreach collection=\"oredCriteria\" item=\"criteria\">\n"
					+ "    <if test=\"criteria.valid\">\n" + "      ${@tk.mybatis.mapper.util.OGNL@andOr(criteria)}"
					+ "      <trim prefix=\"(\" prefixOverrides=\"and |or \" suffix=\")\">\n"
					+ "        <foreach collection=\"criteria.criteria\" item=\"criterion\">\n" + "          <choose>\n"
					+ "            <when test=\"criterion.noValue\">\n" + "              ${@tk.mybatis.mapper.util.OGNL@andOr(criterion)} " + baseTableAlias
					+ ".${criterion.condition}\n" + "            </when>\n" + "            <when test=\"criterion.singleValue\">\n"
					+ "              ${@tk.mybatis.mapper.util.OGNL@andOr(criterion)} " + baseTableAlias + ".${criterion.condition} #{criterion.value}\n"
					+ "            </when>\n" + "            <when test=\"criterion.betweenValue\">\n"
					+ "              ${@tk.mybatis.mapper.util.OGNL@andOr(criterion)} " + baseTableAlias
					+ ".${criterion.condition} #{criterion.value} and #{criterion.secondValue}\n" + "            </when>\n"
					+ "            <when test=\"criterion.listValue\">\n" + "              ${@tk.mybatis.mapper.util.OGNL@andOr(criterion)} " + baseTableAlias
					+ ".${criterion.condition}\n"
					+ "              <foreach close=\")\" collection=\"criterion.value\" item=\"listItem\" open=\"(\" separator=\",\">\n"
					+ "                #{listItem}\n" + "              </foreach>\n" + "            </when>\n" + "          </choose>\n"
					+ "        </foreach>\n" + "      </trim>\n" + "    </if>\n" + "  </foreach>\n" + "</where>" + "</if>";

			sql.append(exampleWhereClause);

			sql.append("<if test=\"orderByClause != null\">");
			sql.append("ORDER BY ${orderByClause}");
			sql.append("</if>");
			String orderByClause = EntityHelper.getOrderByClause(baseEntityTable.getEntityClass());
			if (orderByClause.length() > 0) {
				sql.append("<if test=\"orderByClause == null\">");
				sql.append("ORDER BY " + orderByClause);
				sql.append("</if>");
			}

			sql.append("<if test=\"@tk.mybatis.mapper.util.OGNL@hasForUpdate(_parameter)\">");
			sql.append("FOR UPDATE");
			sql.append("</if>");
		}

		return sql.toString();
	}

	/**
	 * 基于{@link EntityTable}
	 * 
	 * @param ms
	 * @param entityClass
	 * @param extendTable
	 */
	protected void setResultType(MappedStatement ms, Class<?> entityClass) {
		JoinEntityTable entityTable = entityTableMap.get(entityClass);
		List<ResultMap> resultMaps = new ArrayList<ResultMap>();
		resultMaps.add(entityTable.getResultMap(ms.getConfiguration()));
		MetaObject metaObject = SystemMetaObject.forObject(ms);
		metaObject.setValue("resultMaps", Collections.unmodifiableList(resultMaps));
	}

	protected Map<Class<?>, Class<?>> joinBaseClassMap = new ConcurrentHashMap<Class<?>, Class<?>>();

	/**
	 * 重写获取实体类型方法，因为是连接查询表，不存在与实体类对应的表，所以不初始化EntityTable
	 */
	public Class<?> getEntityClass(MappedStatement ms) {
		String msId = ms.getId();
		if (entityClassMap.containsKey(msId)) {
			return entityClassMap.get(msId);
		} else {
			Class<?> mapperClass = MsUtil.getMapperClass(msId);

			Class<?> joinEntityType = ReflectUtil.getSuperClassArgument(mapperClass, CustomJoinMapper.class, 0);
			Class<?> baseEntityType = ReflectUtil.getSuperClassArgument(mapperClass, CustomJoinMapper.class, 1);

			entityClassMap.put(msId, joinEntityType);
			joinBaseClassMap.put(joinEntityType, baseEntityType);

			initJoinEntityTable(joinEntityType, baseEntityType, mapperHelper.getConfig());
			return joinEntityType;
		}
	}

	/**
	 * 连接查询对应的实体
	 * 
	 * @author TontoZhou
	 * @since 2018年7月16日
	 */
	private static class JoinEntityTable {
		public static final Pattern DELIMITER = Pattern.compile("^[`\\[\"]?(.*?)[`\\]\"]?$");

		private Class<?> entityClass; // 实体类
		private String baseTableAlias; // 基础表别名
		private EntityTable baseEntityTable; // 基础表实体
		private Collection<JoinEntityColumn> joinEntityColumns; // 连接的表列实体

		// 表别名
		private Set<JoinCondition> joinConditions; // 连接条件

		// resultMap对象
		private ResultMap resultMap;

		private JoinEntityTable(Class<?> entityClass, EntityTable baseEntityTable, Collection<JoinEntityColumn> joinEntityColumns) {
			this.entityClass = entityClass;
			this.baseEntityTable = baseEntityTable;
			this.joinEntityColumns = joinEntityColumns;

			int i = 1;
			joinConditions = new HashSet<>();
			baseTableAlias = "t" + i++;

			for (JoinEntityColumn column : joinEntityColumns) {

				JoinCondition joinCondition = null;
				for (JoinCondition jt : joinConditions) {
					if (jt.isSameJoinTable(column)) {
						joinCondition = jt;
					}
				}

				if (joinCondition == null) {
					joinCondition = new JoinCondition(column.tableClass, column.foreignColumns, "t" + i++);
					joinConditions.add(joinCondition);
				}

				column.joinCondition = joinCondition;
			}
		}

		/**
		 * 生成当前实体的resultMap对象
		 *
		 * @param configuration
		 * @return
		 */
		public ResultMap getResultMap(Configuration configuration) {
			if (this.resultMap != null) {
				return this.resultMap;
			}

			if (baseEntityTable.getEntityClassColumns() == null || baseEntityTable.getEntityClassColumns().size() == 0) {
				return null;
			}

			List<ResultMapping> resultMappings = new ArrayList<ResultMapping>();
			for (EntityColumn entityColumn : baseEntityTable.getEntityClassColumns()) {
				resultMappings.add(createResultMapping(entityColumn, configuration, null));
			}

			for (JoinEntityColumn entityColumn : joinEntityColumns) {
				resultMappings.add(createResultMapping(entityColumn.entityColumn, configuration, entityColumn.fieldName));
			}

			ResultMap.Builder builder = new ResultMap.Builder(configuration, "BaseMapperResultMap", this.entityClass, resultMappings, true);
			this.resultMap = builder.build();
			return this.resultMap;
		}

		private ResultMapping createResultMapping(EntityColumn entityColumn, Configuration configuration, String alias) {
			String column = entityColumn.getColumn();
			// 去掉可能存在的分隔符
			Matcher matcher = DELIMITER.matcher(column);
			if (matcher.find()) {
				column = matcher.group(1);
			}

			ResultMapping.Builder builder = new ResultMapping.Builder(configuration, alias == null ? entityColumn.getProperty() : alias, column,
					entityColumn.getJavaType());
			if (entityColumn.getJdbcType() != null) {
				builder.jdbcType(entityColumn.getJdbcType());
			}
			if (entityColumn.getTypeHandler() != null) {
				try {
					builder.typeHandler(getInstance(entityColumn.getJavaType(), entityColumn.getTypeHandler()));
				} catch (Exception e) {
					throw new MapperException(e);
				}
			}
			List<ResultFlag> flags = new ArrayList<ResultFlag>();
			if (entityColumn.isId()) {
				flags.add(ResultFlag.ID);
			}
			builder.flags(flags);
			return builder.build();
		}

		/**
		 * 实例化TypeHandler
		 * 
		 * @param javaTypeClass
		 * @param typeHandlerClass
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public <T> TypeHandler<T> getInstance(Class<?> javaTypeClass, Class<?> typeHandlerClass) {
			if (javaTypeClass != null) {
				try {
					Constructor<?> c = typeHandlerClass.getConstructor(Class.class);
					return (TypeHandler<T>) c.newInstance(javaTypeClass);
				} catch (NoSuchMethodException ignored) {
					// ignored
				} catch (Exception e) {
					throw new TypeException("Failed invoking constructor for handler " + typeHandlerClass, e);
				}
			}
			try {
				Constructor<?> c = typeHandlerClass.getConstructor();
				return (TypeHandler<T>) c.newInstance();
			} catch (Exception e) {
				throw new TypeException("Unable to find a usable constructor for " + typeHandlerClass, e);
			}
		}
	}

	/**
	 * 连接条件
	 * 
	 * @author TontoZhou
	 * @since 2018年7月16日
	 */
	private static class JoinCondition {

		private EntityColumn[] foreignColumns;
		private Class<?> joinClass;
		private String alias;

		private JoinCondition(Class<?> joinClass, EntityColumn[] foreignColumns, String alias) {
			this.joinClass = joinClass;
			this.alias = alias;
			this.foreignColumns = foreignColumns;
		}

		public boolean equals(Object obj) {
			if (obj != null) {
				if (obj == this) {
					return true;
				}

				if (obj instanceof JoinTable) {
					JoinCondition jt = (JoinCondition) obj;
					if (jt.joinClass == this.joinClass) {
						return Arrays.equals(jt.foreignColumns, foreignColumns);
					}
				}
			}

			return false;
		}

		public int hashCode() {
			int result = 1;
			result = 31 * result + joinClass.hashCode();
			if (foreignColumns != null) {
				for (EntityColumn fp : foreignColumns) {
					result = 31 * result + fp.getProperty().hashCode();
				}
			}
			return result;
		}

		public boolean isSameJoinTable(JoinEntityColumn column) {
			if (column.joinCondition != null) {
				return equals(column.joinCondition);
			} else {
				if (column.tableClass == this.joinClass) {
					return Arrays.equals(column.foreignColumns, foreignColumns);
				}
				return false;
			}
		}
	}

	/**
	 * 连接查询中连接表的实体列
	 * 
	 * @author TontoZhou
	 * @since 2018年7月16日
	 */
	private static class JoinEntityColumn {

		private EntityColumn entityColumn;
		private Class<?> tableClass;
		private String fieldName;
		private EntityColumn[] foreignColumns;
		private boolean ignoreInMultipleResult = false;
		private JoinCondition joinCondition;

		private JoinEntityColumn(Class<?> tableClass, Class<?> entityClass, String fieldName, String[] foreignProperties, EntityColumn entityColumn) {
			this.tableClass = tableClass;
			this.fieldName = fieldName;

			// 如果在多个外键指向同一个表时，需要指定使用哪个外键
			if (foreignProperties != null) {
				// 为了便于比较，对数组进行排序
				Arrays.sort(foreignProperties);

				EntityTable entityTable = EntityHelper.getEntityTable(entityClass);
				foreignColumns = new EntityColumn[foreignProperties.length];

				for (int i = 0; i < foreignProperties.length; i++) {
					EntityColumn column = entityTable.getEntityClassColumn(foreignProperties[i]);
					if (column == null) {
						throw new RuntimeException("找不到对应连接外键:" + foreignProperties[i]);
					}
					foreignColumns[i] = column;
				}
			}

			this.entityColumn = entityColumn;
		}

		public boolean equals(Object obj) {
			if (obj != null) {
				if (obj == this) {
					return true;
				}

				if (obj instanceof JoinEntityColumn) {
					JoinEntityColumn jec = (JoinEntityColumn) obj;
					return entityColumn.equals(jec.entityColumn);
				}
			}
			return false;
		}

		public int hashCode() {
			int result = 1;
			result = 31 * result + entityColumn.hashCode();
			// result = 31 * result + tableClass.hashCode();
			return result;
		}
	}

	private static final Map<Class<?>, JoinEntityTable> entityTableMap = new ConcurrentHashMap<Class<?>, JoinEntityTable>();
	private static final Map<String, JoinEntityTable> entityTableNameMap = new ConcurrentHashMap<String, JoinEntityTable>();

	/**
	 * 初始化连接查询对应实体
	 * 
	 * @param joinEntityType
	 * @param baseEntityType
	 * @param config
	 */
	public static synchronized void initJoinEntityTable(Class<?> joinEntityType, Class<?> baseEntityType, Config config) {

		if (entityTableMap.get(joinEntityType) != null) {
			return;
		}

		if (joinEntityType == baseEntityType || !baseEntityType.isAssignableFrom(joinEntityType)) {
			throw new RuntimeException("连接表查询泛型参数必须是父子类关系");
		}

		EntityHelper.initEntityNameMap(baseEntityType, config);

		EntityTable baseEntityTable = EntityHelper.getEntityTable(baseEntityType);
		if (baseEntityTable == null) {
			throw new RuntimeException("无法获取基础数据对象：" + baseEntityType);
		}

		HashSet<JoinEntityColumn> joinEntityColumns = new HashSet<>();

		// joinEntityType
		Class<?> entityType = joinEntityType;

		while (true) {
			JoinTable joinTable = entityType.getAnnotation(JoinTable.class);

			Field[] fields = entityType.getDeclaredFields();
			for (Field field : fields) {

				Class<?> joinClass = joinTable == null ? null : joinTable.getClass();
				String joinProperty = field.getName();
				String[] foreignProperty = joinTable == null ? null : joinTable.foreignProperty();
				if (foreignProperty != null && foreignProperty.length == 0) {
					foreignProperty = null;
				}

				JoinColumn column = field.getAnnotation(JoinColumn.class);
				IgnoreInMultipleResult ignoreInMultipleResult = field.getAnnotation(IgnoreInMultipleResult.class);

				// StringUtil.convertByStyle(field.getName(), style);
				if (column != null) {
					if (column.joinClass() != Object.class) {
						joinClass = column.joinClass();
					}

					if (column.joinProperty().length() != 0) {
						joinProperty = column.joinProperty();
					}

					if (column.foreignProperty().length != 0) {
						foreignProperty = column.foreignProperty();
					}
				}

				if (joinClass != null) {
					EntityHelper.initEntityNameMap(joinClass, config);
					EntityTable tagetEntityTable = EntityHelper.getEntityTable(joinClass);
					EntityColumn tagetEntityColumn = tagetEntityTable.getEntityClassColumn(joinProperty);
					if (tagetEntityColumn != null) {
						JoinEntityColumn joinEntityColumn = new JoinEntityColumn(joinClass, baseEntityType, field.getName(), foreignProperty,
								tagetEntityColumn);
						joinEntityColumn.ignoreInMultipleResult = (ignoreInMultipleResult != null);
						joinEntityColumns.add(joinEntityColumn);
					}
				}
			}

			entityType = entityType.getSuperclass();

			if (entityType == baseEntityType || entityType == Object.class) {
				break;
			}
		}

		if (joinEntityColumns.size() == 0) {
			throw new RuntimeException("不应该存在没有连接列的连接查询");
		}

		// 需要在最好new JoinEntityTable，因为在new的过程中会处理joinEntityColumns
		JoinEntityTable joinEntityTable = new JoinEntityTable(joinEntityType, baseEntityTable, joinEntityColumns);
		entityTableMap.put(joinEntityType, joinEntityTable);
		entityTableNameMap.put(joinEntityType.getCanonicalName(), joinEntityTable);
	}

}
