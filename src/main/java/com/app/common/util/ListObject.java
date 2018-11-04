package com.app.common.util;

/**
 * ListObject
 * 기본 페이징 용
 * @author seongilman
 * @create 2017. 05. 09
 */
public class ListObject{

	private String sortType;
	private int count = 0;
	private int pageNo = 1;
	private int pageSize = 10;

	public String getSortType() {
		return this.sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartNum() {
		int pageSize = getPageSize();
		return getPageNo() * pageSize - pageSize + 1;
	}

	public int getEndNum() {
		return getPageNo() * getPageSize();
	}
}