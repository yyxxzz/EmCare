package com.company.emcare.action;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.sf.json.JSONObject;

import com.company.emcare.dao.PersonDao;
import com.company.emcare.model.Person;
@Scope("prototype")
@Controller
public class changePasswordAction extends BaseAction {
	private String oldPassword;
	private String newPassword;
	@Resource
	private PersonDao personDao;
	public String executeChange() throws Exception {
		JSONObject json = new JSONObject();
		if(getUserInfo()==null||!isAdmin()){
			json.put("msg", "You are not authorized!");
			printJson(json.toString());
			return null;
		}
		Person person = getUserInfo().getPerson();
		if(!person.getPassword().equals(oldPassword)){
			json.put("msg", "Wrong old password!");
			printJson(json.toString());
			return null;
		}
		person.setPassword(newPassword);
		personDao.updateUser(person);
		json.put("msg", "Change password success!");
		json.put("success", true);
		printJson(json.toString());
		return null;
	}
	
	private void printJson(String json) throws IOException{
		response.setContentType("application/json");
		response.getWriter().print(json);
	}
	public String execute() throws  Exception{
		return SUCCESS;
	}
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
}
