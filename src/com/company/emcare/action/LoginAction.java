package com.company.emcare.action;

import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.company.emcare.dto.UserInfo;
import com.company.emcare.model.Person;
import com.company.emcare.model.Role;
import com.company.emcare.service.LoginService;
import com.company.emcare.service.UserService;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction{

	private Person person;
	private String returnInfo;
	
	@Resource
	private LoginService loginService;
	
	public String executeLogin() throws Exception {
		UserInfo userInfo = (UserInfo) session.get("userInfo");
		if(userInfo != null &&(session.get("ownRight")!=null)) {
			//if(userInfo.getPerson().getUsername().equals(person.getUsername()))
			return "loginSuccess";
		}
		if(person == null ) {
			return "login";
		}else {
			userInfo = loginService.login(person.getUsername(), person.getPassword());
			if(userInfo.getPerson() == null) {
				setReturnInfo("Sorry, your username and password are incorrect - please try again");
				return "loginError";
			}else {
				session.put("userInfo", userInfo);
				if(!userInfo.getPerson().getPersonRoles().isEmpty()){
					Iterator<Role> it = userInfo.getPerson().getPersonRoles().iterator();
					while(it.hasNext()){
						int roleType = it.next().getRoleType();
						if(roleType==Role.ROLE_SUM || roleType==Role.ROLE_SYS_ADMIN||roleType==Role.ROLE_LM){
							session.put("ownRight", true);
						}
					}
				}
				return "loginSuccess";
			}
		}
	}
	
	public String logout() {
		session.remove("userInfo");
		session.remove("ownRight");
		return "login";
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Person getPerson() {
		return person;
	}
	public void setReturnInfo(String returnInfo) {
		this.returnInfo = returnInfo;
	}
	public String getReturnInfo() {
		return returnInfo;
	}
	
}
