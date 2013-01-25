package com.company.emcare.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Projections;

import com.company.emcare.dto.PageBean;
import com.company.emcare.model.Voice;
import com.company.emcare.model.VoiceComment;
import com.company.emcare.model.VoiceType;

public interface VoiceDao { 
	public void addVoice(Voice voice);
	public void updateVoice(Voice voice);
	public void deleteVoice(Long id);
	public Voice getVoice(Long id);
	public List<Voice> getAllVoicesSortByCreateTime(int firstResult,int maxResult);
	public List<Voice> getVoicesByPeriod(Timestamp start,Timestamp end);
	public List<Voice> getVoiceByParameters(Map<String,Object> map);
	public List<Voice> getVoiceByParameters(Map<String,Object> map,int firstResult, int maxResults);
	int getRecordsSizeByParameters(Map<String, Object> map);
	public List<Voice> searchVoice(String keyword,int firstResult,int maxResults);
	public int searchHitCount(String keyword);
	public int getVoiceCount();
	public int getUnClosedTotal();
	public int getClosedTotal();
	public List<VoiceType> getAllVoiceTypes();
	public VoiceType getVoiceTypeById(long id);
	int getVoicesCountByAssignTo(Long personId);
	List<Voice> getVoicesByAssignTo(Long personId,int firstResult, int maxResults);
	public List<Voice> getOpenVoice(PageBean page);
	public void saveVoiceComment(VoiceComment comment);
	public List<VoiceComment> listCommentByPage(PageBean page, Long voiceId);
	public Long updateEmcareVisitorCount(long currentCount);
	public Long getCurrentVisitorCount();
}
