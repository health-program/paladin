package com.paladin.framework.common;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

public  class PageResult<T> {
	
	private int page;
	
	private int limit;
	
	private long total;
	
	private List<T> data;
	
	public PageResult() {
		
	}
	
	public PageResult(Page<T> page)
	{
		this.page = page.getPageNum();
		this.limit = page.getPageSize();
		this.total = page.getTotal();
		this.data = page;
		
		PageHelper.clearPage();
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	
	
}
