package com.company.emcare.action;

import java.io.IOException;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.company.emcare.dao.VoiceDao;
import com.company.emcare.model.Person;
import com.company.emcare.model.Voice;

@Controller
@Scope("prototype")
public class VoteVoiceAction extends BaseAction{

	private Voice voice;
	private Person person;
	
	@Resource
	private VoiceDao voiceDao;
	
	public void agree() throws Exception{
		
		voice.setAgreeCount(voice.getAgreeCount() + 1);
		voiceDao.updateVoice(voice);
		
		JSONObject obj = new JSONObject();
		obj.put("agree", voice.getAgreeCount());
		obj.put("voteTotal", voice.getAgreeCount() + voice.getDisagreeCount());
		
		response.getWriter().print(obj.toString());
	}
	
	public void disagree() throws Exception{
				
		voice.setDisagreeCount(voice.getDisagreeCount() + 1);
		voiceDao.updateVoice(voice);
		
		JSONObject obj = new JSONObject();
		obj.put("disagree", voice.getDisagreeCount());
		obj.put("voteTotal", voice.getAgreeCount() + voice.getDisagreeCount());
		
		response.getWriter().print(obj.toString());
	}

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
