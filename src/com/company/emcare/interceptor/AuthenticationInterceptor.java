package com.company.emcare.interceptor;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.tomcat.util.http.Cookies;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.company.emcare.action.BaseAction;
import com.company.emcare.dao.PersonDao;
import com.company.emcare.dto.UserInfo;
import com.company.emcare.model.Person;
import com.company.emcare.service.impl.ActivateService;
import com.company.emcare.util.DESUtil;

public class AuthenticationInterceptor extends MethodFilterInterceptor{
	public static final String ACTIVATE = "activation";
	@Resource
	private PersonDao personDao;
	@Resource
	private ActivateService activateService;
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get("userInfo");
		if(userInfo!=null){
			 return invocation.invoke();  
		}
		String userLogName = activateService.checkUserCookie();
		if(userLogName!=null){
			Person person = personDao.getPersonByUserName(userLogName);
			if(person==null){
				return ACTIVATE;
			}
			userInfo = new UserInfo();
			userInfo.setPerson(person);
			userInfo.setLoginTime(new Date());
			userInfo.setUsername(person.getRealname());
			session.put("userInfo", userInfo);
		}
		if(userInfo!=null){
			return invocation.invoke();
		}
		return ACTIVATE;
	}
	
	
	
}
