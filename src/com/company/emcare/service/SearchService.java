package com.company.emcare.service;

import java.util.List;

import com.company.emcare.dto.PageBean;
import com.company.emcare.dto.VoiceDTO;

public interface SearchService {
	public List<VoiceDTO> search(String keyword,PageBean page) ;
}
