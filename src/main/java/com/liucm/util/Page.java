package com.liucm.util;

import java.util.List;

public class Page<E> {

	private int curPage;
	
	private int curCol;

	private int pageSize;
	// 数据总量
	private int totalSize;
	// 页总量
	private int totalNum;

	private List<E> data;
	
	private String message;
	
	public Page() {}
	
	public Page(int curPage, int pageSize) {
		this.curPage = curPage;
		this.pageSize = pageSize;
		this.curCol = (curPage-1) * pageSize;
	}
	
	public Page(int curPage, int pageSize, int totalSize) {
		this.curPage = curPage;
		this.pageSize = pageSize;
		this.curCol = (curPage-1) * pageSize;
		this.totalSize = totalSize;
		this.totalNum = totalSize / pageSize + (totalSize % pageSize) == 0 ? 0 : 1;
	}

	public int getCurCol() {
		return curCol;
	}

	public void setCurCol(int curCol) {
		this.curCol = curCol;
	}

	public void next() {
		if ((this.curPage + 1) < this.totalNum) {
			this.curPage++;
			this.curCol = (curPage-1) * pageSize;
		}
	}

	public void last() {
		if ((this.curPage - 1) > 0) {
			this.curPage--;
			this.curCol = (curPage-1) * pageSize;
		}
	}

	public int getCurPage() {
		return curPage;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
		this.totalNum = (this.totalSize / this.pageSize) + ((totalSize % this.pageSize) == 0 ? 0 : 1);
	}

	public int getTotalNum() {
		return totalNum;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		if(null != data) {
			this.data = data;
		}else {
			this.message="查询数据为空";
		}
	}

}
