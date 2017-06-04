package com.shark.entity;

public class Pager {
	private int pageIndex , pageSize, totalCount, totalPage;

	public Pager() {
		this(1, 3, 0, 1);
	}

	public Pager(int pageIndex, int pageSize, int totalCount, int totalPage) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
	}

	@Override
	public String toString() {
		return "Pager [pageIndex=" + pageIndex + ", pageSize=" + pageSize + ", totalCount=" + totalCount
				+ ", totalPage=" + totalPage + "]";
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = (pageIndex>totalCount || pageIndex<1)?this.pageIndex:pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize<1)
			return;
		this.pageSize = pageSize;
		totalPage = totalCount%pageSize == 0?totalCount/pageSize :totalCount/pageSize+1;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		if (totalCount <0)
			return;
		this.totalCount = totalCount;
		totalPage = totalCount%pageSize == 0?totalCount/pageSize :totalCount/pageSize+1;
		if (pageIndex> totalPage){
			pageIndex =1;
		}
		if (totalCount == 0)
			pageIndex = 0;
	}

	public int getTotalPage() {
		return totalPage;
	}
	
}
