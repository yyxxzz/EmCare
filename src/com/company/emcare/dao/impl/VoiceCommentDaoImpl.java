package com.company.emcare.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.company.emcare.dao.BaseDao;
import com.company.emcare.dao.VoiceCommentDao;
import com.company.emcare.model.VoiceComment;
@Repository("voiceCommentDao")
public class VoiceCommentDaoImpl extends BaseDao implements VoiceCommentDao {

	@Override
	public void addVoiceComment(VoiceComment voiceComment) {
		getHibernateTemplate().save(voiceComment);
	}

}
