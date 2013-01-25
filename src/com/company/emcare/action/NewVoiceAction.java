package com.company.emcare.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class NewVoiceAction extends BaseAction{

	@Override
	public String execute(){
		
		return SUCCESS;
	}
}
