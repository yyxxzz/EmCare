package com.company.emcare.dao.impl;

import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.company.emcare.dao.BaseDao;
import com.company.emcare.dao.VoiceDao;
import com.company.emcare.dto.PageBean;
import com.company.emcare.model.ActionHist;
import com.company.emcare.model.EmCareInfo;
import com.company.emcare.model.Voice;
import com.company.emcare.model.VoiceAssignment;
import com.company.emcare.model.VoiceComment;
import com.company.emcare.model.VoiceType;

@Repository(value = "voiceDao")
public class VoiceDaoImpl extends BaseDao implements VoiceDao {

	@Override
	public void addVoice(Voice voice) {
		getHibernateTemplate().save(voice);
	}

	@Override
	@Transactional
	public void updateVoice(Voice voice) {
		getHibernateTemplate().update(voice);

	}

	@Override
	public void deleteVoice(Long id) {
		List<ActionHist> list = getHibernateTemplate().find("from com.company.emcare.model.ActionHist ah where ah.voice=?", getVoice(id));
		getHibernateTemplate().delete(list.get(0));
		getHibernateTemplate().delete(getVoice(id));

	}

	@Override
	public Voice getVoice(Long id) {
		return getHibernateTemplate().get(Voice.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Voice> getAllVoicesSortByCreateTime(int firstResult,
			int maxResult) {
		return getSession().createCriteria(Voice.class)
				.addOrder(Order.desc("submitTime")).setFirstResult(firstResult)
				.setMaxResults(maxResult).list();

	}
	/**
	 * 
	 * key "voiceId" "title" "startTime" "endTime" "status" "type" "assignTo"
	 * ........
	 */
	@Override
	public List<Voice> getVoiceByParameters(Map<String, Object> map) {
		return this.getHibernateTemplate().findByCriteria(
				constructCriteria(map).addOrder(Order.desc("submitTime")));
	}

	private DetachedCriteria constructCriteria(Map<String, Object> map) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Voice.class);
		for (String key : map.keySet()) {
			if (key.equals("voiceId")) {
				criteria.add(Restrictions.eq("voiceId", map.get("voiceId")));
			}
			if (key.equals("title")) {
				criteria.add(Restrictions.like("title", "%" + map.get("title")
						+ "%"));
			}
			if (key.equals("startTime")) {
				criteria.add(Restrictions.ge("submitTime",map.get("startTime")));
			}
			if(key.equals("endTime")) {
				criteria.add(Restrictions.le("submitTime",map.get("endTime")));
			}
			if (key.equals("status")) {
				criteria.add(Restrictions.eq("status", map.get("status")));
			}
			if (key.equals("type.id")) {
				criteria.add(Restrictions.eq("type.id", map.get("type.id")));
			}
			if(key.equals("type.name")){
				criteria.add(Restrictions.eq("type.name", map.get("type.name")));
			}

			if (key.equals("assignTo")) {
				criteria.createAlias("assignment.assignTo", "person", Criteria.INNER_JOIN);
				criteria.add(Restrictions.eq("person.personId",	map.get("assignTo")));
			}
		}
		return criteria;
	}
	

