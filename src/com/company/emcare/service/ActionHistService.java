package com.company.emcare.service;

import java.util.List;

import com.company.emcare.model.ActionHist;
import com.company.emcare.model.Voice;

public interface ActionHistService {
	public void logActionHist();

	public List<ActionHist> getActionHist(Voice voice);
}
