package com.company.emcare.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import com.company.emcare.dao.VoiceDao;
import com.company.emcare.dto.PageBean;
import com.company.emcare.dto.VoiceDTO;
import com.company.emcare.model.Voice;
import com.company.emcare.service.SearchService;
import com.company.emcare.service.VoiceService;
@Controller
@Scope("prototype")
public class ViewVoicesAction extends BaseAction{

	private static final long serialVersionUID = -5356707975732462049L;
	@Resource
	private VoiceService voiceService;
	@Resource 
	private VoiceDao voiceDao;
	private List<VoiceDTO> voices;
	private PageBean page;
	private Integer pageNo;
	private String userAction;
	public String execute(){
		userAction="viewVoices";
		page = new PageBean();
		page.setRecordSize(voiceDao.getVoiceCount());
		page.setCurrentPage(getPageNo());
		if(category==null||category.isEmpty()){
			voices = voiceService.getVoicesSortByCreateTime(page);
			category="all";
		}
		else{
			Map<String,Object> args = new HashMap<String,Object>();
			if("open".equals(category)){
				voices = voiceService.getOpenVoice(page);
			}else if("closed".equals(category)){
				args.put("status", Voice.STATUS_CLOSED);
				page.setRecordSize(voiceDao.getRecordsSizeByParameters(args));
				voices = voiceService.getVoicesByConditions(args, page);
			}else{
				voices = voiceService.getVoicesSortByCreateTime(page);
			}
			
		}
		return SUCCESS;
	}
	
	//search key words
	private String q;
	@Resource
	private SearchService searchService;

	public String executeSearch() {
		userAction = "search";
		if (q == null || q.isEmpty()) {
			q = (String) session.get("keyword");
		}
		if (q != null && !q.isEmpty()) {
			q = q.trim();
		}

		session.put("keyword", q);
		page = new PageBean();
		page.setRecordSize(voiceDao.searchHitCount(q));
		if(page.getCurrentPage() == 0){
			page.setCurrentPage(1);
		}		
		if(pageNo != null){
			page.setCurrentPage(pageNo);
		}
		
		voices = searchService.search(q, page);
		return SUCCESS;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}
	
	public List<VoiceDTO> getVoices() {
		return voices;
	}
	public void setVoices(List<VoiceDTO> voices) {
		this.voices = voices;
	}
	public PageBean getPage() {
		return page;
	}
	public void setPage(PageBean page) {
		this.page = page;
	}
	public int getPageNo() {
		return pageNo!=null?pageNo:1;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public String getUserAction() {
		return userAction;
	}
	public void setUserAction(String userAction) {
		this.userAction = userAction;
	}
	
	
	
}
