package com.company.emcare.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.company.emcare.dao.BaseDao;
import com.company.emcare.dao.VoiceAssignmentDao;
import com.company.emcare.model.Person;
import com.company.emcare.model.VoiceAssignment;
@Repository("voiceAssignmentDao")
@Transactional
public class VoiceAssignmentDaoImpl extends BaseDao implements VoiceAssignmentDao {

	@Override
	public void saveVoiceAssignment(VoiceAssignment voiceAssignment) {
		getHibernateTemplate().save(voiceAssignment);
	}

	@Override
	public List<VoiceAssignment> getVoiceAssigmentsByPerson(Person person) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from com.company.emcare.model.VoiceAssignment as vam where vam.assignTo=?",person);
	}

	@Override
	public void deleteVoiceAssigment(VoiceAssignment voiceAssigments) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(voiceAssigments);
		
	}

	@Override
	public void deleteVoiceAssigment(List<VoiceAssignment> vaList) {
		getHibernateTemplate().deleteAll(vaList);
	}

}
