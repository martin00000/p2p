package com.eloan.base.query;

public class QueryObject {

	private int currentPage = 1;
	private int pageSize = 10;

	public int getStart() {
		return (currentPage - 1) * pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
