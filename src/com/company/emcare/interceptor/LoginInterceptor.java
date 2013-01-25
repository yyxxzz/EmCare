package com.company.emcare.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.company.emcare.dto.UserInfo;

public class LoginInterceptor extends MethodFilterInterceptor{
	 @Override
	    protected String doIntercept(ActionInvocation invocation) throws Exception {
	        HttpServletRequest request = ServletActionContext.getRequest();  
	       Map<String, Object> session = ActionContext.getContext().getSession();
	        String backLink = request.getRequestURL().toString();
	        if(checkRequestURL(backLink)){
	        	session.put("_backLink", backLink);
	        }
	        UserInfo userInfo = (UserInfo) session.get("userInfo");
	        if (userInfo != null&& (session.get("ownRight")!=null))  
	            return invocation.invoke();  
	        else {  
	            return Action.INPUT;  
	        }  
	    }
	    
	    private boolean checkRequestURL(String backLink){
	    	int endSuffixPos = backLink.lastIndexOf('?');
	    	String action = backLink.substring(0, endSuffixPos==-1?backLink.length():endSuffixPos);
			if(action.endsWith("login")||action.endsWith("login.action")){
				return false;
			}
			return true;
	    }
}
