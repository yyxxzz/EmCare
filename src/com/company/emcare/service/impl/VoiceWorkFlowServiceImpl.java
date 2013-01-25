package com.company.emcare.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.emcare.dao.ActionHistDao;
import com.company.emcare.dto.VoiceLifeCycle;
import com.company.emcare.model.ActionHist;
import com.company.emcare.model.Voice;
import com.company.emcare.service.VoiceWorkFlowService;

@Service("voiceWorkFlowService")
public class VoiceWorkFlowServiceImpl implements VoiceWorkFlowService {
	@Resource
	private ActionHistDao actionHistDao;

	@Override
	public void getWorkFlowForVoice(Voice voice) {
		List<ActionHist> actionHistList = actionHistDao.getActionHistForVoice(voice);
		
		for(ActionHist ah:actionHistList){
			 
		}
	}

}
