package com.xu.common.page;

import java.util.List;

public class PageVO<T> {
	private int pageSize=10;//分页大小
	private int curPage=1;//当前页
	private int totalPage;//总页数
	private int totalRecord;//总记录数
	private int offset;//查询的起始位置
	private int end;//查询结束位置，用于oracle分页
	
	// 分页显示相关属性
	private long pageNum = 10;//页码最多显示10条
	private long pageStart;
	private long pageEnd;
	
	public long getPageNum() {
		return pageNum;
	}

	public void setPageNum(long pageNum) {
		this.pageNum = pageNum;
	}

	public long getPageStart() {
		return pageStart;
	}

	public void setPageStart(long pageStart) {
		this.pageStart = pageStart;
	}

	public long getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(long pageEnd) {
		this.pageEnd = pageEnd;
	}

	
		
	
	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	private List<T> data;//数据列表

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if(pageSize<1){
			pageSize=10;
		}
		this.pageSize = pageSize;
		this.setCurPage(this.curPage);//重新设置当前业务，防止先调用setCurPage导致偏移位置数据 
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
        
		if(curPage<1){
			curPage=1;
		}
		
		this.curPage = curPage;
		this.offset=(this.curPage-1)*this.pageSize;
		this.end=this.curPage*this.pageSize;
		
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		//计算总页数
		this.totalPage=this.totalRecord/this.pageSize;
		if(this.totalRecord%this.pageSize!=0){
			this.totalPage++;
		}
		
		// 分页显示相关算法
		if (curPage <= pageNum / 2 + 1) {
			pageStart = 1;
			pageEnd = pageNum;
		} else if (curPage > pageNum / 2 + 1) {
			pageStart = curPage - pageNum / 2;
			pageEnd = curPage + pageNum / 2 - 1;
		}
		// 对pageEnd 进行校验，并重新赋值
		if (pageEnd > this.totalPage) {
			pageEnd = this.totalPage;
		}
		if (pageEnd <= pageNum) {// 当不足pageNum数目时，要全部显示，所以pageStart要始终置为1
			pageStart = 1;
		}
				
	}

	public int getOffset() {
		return offset;
	}

	@Override
	public String toString() {
		return "PageVO [pageSize=" + pageSize + ", curPage=" + curPage
				+ ", totalPage=" + totalPage + ", totalRecord=" + totalRecord
				+ ", offset=" + offset + ", end=" + end + ", data=" + data
				+ "]";
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	
}
