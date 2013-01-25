package com.company.emcare.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.company.emcare.dao.ActionHistDao;
import com.company.emcare.dao.BaseDao;
import com.company.emcare.model.ActionHist;
import com.company.emcare.model.Person;
import com.company.emcare.model.Voice;
@Repository("actionHistDao")
@Transactional
public class ActionHistDaoImpl extends BaseDao implements ActionHistDao {

	@Override
	public List<ActionHist> getActionHistForVoice(Voice voice) {
		return getHibernateTemplate().find("from com.company.emcare.model.ActionHist ah where ah.voice=? order by ah.updateTime",voice);
	}
	
	public ActionHist getLastestActionHistForVoice(Voice voice){
		
		List<ActionHist> result= getHibernateTemplate().find("from com.company.emcare.model.ActionHist ah where ah.voice=? order by ah.updateTime desc",voice);
		if(!result.isEmpty())
			return result.get(0);
		return null;
	}
	
	public void addActionHist(ActionHist hist) {
		getHibernateTemplate().save(hist);
	}

	@Override
	public List<ActionHist> getActionHistByPerson(Person person) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from com.company.emcare.model.ActionHist ah where ah.minorPerson=?",person);
		
	}

	@Override
	public void deleteActionHista(List<ActionHist> actionHists) {
		// TODO Auto-generated method stub
		getHibernateTemplate().deleteAll(actionHists);
	}
	
	
}
