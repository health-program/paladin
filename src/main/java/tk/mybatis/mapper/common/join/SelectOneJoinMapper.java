package tk.mybatis.mapper.common.join;

import org.apache.ibatis.annotations.SelectProvider;

import tk.mybatis.mapper.provider.JoinSelectProvider;

public interface SelectOneJoinMapper<T, J> extends JoinMapper<T, J> {
		
	 /**
     * 查询全部结果
     *
     * @return
     */
    @SelectProvider(type = JoinSelectProvider.class, method = "dynamicSQL")
    T getJoin(Object pk);
	
}
