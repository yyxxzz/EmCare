package com.company.emcare.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.company.emcare.dto.PageBean;
import com.company.emcare.dto.VoiceDTO;
import com.company.emcare.model.Person;
import com.company.emcare.model.Role;
import com.company.emcare.model.Voice;
import com.company.emcare.model.VoiceType;
import com.company.emcare.service.VoiceService;
import com.company.emcare.util.VoiceSearchOption;

@Controller
@Scope("prototype")
public class HandleVoiceAction extends BaseAction {
	private List<VoiceDTO> voices;
	private PageBean page;
	private Integer pageNo;
	private int newVoiceSize;
	private int resolvedSize;
	private int totalVoiceSize;
	private Set<Person> personLMs;
	private List<VoiceType> voiceTypes;
	private VoiceDTO voice;
	@Resource
	private VoiceService voiceService;
	private VoiceSearchOption searchOption;
	@Override
	public String execute() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		page = new PageBean(Voice.class);
		page.setPageUrl("/EmCare/admin/handleVoice");
		map.put("status", Voice.STATUS_OPEN);
		newVoiceSize = voiceService.getVoiceSizeByParameters(map);
		page.setCurrentPage(getPageNo());
		page.setPageSize(5);
		page.setRecordSize(newVoiceSize);
		voices = voiceService.getVoicesByConditions(map, getPage());
		return "success";
	}
	
	public String listSolvedVoices() {
		Map<String,Object> map = new HashMap<String,Object>();
		page = new PageBean(Voice.class);
		page.setPageUrl("/EmCare/admin/handleVoice!listSolvedVoices");
		voices = new ArrayList<VoiceDTO>();
		
		map.put("status", Voice.STATUS_RESOLVED);
		resolvedSize = voiceService.getVoiceSizeByParameters(map);
		voices.addAll(voiceService.getVoicesByParameters(map));
		
		map.put("status", Voice.STATUS_REJECTED);
		voices.addAll(voiceService.getVoicesByParameters(map));
		resolvedSize+=voiceService.getVoiceSizeByParameters(map);
		
		page.setCurrentPage(getPageNo());
		page.setPageSize(5);
		page.setRecordSize(resolvedSize);
		return "solvedVoices";
	}
	
	public String listClosedVoices(){
		Map<String,Object> map = new HashMap<String,Object>();
		page = new PageBean(Voice.class);
		page.setPageUrl("/EmCare/admin/handleVoice!listClosedVoices");
		voices = new ArrayList<VoiceDTO>();
		
		map.put("status", Voice.STATUS_CLOSED);
		resolvedSize = voiceService.getVoiceSizeByParameters(map);
		voices.addAll(voiceService.getVoicesByParameters(map));
		
		page.setCurrentPage(getPageNo());
		page.setPageSize(5);
		page.setRecordSize(resolvedSize);
		return "closedVoices";
	}
	
	public String searchVoices() throws ParseException {
		Map<String,Object> map = new HashMap<String,Object>();
		String pageUrl = "/EmCare/admin/handleVoice!searchVoices?";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(searchOption != null) {
			if(searchOption.getVoiceId() != null) {
				if(pageUrl.length() >42 ) {
					pageUrl +="&searchOption.voiceId="+String.valueOf(searchOption.getVoiceId());
				}else {
					pageUrl +="searchOption.voiceId="+String.valueOf(searchOption.getVoiceId());
				}
				
				map.put("voiceId", searchOption.getVoiceId());
			}
			if(searchOption.getType() != null) {
				if(pageUrl.length() >42 ) {
					pageUrl +="&searchOption.type="+String.valueOf(searchOption.getType());
				}else {
					pageUrl +="searchOption.type="+String.valueOf(searchOption.getType());
				}
				map.put("type.id",searchOption.getType());
			}
			if( StringUtils.isNotEmpty(searchOption.getTitle())) {
				if(pageUrl.length() >42 ) {
					pageUrl +="&searchOption.title="+String.valueOf(searchOption.getTitle());
				}else {
					pageUrl +="searchOption.title="+String.valueOf(searchOption.getTitle());
				}
				map.put("title", searchOption.getTitle());
			}
			if(searchOption.getStatus() != 0) {
				if(pageUrl.length() >42 ) {
					pageUrl +="&searchOption.status="+String.valueOf(searchOption.getStatus());
				}else {
					pageUrl +="searchOption.status="+String.valueOf(searchOption.getStatus());
				}
				map.put("status",searchOption.getStatus());
			}
			if(searchOption.getStartTime() != null) {
				if(pageUrl.length() >42 ) {
					pageUrl +="&searchOption.startTime="+String.valueOf(searchOption.getStartTime());
				}else {
					pageUrl +="searchOption.startTime="+String.valueOf(searchOption.getStartTime());
				}
				Date startTime = sdf.parse(searchOption.getStartTime());
				map.put("startTime",startTime);
			}
			if(searchOption.getEndTime() != null) {
				if(pageUrl.length() >42 ) {
					pageUrl +="&searchOption.endTime="+String.valueOf(searchOption.getEndTime());
				}else {
					pageUrl +="searchOption.endTime="+String.valueOf(searchOption.getEndTime());
				}
				Date endTime = sdf.parse(searchOption.getEndTime());
				map.put("endTime",endTime);
			}
			if(searchOption.getAssignTo() != null) {
				if(pageUrl.length() >42 ) {
					pageUrl +="&searchOption.assignTo="+String.valueOf(searchOption.getAssignTo());
				}else {
					pageUrl +="searchOption.assignTo="+String.valueOf(searchOption.getAssignTo());
				}
				map.put("assignTo", searchOption.getAssignTo());
			}
			request.setAttribute("searchOption", searchOption);
		}
		page = new PageBean(Voice.class);
		
		totalVoiceSize = voiceService.getVoiceSizeByParameters(map);
		page.setCurrentPage(getPageNo());
		page.setPageSize(5);
		page.setRecordSize(totalVoiceSize);
		page.setPageUrl(pageUrl);
		voices = voiceService.getVoicesByConditions(map, page);
		personLMs = voiceService.getPersonByRoleType(Role.ROLE_LM);
		voiceTypes = voiceService.getAllVoiceTypes();
		return "searchVoices";
	}
	
	public String showVoiceDetail() {
		if(searchOption.getVoiceId() != null) {
			voice = voiceService.getVoiceById(searchOption.getVoiceId());
		}
		personLMs = voiceService.getPersonByRoleType(Role.ROLE_LM);
		return "showVoiceDetail";
	}

	public void setVoices(List<VoiceDTO> voices) {
		this.voices = voices;
	}

	public List<VoiceDTO> getVoices() {
		return voices;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageNo() {
		return pageNo!=null?pageNo:1;
	}

	public void setPage(PageBean page) {
		this.page = page;
	}

	public PageBean getPage() {
		return page;
	}

	public void setNewVoiceSize(int newVoiceSize) {
		this.newVoiceSize = newVoiceSize;
	}

	public int getNewVoiceSize() {
		return newVoiceSize;
	}


	public void setResolvedSize(int resolvedSize) {
		this.resolvedSize = resolvedSize;
	}

	public int getResolvedSize() {
		return resolvedSize;
	}

	public void setTotalVoiceSize(int totalVoiceSize) {
		this.totalVoiceSize = totalVoiceSize;
	}

	public int getTotalVoiceSize() {
		return totalVoiceSize;
	}

	public void setSearchOption(VoiceSearchOption searchOption) {
		this.searchOption = searchOption;
	}

	public VoiceSearchOption getSearchOption() {
		return searchOption;
	}

	public void setPersonLMs(Set<Person> personLMs) {
		this.personLMs = personLMs;
	}

	public Set<Person> getPersonLMs() {
		return personLMs;
	}

	public void setVoiceTypes(List<VoiceType> voiceTypes) {
		this.voiceTypes = voiceTypes;
	}

	public List<VoiceType> getVoiceTypes() {
		return voiceTypes;
	}

	public void setVoice(VoiceDTO voice) {
		this.voice = voice;
	}

	public VoiceDTO getVoice() {
		return voice;
	}

}
