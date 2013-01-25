package com.company.emcare.action;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.company.emcare.dao.VoiceDao;
import com.company.emcare.model.Voice;
import com.company.emcare.model.VoiceType;
import com.company.emcare.service.VoiceService;
import com.company.emcare.service.VoiceTypeService;
@Controller
@Scope("prototype")
public class SaveVoiceAction extends BaseAction{
	
	private Voice voice;
	@Resource
	private VoiceService voiceService;
	@Resource
	private VoiceTypeService voiceTypeService;
	
	public String execute(){
		if(checkVoice(voice)){
			voice.setAgreeCount(0L);
			voice.setDisagreeCount(0L);
			voice.setViewCount(0L);
			//default voice type
			VoiceType type = voiceTypeService.getVoiceTypeById(4L);
			voice.setType(type);
			voice.setStatus(Voice.STATUS_OPEN);
			voice.setSubmitTime(new Timestamp(System.currentTimeMillis()));
			voiceService.setVoiceTypeToUnassigned(voice);
			voiceService.addNewVoice(voice);
			return SUCCESS;
		}else{
			this.request.setAttribute("tips", "Title or Content can not be empty.");
			this.setCategory(CATEGORY_NEW);
			return INPUT;
		}
		
	}

	private boolean checkVoice(Voice voice) {
		if(voice!=null){
		if(StringUtils.isNotEmpty(voice.getTitle())&&StringUtils.isNotEmpty(voice.getContent()))
				return true;
		}
		return false;
	}

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}
	
	
}
