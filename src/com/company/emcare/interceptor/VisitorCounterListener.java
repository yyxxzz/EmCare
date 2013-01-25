package com.company.emcare.interceptor;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.company.emcare.service.VoiceService;

public class VisitorCounterListener implements HttpSessionListener{

	
	@Override
	public void sessionCreated(HttpSessionEvent event) {

		HttpSession session = event.getSession();
        ServletContext application = session.getServletContext(); 
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
        
        try{
            VoiceService voiceService = (VoiceService) ctx.getBean("voiceService");        
    		Long currentCount  = voiceService.updateVisitorCount();
    		application.setAttribute("visitorAmount", currentCount);
        }catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		
	}

}
