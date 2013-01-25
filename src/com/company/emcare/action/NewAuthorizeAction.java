package com.company.emcare.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.company.emcare.service.impl.ActivateService;

@Controller
@Scope("prototype")
public class NewAuthorizeAction extends BaseAction{

	@Resource
	private ActivateService activateService;
	
	@Override
	public String execute(){
		
		if(hasOurCookie()){
			return INPUT;
		}else{
			return SUCCESS;
		}
	}

	private boolean hasOurCookie() {

		String cookie = activateService.checkUserCookie();		
		return cookie == null? false:true;
	}
}
