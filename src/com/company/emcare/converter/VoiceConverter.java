package com.company.emcare.converter;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.util.StrutsTypeConverter;

import com.company.emcare.dao.VoiceDao;
import com.company.emcare.model.Voice;


@SuppressWarnings("rawtypes")
public class VoiceConverter extends StrutsTypeConverter{
   
	@Resource
	private VoiceDao voiceDao;
	
	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		
		if(arg1[0] == null || arg1[0].trim().equals("")){
			return null;
		}else{
			Long id = Long.parseLong(arg1[0]);
			Voice voice = voiceDao.getVoice(id);		
			return voice;
		}		
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {

		return null;
	}

}
