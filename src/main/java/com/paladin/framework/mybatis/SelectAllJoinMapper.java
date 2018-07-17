package com.paladin.framework.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import tk.mybatis.mapper.provider.JoinSelectProvider;

public interface SelectAllJoinMapper<T, J> {
		
	 /**
     * 查询全部结果
     *
     * @return
     */
    @SelectProvider(type = JoinSelectProvider.class, method = "dynamicSQL")
    List<T> selectJoinAll();
	
}
