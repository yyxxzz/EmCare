package com.company.emcare.dto;

import com.company.emcare.util.PaginationUtil;


public class PageBean {
	private int pageSize=10;
	private int recordSize;
	private int currentPage=1;
	private String pageUrl;
	public PageBean(Class clazz){
		this.recordSize = PaginationUtil.getRecordsSize(clazz);
	}
	public PageBean(){
		
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		if(recordSize==0)
			return 1;
		return (int) Math.ceil(((double)recordSize/(double)pageSize));
	}
	public int getRecordSize() {
		return recordSize;
	}
	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getFirstResult(){
		return (currentPage-1)*pageSize;
	}
	public String getPageUrl() {
		if(pageUrl!=null&&pageUrl.contains("?")){
			if(!pageUrl.endsWith("&")&& !pageUrl.endsWith("?")) {
				pageUrl+="&";
			}
		}else if(pageUrl!=null&& !pageUrl.contains("?")) {
			pageUrl+="?";
		}
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
}
