package com.company.emcare.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.emcare.dao.ActionHistDao;
import com.company.emcare.dao.BaseDao;
import com.company.emcare.model.ActionHist;
import com.company.emcare.model.Voice;
import com.company.emcare.service.ActionHistService;
@Service("actionHistService")
public class ActionHistServiceImpl  implements ActionHistService{
	@Resource
private ActionHistDao actionHistDao;
	@Override
	public void logActionHist() {
		
		
	}
	
	public List<ActionHist> getActionHist(Voice voice){
		return actionHistDao.getActionHistForVoice(voice);
	}
	
}
