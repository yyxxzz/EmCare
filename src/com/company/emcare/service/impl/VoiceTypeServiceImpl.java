package com.company.emcare.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.emcare.dao.VoiceTypeDao;
import com.company.emcare.model.VoiceType;
import com.company.emcare.service.VoiceTypeService;

@Service("voiceTypeService")
public class VoiceTypeServiceImpl implements VoiceTypeService{
	
	@Resource
	private VoiceTypeDao voiceTypeDao;
	
	@Override
	public List<VoiceType> getAllVoiceType() {
		// TODO Auto-generated method stub
		return voiceTypeDao.getAllVoiceType();
	}

	@Override
	public void addVoiceType(VoiceType voiceType) {
		// TODO Auto-generated method stub
		voiceTypeDao.addVoiceType(voiceType);
	}

	@Override
	public void deleteVoiceType(VoiceType voiceType) {
		// TODO Auto-generated method stub
		voiceTypeDao.deleteVoiceType(voiceType);
	}

	@Override
	public void updateVoiceType(VoiceType voiceType) {
		// TODO Auto-generated method stub
		voiceTypeDao.updateVoiceType(voiceType);
	}

	@Override
	public VoiceType getVoiceTypeById(long id) {
		// TODO Auto-generated method stub
		return voiceTypeDao.getVoiceTypeById(id);
	}

	@Override
	public VoiceType getVoiceTypeByName(String trim) {
		
		return voiceTypeDao.getVoiceTypeByName(trim);
	}

}
