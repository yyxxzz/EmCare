package com.company.emcare.dto;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.annotation.Resource;

import com.company.emcare.dao.ActionHistDao;
import com.company.emcare.model.ActionHist;
import com.company.emcare.model.Voice;

public class VoiceLifeCycle {
	private List<ActionHist> actions ;
	@Resource
	private ActionHistDao actionHistDao;
	private VoiceLifeCycle(){}
	
	public static VoiceLifeCycle getVoiceLifeCycle(Voice voice){
		
		return new VoiceLifeCycle();
	}

	private void fetchActionHist(Voice voice) {
		actions = actionHistDao.getActionHistForVoice(voice);
	}
	
	public BufferedImage generateWorkFlow(){
		
		return null;
	}
	
}
