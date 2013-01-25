package com.company.emcare.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.company.emcare.action.BaseAction;
import com.company.emcare.dao.ActionHistDao;
import com.company.emcare.dao.PersonDao;
import com.company.emcare.dao.VoiceAssignmentDao;
import com.company.emcare.dao.VoiceCommentDao;
import com.company.emcare.dao.VoiceDao;
import com.company.emcare.dao.VoiceTypeDao;
import com.company.emcare.dto.PageBean;
import com.company.emcare.dto.UserInfo;
import com.company.emcare.dto.VoiceDTO;
import com.company.emcare.model.ActionHist;
import com.company.emcare.model.Person;
import com.company.emcare.model.Voice;
import com.company.emcare.model.VoiceComment;
import com.company.emcare.model.VoiceType;
import com.company.emcare.model.VoiceAssignment;
import com.company.emcare.service.VoiceService;
import com.company.emcare.util.DateUtil;
@Service("voiceService")
public class VoiceServiceImpl implements VoiceService{
	@Resource
	private VoiceDao voiceDao;
	@Resource
	private ActionHistDao actionHistDao;
	@Resource
	private VoiceAssignmentDao voiceAssignmentDao;
	@Resource
	private PersonDao personDao;
	@Resource
	private VoiceCommentDao voiceCommentDao;
	@Resource
	private VoiceTypeDao voiceTypeDao;
	@Override
	public List<VoiceDTO> getVoicesSortByCreateTime(PageBean page) {
		List<Voice> voices = voiceDao.getAllVoicesSortByCreateTime(page.getFirstResult(),page.getPageSize()); 
		return assembleVoiceDTO(voices);
	}
	
	private List<VoiceDTO> assembleVoiceDTO(List<Voice> voices){
		List<VoiceDTO> voiceDTOList = new ArrayList<VoiceDTO>();
		for(Voice voice:voices){
			VoiceDTO voiceDTO = new VoiceDTO();
			voiceDTO.setVoice(voice);
			ActionHist ah = actionHistDao.getLastestActionHistForVoice(voice);
			if(ah!=null){
				voiceDTO.setUpdateTime(ah.getUpdateTime());
			}
			voiceDTOList.add(voiceDTO);
		}
		return voiceDTOList;
	}

	@Override
	public List<VoiceDTO> getVoicesSortByUpdateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VoiceDTO> getVoicesByStatus(int status) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void addNewVoice(Voice voice) {
		voiceDao.addVoice(voice);
		ActionHist actionHist = new ActionHist();
		actionHist.setActionType(ActionHist.NEW);
		actionHist.setVoice(voice);
		actionHist.setUpdateTime(voice.getSubmitTime());
		actionHistDao.addActionHist(actionHist);
	}

	@Override
	public void assignVoiceTo(Long voiceId,Long personId,String comment){
		Voice voice = voiceDao.getVoice(voiceId);
		Person person = personDao.getUser(personId);
		this.assignVoiceTo(voice, person, comment);
	}
	
	@Override
	public void assignVoiceTo(Voice voice, Person person, String comment) {
		VoiceAssignment assignment = new VoiceAssignment();
		assignment.setAssignAt(new Timestamp(System.currentTimeMillis()));
		assignment.setAssignTo(person);
		assignment.setVoice(voice);
		assignment.setComments(comment);
		voiceAssignmentDao.saveVoiceAssignment(assignment);
	}
	@Override
	public void startVoiceProgress(Voice voice){
		changeStatus(voice, Voice.STATUS_PENDING);
	}
	
	@Override
	public List<VoiceDTO> getVoicesByConditions(Map<String, Object> map,PageBean page) {
		List<VoiceDTO> voiceDTOList = new ArrayList<VoiceDTO>();
		List<Voice> voices = voiceDao.getVoiceByParameters(map, page.getFirstResult(), page.getPageSize());
			for(Voice voice:voices){
				VoiceDTO voiceDTO = new VoiceDTO();
				voiceDTO.setVoice(voice);
				ActionHist ah = actionHistDao.getLastestActionHistForVoice(voice);
				if(ah!=null){
					voiceDTO.setUpdateTime(ah.getUpdateTime());
				}
				voiceDTOList.add(voiceDTO);
		}
		return voiceDTOList;
	}

	@Override
	public List<VoiceDTO> getOpenVoice(PageBean page) {
		List<VoiceDTO> voiceDTOList = new ArrayList<VoiceDTO>();
		List<Voice> voices = voiceDao.getOpenVoice(page);
			for(Voice voice:voices){
				VoiceDTO voiceDTO = new VoiceDTO();
				voiceDTO.setVoice(voice);
				ActionHist ah = actionHistDao.getLastestActionHistForVoice(voice);
				if(ah!=null){
					voiceDTO.setUpdateTime(ah.getUpdateTime());
				}
				voiceDTOList.add(voiceDTO);
		}
		return voiceDTOList;
	}
	
	@Override
	public VoiceDTO getVoiceById(Long id) {
		Voice voice = voiceDao.getVoice(id);
		VoiceDTO voiceDTO = new VoiceDTO();
		voiceDTO.setVoice(voice);
		ActionHist ah = actionHistDao.getLastestActionHistForVoice(voice);
		voiceDTO.setUpdateTime(ah==null?voice.getSubmitTime():ah.getUpdateTime());
		return voiceDTO;
	}
	
