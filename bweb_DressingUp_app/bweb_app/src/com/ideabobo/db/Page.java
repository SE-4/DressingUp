package com.ideabobo.db;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

public class Page {
	public Object model;
	// 当前页
	public Integer pageNo;
	// 每页显示数量
	public Integer pageSize;
	// 数据
	public ArrayList rows;
	// 总共条数
	public Integer total;
	// 总页数
	public Integer totalPage;
	
	public List footer;
	
	public Object getModel() {
		return model;
	}
	public void setModel(Object model) {
		this.model = model;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public ArrayList getRows() {
		return rows;
	}
	public void setRows(ArrayList rows) {
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List getFooter() {
		return footer;
	}
	public void setFooter(List footer) {
		this.footer = footer;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	
	
}
