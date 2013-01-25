package com.company.emcare.service.impl;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.company.emcare.dao.PersonDao;
import com.company.emcare.model.Person;
import com.company.emcare.util.DESUtil;
import com.company.emcare.util.MailUtil;

@Service
public class ActivateService {
	public static String COOKIE_AUTH_KEY="INTRA_EM_cookie";
	@Resource
	private PersonDao personDao;
	private String generateActivateCode(Person person){
		String username = person.getUsername();
		return DESUtil.encrypt(username+"_"+System.currentTimeMillis());
	}
	
	public void sendActivateEmail(Person person, String baseUrl, String mailTemplatePath) throws Exception{
		Map<Object,Object> context  = new HashMap<Object, Object>();
		
		context.put("user", person);
		context.put("basePath", baseUrl);
		context.put("code", generateActivateCode(person));
		
		MailUtil.setMailTemplatePath(mailTemplatePath);
		String mailBody = MailUtil.renderMail(context, "activate_template.vm");		
		MailUtil.sendMail(person.getEmail(), null, "Complete Activation on EmCare", mailBody);
	}
	
	public void activateUser(String authCode) throws ActivationException{
		if(checkUserCookie()!=null)
			return;
		String decode = DESUtil.decrypt(authCode);
		String username = decode.substring(0, decode.lastIndexOf("_"));
		Person person = personDao.getPersonByUserName(username);
		if(person==null){
			throw new ActivationException();
		}
		writeAuthInfoToCookie(person);
	}
	
	private void writeAuthInfoToCookie(Person person){
		Cookie cookie = new Cookie(COOKIE_AUTH_KEY, DESUtil.encrypt(person.getUsername()));
		cookie.setMaxAge(60*60*24*360);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addCookie(cookie);
	}
	
	public String checkUserCookie(){
		HttpServletRequest request = ServletActionContext.getRequest();  
		Cookie[] cookies = request.getCookies();
		if(cookies!=null)
		for(Cookie cookie:cookies){
			if(ActivateService.COOKIE_AUTH_KEY.equals(cookie.getName())){
				return DESUtil.decrypt(cookie.getValue());
			}
		}
		return null;
	}
	
	public static class ActivationException extends Exception{

		public ActivationException() {
			super();
		}

		public ActivationException(String message, Throwable cause) {
			super(message, cause);
		}

		public ActivationException(String message) {
			super(message);
		}

		public ActivationException(Throwable cause) {
			super(cause);
		}
		
	}
}
