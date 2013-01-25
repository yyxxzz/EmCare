package com.company.emcare.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.company.emcare.dto.PageBean;
import com.company.emcare.dto.VoiceDTO;
import com.company.emcare.model.Person;
import com.company.emcare.model.Voice;
import com.company.emcare.model.VoiceComment;
import com.company.emcare.model.VoiceType;

public interface VoiceService {
	public void addNewVoice(Voice voice);
	public List<VoiceDTO> getVoicesSortByCreateTime(PageBean page);
	public List<VoiceDTO> getVoicesSortByUpdateTime();
	public List<VoiceDTO> getVoicesByStatus(int status);
	/**
	 * args key
	 * "voiceId"
	 * "title"
	 * "startTime"
	 * "endTime"
	 * "status"
	 * "type"
	 * "assignTo"
	 * "currentPage"
	 * "pageSize"
	 * ........
	 */ 
	public List<VoiceDTO> getVoicesByConditions(Map<String,Object> args,PageBean page);

	public List<VoiceDTO> getVoicesByParameters(Map<String,Object> map);

	public VoiceDTO getVoiceById(Long id);
	
	public Set<Person> getPersonByRoleType(int roleType);
	
	public int getTotalByCategory(String category);
	
	public int getVoiceSizeByParameters(Map<String,Object> map);
	public void assignVoiceTo(Long voiceId, Long personId, String comment);
	public void assignVoiceTo(Voice voice, Person person, String comment);
	public void resoveVoiceCategory(Voice voice,Long voiceTypeId);
	public void rejectVoice(Voice voice);
	public void rejectVoice(Long voiceId);
	public void changeStatus(Long voiceId,int status);
	public void deleteVoice(Long id);
	
	public List<VoiceType> getAllVoiceTypes();
	
	public int getVoiceSizeByType(long id);
	void changeStatus(Voice voice, int status);
	void closeVoice(Voice voice);
	void closeVoice(Long voiceId);
	void openVoice(Voice voice);
	public void setVoiceTypeToUnassigned(Voice voice);
	
	public List<Voice> getVoicesByPeriod(String start,String end);
	List<VoiceDTO> getVoicesByAssignee(Long personId, PageBean page);
	int getVoicesCountByAssignee(Long personId);
	void startVoiceProgress(Voice voice);
	void resolveVoice(Voice voice, String comment, Person person);
	public List<VoiceDTO> getOpenVoice(PageBean page);
	public void saveVoiceComment(VoiceComment comment);
	public void updateViewCount(Voice voice);
	public List<VoiceComment> listComments(PageBean page, Long voiceId);
	public Long updateVisitorCount(long currentCount);
	public Long getCurrentVisitorCount();
	public Long updateVisitorCount();
} 
