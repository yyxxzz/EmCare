package com.company.emcare.action;

import java.io.IOException;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.company.emcare.dao.PersonDao;
import com.company.emcare.model.Person;
import com.company.emcare.service.impl.ActivateService;
import com.company.emcare.service.impl.ActivateService.ActivationException;

@Scope("prototype")
@Controller
public class ActivateAcessRightAction extends BaseAction{
	private String username;
	private String authCode;
	@Resource
	private PersonDao personDao;
	@Resource
	private ActivateService activateService;
	public void sendActivateRequest() throws IOException{
		
		String msg = "";
		JSONObject obj = new JSONObject();
		if(StringUtils.isEmpty(username)){
			obj.put("error", true);
			msg = "Please input your 5 + 3 ID.";
			obj.put("msg", msg);
			response.getWriter().print(obj.toString());
			return;
		}
		
		final Person person = personDao.getPersonByUserName(username);
		if(person==null){
			obj.put("error", true);
			msg = "Oops! Maybe you are not the member of <a>Excellence R&D</a>, if you want to access EMCARE, please contact <a href='mailto:company@company.com'>Zhang Yi A</a> for more support.";
		}else{
			final String baseUrl =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
			final String mailTemplatePath = ServletActionContext.getServletContext().getRealPath("/mail_template/");

			try {
				activateService.sendActivateEmail(person,baseUrl,mailTemplatePath);
				msg = "Your EmCare account has been authorized! Please go to your mailbox to check it.";
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("error", true);
				msg = "Server encounter an error when sending email.";
			}
		}
		
		obj.put("msg", msg);
		response.getWriter().print(obj.toString());
	}
	
	
	public String executeActivate(){
		if(StringUtils.isEmpty(authCode)){
			return ERROR;
		}
		try {
			activateService.activateUser(authCode);
		} catch (ActivationException e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}


	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
}
