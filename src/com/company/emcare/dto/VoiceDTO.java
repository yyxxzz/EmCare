package com.company.emcare.dto;

import java.sql.Timestamp;

import com.company.emcare.model.Voice;

public class VoiceDTO {
	private Voice voice;
	private Timestamp updateTime;
	/*public Long getVoiceId() {
		return getVoice().getVoiceId();
	}
	public String getVoiceTitle() {
		return getVoice().getTitle();
	}
	public String getVoiceContent() {
		return getVoice().getContent();
	}
	public String getVoiceStatus() {
		return getVoice().getStatusString();
	}*/
	
	public Voice getVoice() {
		return voice;
	}

	public Timestamp getUpdateTime() {
		return updateTime==null?voice.getSubmitTime():updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

}
