package tk.mybatis.mapper.common.join;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import tk.mybatis.mapper.provider.JoinSelectProvider;

public interface SelectExampleJoinMapper<T, J> extends JoinMapper<T, J> {
		
	 /**
     * 条件查询结果
     *
     * @return
     */
    @SelectProvider(type = JoinSelectProvider.class, method = "dynamicSQL")
    List<T> selectJoinByExample(Object example);
	
}
