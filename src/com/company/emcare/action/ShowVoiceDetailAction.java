package com.company.emcare.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.company.emcare.dto.PageBean;
import com.company.emcare.dto.VoiceDTO;
import com.company.emcare.model.ActionHist;
import com.company.emcare.model.VoiceComment;
import com.company.emcare.service.ActionHistService;
import com.company.emcare.service.VoiceService;
@Controller
@Scope("prototype")
public class ShowVoiceDetailAction extends BaseAction{
	private Long id;
	private VoiceDTO voice;
	private Integer pageNo;
	private PageBean page;
	private String userAction;
	private List<VoiceComment> comments;
	@Resource
	private ActionHistService actionHistService;
	@Resource
	private VoiceService voiceService;
	private List<ActionHist> workflow ;
	public String execute(){
		if(id==null){
			return ERROR;
		}
		voice = voiceService.getVoiceById(id);
		workflow = actionHistService.getActionHist(voice.getVoice());
		if(voice.getVoice().getViewCount() == null){
			voice.getVoice().setViewCount(0L);
		}
		voiceService.updateViewCount(voice.getVoice());
		
		userAction="showVoicesDetail";
		page = new PageBean();
		page.setCurrentPage(getPageNo());		
		page.setPageSize(5);
		page.setRecordSize(voice.getVoice().getComments().size());
		
		comments = voiceService.listComments(page,voice.getVoice().getVoiceId());
		
		int voiceStatus = voice.getVoice().getStatus();
		if(voiceStatus == 6){
			this.setCategory(CATEGORY_CLOSE);
		}else{
			this.setCategory(CATEGORY_OPEN);
		}
		
		return SUCCESS;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public VoiceDTO getVoice() {
		return voice;
	}
	public void setVoice(VoiceDTO voice) {
		this.voice = voice;
	}
	public List<ActionHist> getWorkflow() {
		return workflow;
	}
	public void setWorkflow(List<ActionHist> workflow) {
		this.workflow = workflow;
	}
	public List<VoiceComment> getComments() {
		return comments;
	}
	public void setComments(List<VoiceComment> comments) {
		this.comments = comments;
	}
	public Integer getPageNo() {
		return pageNo!=null?pageNo:1;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public PageBean getPage() {
		return page;
	}
	public void setPage(PageBean page) {
		this.page = page;
	}
	public String getUserAction() {
		return userAction;
	}
	public void setUserAction(String userAction) {
		this.userAction = userAction;
	}
	
	
}
