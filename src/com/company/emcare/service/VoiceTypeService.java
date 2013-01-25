package com.company.emcare.service;

import java.util.List;

import com.company.emcare.model.VoiceType;

public interface VoiceTypeService {
	
	public List<VoiceType> getAllVoiceType();
	
	public void addVoiceType(VoiceType voiceType);
	
	public void deleteVoiceType(VoiceType voiceType);
	
	public void updateVoiceType(VoiceType voiceType);
	
	public VoiceType getVoiceTypeById(long id);

	public VoiceType getVoiceTypeByName(String trim);

}
