package com.company.emcare.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.company.emcare.service.VoiceService;
@Controller
@Scope("prototype")
public class DashboardAction extends BaseAction{
	private VoiceService voiceService;
	public String execute() throws Exception{
		
		return SUCCESS;
	}
}
