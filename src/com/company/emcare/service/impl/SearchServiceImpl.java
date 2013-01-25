package com.company.emcare.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.emcare.dao.ActionHistDao;
import com.company.emcare.dao.VoiceDao;
import com.company.emcare.dto.PageBean;
import com.company.emcare.dto.VoiceDTO;
import com.company.emcare.model.ActionHist;
import com.company.emcare.model.Voice;
import com.company.emcare.service.SearchService;

@Service("searchService")
public class SearchServiceImpl implements SearchService {
	@Resource
	private VoiceDao voiceDao;
	@Resource
	private ActionHistDao actionHistDao;

	@Override
	public List<VoiceDTO> search(String keyword,PageBean page) {
		List<Voice> voices = voiceDao.searchVoice(keyword, page.getFirstResult(),page.getPageSize());
		List<VoiceDTO> voiceDTOList = new ArrayList<VoiceDTO>();
		for(Voice voice:voices){
			VoiceDTO voiceDTO = new VoiceDTO();
			voiceDTO.setVoice(voice);
			ActionHist ah = actionHistDao.getLastestActionHistForVoice(voice);
			if(ah!=null){
				voiceDTO.setUpdateTime(ah.getUpdateTime());
			}
			voiceDTOList.add(voiceDTO);
		}
		return voiceDTOList;
	}

}
