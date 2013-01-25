package com.company.emcare.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.company.emcare.dao.BaseDao;
import com.company.emcare.dao.VoiceTypeDao;
import com.company.emcare.model.VoiceType;

@Repository(value="voiceTypeDao")
public class VoiceTypeDaoImpl extends BaseDao implements VoiceTypeDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<VoiceType> getAllVoiceType() {
		// TODO Auto-generated method stub
		return getSession().createCriteria(VoiceType.class).list();
	}

	@Override
	public void addVoiceType(VoiceType voiceType) {
		// TODO Auto-generated method stub
		 getHibernateTemplate().save(voiceType);
		
	}

	@Override
	public void deleteVoiceType(VoiceType voiceType) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(voiceType);
		
	}

	@Override
	public void updateVoiceType(VoiceType voiceType) {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(voiceType);
	}

	@Override
	public VoiceType getVoiceTypeById(long id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(VoiceType.class, id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public VoiceType getVoiceTypeByName(String name) {		
		String hql = "FROM VoiceType v where v.name='" + name+ "'";		
		List result = getHibernateTemplate().find(hql);		
		if(result.size() > 0){
			VoiceType type = (VoiceType) result.get(0);			
			return type;
		}else{
			return null;
		}
	}

}
