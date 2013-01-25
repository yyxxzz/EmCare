package com.company.emcare.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.company.emcare.dao.ActionHistDao;
import com.company.emcare.dao.PersonDao;
import com.company.emcare.dao.VoiceDao;
import com.company.emcare.dao.VoiceTypeDao;
import com.company.emcare.dto.PageBean;
import com.company.emcare.dto.VoiceDTO;
import com.company.emcare.model.ActionHist;
import com.company.emcare.model.Person;
import com.company.emcare.model.Role;
import com.company.emcare.model.Voice;
import com.company.emcare.model.VoiceType;
import com.company.emcare.service.UserService;
import com.company.emcare.service.VoiceService;
import com.company.emcare.util.MailUtil;
@Controller
@Scope("prototype")
public class VoiceAdminAction extends BaseAction{
	@Resource
	private PersonDao personDao;
	@Resource
	private ActionHistDao actionHistDao;
	@Resource
	private VoiceService voiceService;
	private Voice voice;
	private Long voiceTypeId;
	@Resource
	private UserService userService;
	private List<Person> LMs;
	private List<VoiceType> voiceCategories;
	@Resource
	private VoiceTypeDao voiceTypeDao;
	private List<VoiceDTO> voices;
	private PageBean page;
	
	
	
	public String assgine(){
		LMs = userService.getPersonByRole(Role.ROLE_LM);
		voiceCategories = voiceTypeDao.getAllVoiceType();
		return SUCCESS;
	}
	
	private Long assignee;
	private String comment;
	
	public String assgineVoice(){
		Person person = personDao.getUser(assignee);
		if(voice.getAssignment()!=null){
			return ERROR;
		}
		voiceService.resoveVoiceCategory(voice, voiceTypeId);
		voiceService.assignVoiceTo(voice, person, comment);
		ActionHist ah = ActionHist.constructActionHist(voice, getUserInfo().getPerson(), person, ActionHist.ASSIGN,comment);
		voiceService.changeStatus(voice, Voice.STATUS_ASSIGNED);
		actionHistDao.addActionHist(ah);
		//TODO: Send notification mail to assignee
		Map<Object,Object> context  = new HashMap<Object, Object>();
		context.put("assignee",person );
		context.put("user", getUserInfo().getPerson());
		context.put("ah", ah);
		HttpServletRequest request =  ServletActionContext.getRequest();
		String baseUrl =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		context.put("basePath", baseUrl);
		final String mailTemplatePath = ServletActionContext.getServletContext().getRealPath("/mail_template/");
		MailUtil.setMailTemplatePath(mailTemplatePath);
		String mailBody = MailUtil.renderMail(context, "assignment_notification.vm");
		if(mailBody!=null){
			try{
			MailUtil.sendMail(person.getEmail(), null, "Notification from EmCare", mailBody);
			}catch (Exception e) {
				log.error(e);
			}
		}else{
			log.info("error ocurred in rending mail, please check template");
		}
		return SUCCESS;
	}
	
	public String closeVoice() throws Exception{
		if(voice.getStatus()!=Voice.STATUS_CLOSED){
			voiceService.closeVoice(voice);
			ActionHist ah =  ActionHist.constructActionHist(voice, getUserInfo().getPerson(), ActionHist.CLOSE,comment);
			actionHistDao.addActionHist(ah);
		}
//		JSONObject obj = new JSONObject();
//	    obj.put("result", true);
//	    printResponse(obj.toString());
		return SUCCESS;
	}
	
	public String reOpenVoice() throws Exception{
		if(voice.getStatus()==Voice.STATUS_CLOSED){
			voiceService.openVoice(voice);
			ActionHist ah =  ActionHist.constructActionHist(voice, getUserInfo().getPerson(), ActionHist.REOPEN,comment);
			actionHistDao.addActionHist(ah);
		}
		return SUCCESS;
	}
	
	public String rejectVoice() throws Exception{
		voiceService.rejectVoice(voice);
		ActionHist ah = ActionHist.constructActionHist(voice, getUserInfo().getPerson(),ActionHist.REJECT,comment);
	    actionHistDao.addActionHist(ah);
		return SUCCESS;
	}
	
	private void printResponse(String str) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(str);
		out.flush();
	}
	
	public String fetchVoicesAssignedToMe(){
		Long personId = getUserInfo().getPerson().getPersonId();
		page = new PageBean();
		page.setPageUrl(request.getContextPath()+"/admin/assignedToMe");
		page.setRecordSize(voiceService.getVoicesCountByAssignee(personId));
		voices = voiceService.getVoicesByAssignee(personId, page);
		return SUCCESS;
	} 
	
	public String startVoiceProgress() throws Exception{
		voiceService.startVoiceProgress(voice);
		ActionHist ah = ActionHist.constructActionHist(voice, getUserInfo().getPerson(), ActionHist.INPROGRESS);
		actionHistDao.addActionHist(ah);
		JSONObject obj = new JSONObject();
		obj.put("success", true);
		printResponse(obj.toString());
		return null;
	}
	
	public String resolveVoice() throws Exception{
		voiceService.resolveVoice(voice,comment,getUserInfo().getPerson());
		ActionHist ah = ActionHist.constructActionHist(voice, getUserInfo().getPerson(), ActionHist.RESOLVE,comment);
		actionHistDao.addActionHist(ah);
		JSONObject obj = new JSONObject();
		obj.put("success", true);
		printResponse(obj.toString());
		return null;
	}
	
	public String loadAssignPage(){
		
		return SUCCESS;
	}
	
	public String deleteVoice(){
		voiceService.deleteVoice(voice.getVoiceId());
		return comment;
		
	}

	public List<Person> getLMs() {
		return LMs;
	}


	public void setLMs(List<Person> lMs) {
		LMs = lMs;
	}

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	public Long getAssignee() {
		return assignee;
	}

	public void setAssignee(Long assignee) {
		this.assignee = assignee;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public List<VoiceType> getVoiceCategories() {
		return voiceCategories;
	}

	public void setVoiceCategories(List<VoiceType> voiceCategories) {
		this.voiceCategories = voiceCategories;
	}

	public Long getVoiceTypeId() {
		return voiceTypeId;
	}

	public void setVoiceTypeId(Long voiceTypeId) {
		this.voiceTypeId = voiceTypeId;
	}

}