	@Override
	public List<VoiceDTO> getVoicesByParameters(Map<String, Object> map) {
		List<Voice> voices = voiceDao.getVoiceByParameters(map);
		return assembleVoiceDTO(voices);
	}

	@Override
	public Set<Person> getPersonByRoleType(int roleType) {
		return personDao.getPersonByRoleType(roleType);
	}

	@Override
	public int getTotalByCategory(String category) {
		
		if(BaseAction.CATEGORY_OPEN.equals(category)){
    		return voiceDao.getUnClosedTotal();
    	}else if(BaseAction.CATEGORY_CLOSE.equals(category)){
    		return voiceDao.getClosedTotal();
    	}else if(BaseAction.CATEGORY_ALL.equals(category)){
    		return voiceDao.getVoiceCount();
    	}else{
    		return voiceDao.getVoiceCount();
    	}
		
	
	}

	@Override
	public int getVoiceSizeByParameters(Map<String, Object> map) {
		return voiceDao.getRecordsSizeByParameters(map);
	}

	@Override
	public List<VoiceType> getAllVoiceTypes() {
		return voiceDao.getAllVoiceTypes();
	}

	@Override
	public int getVoiceSizeByType(long id) {
		Map<String,Object> filter = new HashMap<String,Object>();
		filter.put("type.id", id);
		return voiceDao.getRecordsSizeByParameters(filter);
	}

	@Override
	public void changeStatus(Long voiceId, int status) {
		Voice voice = voiceDao.getVoice(voiceId);
		changeStatus(voice,status);
	}
	@Override
	public void changeStatus(Voice voice, int status){
		voice.setStatus(status);
		voiceDao.updateVoice(voice);
	}
	
	@Override
	public void closeVoice(Voice voice){
		changeStatus(voice, Voice.STATUS_CLOSED);
	}
	@Override
	public void closeVoice(Long voiceId){
		Voice voice = voiceDao.getVoice(voiceId);
		closeVoice(voice);
	}
	
	@Override
	public void rejectVoice(Voice voice) {
		changeStatus(voice,Voice.STATUS_REJECTED);
	}

	@Override
	public void rejectVoice(Long voiceId) {
		Voice voice = voiceDao.getVoice(voiceId);
		rejectVoice(voice);
	}
	
	@Override
	public List<Voice> getVoicesByPeriod(String start, String end) {
		// TODO Auto-generated method stub
		try {
			if (start == null || start.equals("")) {
				start = DateUtil.VERY_BEGINING;
			}
			if (end == null || end.equals("")) {
				end = DateUtil.getDateStringWithPatten(new Date(),DateUtil.DATE_FULL_FORMAT);
			}
		
			return voiceDao.getVoicesByPeriod(DateUtil.date2Timestamp(start),DateUtil.date2Timestamp(end));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteVoice(Long id) {
		// TODO Auto-generated method stub
		voiceDao.deleteVoice(id);
	}


	@Override
	public void setVoiceTypeToUnassigned(Voice voice) {		
		VoiceType voiceType =voiceDao.getVoiceTypeById(4L);
		voice.setType(voiceType);
	}
	@Override
	public List<VoiceDTO> getVoicesByAssignee(Long personId, PageBean page){
		List<Voice> voices = voiceDao.getVoicesByAssignTo(personId, page.getFirstResult(), page.getPageSize());
		return assembleVoiceDTO(voices);
	}
	@Override
	public int getVoicesCountByAssignee(Long personId){
		return voiceDao.getVoicesCountByAssignTo(personId);
	}

	@Override
	public void resolveVoice(Voice voice, String comment, Person person){
		changeStatus(voice, Voice.STATUS_RESOLVED);
    //	VoiceComment voiceComment = new VoiceComment(voice,comment,person);
	//	voiceCommentDao.addVoiceComment(voiceComment);
	}

	@Override
	public void saveVoiceComment(VoiceComment comment) {
		voiceDao.saveVoiceComment(comment);
	}

	@Override
	public void updateViewCount(Voice voice) {		
		voice.setViewCount(voice.getViewCount() + 1);
		voiceDao.updateVoice(voice);
	}

	@Override
	public List<VoiceComment> listComments(PageBean page,Long voiceId) {		
		return voiceDao.listCommentByPage(page,voiceId);
	}

	@Override
	public void resoveVoiceCategory(Voice voice, Long voiceTypeId) {
		VoiceType voiceType = voiceTypeDao.getVoiceTypeById(voiceTypeId);
		voice.setType(voiceType);
		voiceDao.updateVoice(voice);
	}

	@Override
	public void openVoice(Voice voice) {
		if(voice.getAssignment()!=null){
			voiceAssignmentDao.deleteVoiceAssigment(voice.getAssignment());
			voice.setAssignment(null);
		}
		voice.setStatus(Voice.STATUS_OPEN);
		voiceDao.updateVoice(voice);
	}

	@Override
	public Long updateVisitorCount(long currentCount) {
		
		return voiceDao.updateEmcareVisitorCount(currentCount);
	}

	@Override
	public Long getCurrentVisitorCount() {

		return voiceDao.getCurrentVisitorCount();
	}

	@Override
	@Transactional
	public Long updateVisitorCount() {
		Long currentCount = this.getCurrentVisitorCount();
		return this.updateVisitorCount(currentCount + 1);
	}

}