	/**
	 *
	 * key
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
	@Override
	public List<Voice> getVoiceByParameters(Map<String, Object> map,int firstResult, int maxResults) {
		return this.getHibernateTemplate().findByCriteria(
				constructCriteria(map).addOrder(Order.desc("submitTime")), firstResult, maxResults);
	}

	@Override
	public int getRecordsSizeByParameters(Map<String, Object> map) {
		long result = (Long) getHibernateTemplate().findByCriteria(
				constructCriteria(map).setProjection(Projections.rowCount()))
				.get(0);
		;
		return (int) result;
	}

	@Override
	public List<Voice> searchVoice(String keyword, int firstResult,
			int maxResults) {
		
		final int fr = firstResult;
		final int mr = maxResults;
		final String hql = "FROM Voice v WHERE v.title LIKE '%"+ keyword +"%'";		
		return getHibernateTemplate().execute(new HibernateCallback<List<Voice>>() {

			@Override
			public List<Voice> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createQuery(hql);
				query.setFirstResult(fr);
				query.setMaxResults(mr);
				
				return query.list();
			}			
		});
	}

	public int searchHitCount(String keyword) {
		String hql = "FROM Voice v WHERE v.title LIKE '%"+ keyword +"%'";		
		return getHibernateTemplate().find(hql).size();
	}

	private DetachedCriteria constructSearchCriteria(String keyword) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Voice.class);
		criteria.add(Restrictions.like("title", keyword, MatchMode.ANYWHERE));
		criteria.add(Restrictions.like("content", keyword, MatchMode.ANYWHERE));
		return criteria;
	}

	@Override
	public int getVoiceCount() {
		long result = (Long) getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(Voice.class).setProjection(
						Projections.rowCount())).get(0);
		return (int) result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int getUnClosedTotal() {
		
//		String hql = "select count(*) from Voice v where v.status != 5";
//		List result = getHibernateTemplate().find(hql);
//		if(result.size() > 0){
//			return (Integer) result.get(0);
//		}else{
//			return 0;
//		}
		
		try{
			List result = getHibernateTemplate().find("SELECT COUNT(*) FROM Voice v where v.status != 6");
			long total = (Long) result.get(0);
			return (int) total;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int getClosedTotal() {
		
		try{
			List result = getHibernateTemplate().find("SELECT COUNT(*) FROM Voice v where v.status = 6");
			long total = (Long) result.get(0);
			return (int) total;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public List<VoiceType> getAllVoiceTypes() {
		List<VoiceType> result = this.getHibernateTemplate().find("From com.company.emcare.model.VoiceType");
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Voice> getVoicesByPeriod(Timestamp start, Timestamp end) {
		// TODO Auto-generated method stub		
		return getSession().createCriteria(Voice.class)
				.add(Restrictions.between("submitTime", start, end))
				.addOrder(Order.asc("submitTime"))
				.list();
	}

	@Override
	public VoiceType getVoiceTypeById(long id) {
		
		return getHibernateTemplate().get(VoiceType.class, id);
	}
	@Override
	public List<Voice> getVoicesByAssignTo(final Long personId,final int firstResult,final int maxResults) {
		final String hql = "select ag.voice from com.company.emcare.model.VoiceAssignment ag where ag.assignTo.personId=?";
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Voice>>() {

			@Override
			public List<Voice> doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql).setParameter(0, personId).setFirstResult(firstResult).setMaxResults(maxResults);
				return query.list();
			}
		});
		
	}
	@Override
	public int getVoicesCountByAssignTo(Long personId){
		String hql = "select count(*) from com.company.emcare.model.VoiceAssignment ag where ag.assignTo.personId=?";
		long result =(Long) getHibernateTemplate().find(hql, personId).get(0);
		return (int)result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Voice> getOpenVoice(PageBean page) {
		final String hql = "FROM Voice v WHERE v.status != 6 order by v.submitTime desc";	
		final PageBean p = page;
		
		List<Voice> result = getHibernateTemplate().execute(new HibernateCallback<List<Voice>>() {

			@Override
			public List<Voice> doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult(p.getFirstResult());
				query.setMaxResults(p.getPageSize());
				return query.list();
			}			
		});
		
		return result;
	}

	@Override
	public void saveVoiceComment(VoiceComment comment) {
		this.getHibernateTemplate().save(comment);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VoiceComment> listCommentByPage(PageBean page, Long voiceId) {
		
		final String hql = "FROM VoiceComment v WHERE v.voice.voiceId = "+ voiceId +" order by v.commitDate desc";	
		final PageBean p = page;
		
		List<VoiceComment> result = this.getHibernateTemplate().execute(new HibernateCallback<List<VoiceComment>>() {

			@Override
			public List<VoiceComment> doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				Query query = session.createQuery(hql);
				query.setFirstResult(p.getFirstResult());
				query.setMaxResults(p.getPageSize());
				return query.list();
			}
		});
		return result;
	}

	@Override
	public Long updateEmcareVisitorCount(long currentCount) {
		
		EmCareInfo info =	(EmCareInfo) getHibernateTemplate().find("FROM EmCareInfo").get(0);
		info.setCount(currentCount);		
		getHibernateTemplate().update(info);		
		return info.getCount();
	}

	@Override
	public Long getCurrentVisitorCount() {
		
		EmCareInfo info =	(EmCareInfo) getHibernateTemplate().find("FROM EmCareInfo").get(0);
		return info.getCount();
	}
}
