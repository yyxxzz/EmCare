package com.company.emcare.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.company.emcare.dto.UserInfo;
import com.company.emcare.model.VoiceType;
import com.company.emcare.service.VoiceService;

@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport
        implements
            SessionAware,
            ServletRequestAware,
            ServletResponseAware {

    protected Log log = LogFactory.getLog(getClass());
    protected Map<String, Object> session;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    
    protected String category;
    
    public static final String CATEGORY_OPEN = "open";
    public static final String CATEGORY_CLOSE = "closed";
    public static final String CATEGORY_ALL = "all";
    public static final String CATEGORY_NEW = "new";

	@Resource
	private VoiceService voiceService;
    
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
    
    protected UserInfo getUserInfo() {
        UserInfo userInfo = null;
        try {
            userInfo = (UserInfo) session.get("userInfo");
        } catch (Exception e) {
            log.error("Could not find UserInfo from session", e);
        }
        if (null == userInfo) {
            log.error("Could not find UserInfo from session");
        }
        return userInfo;
    }
    
    public void setBackLink(String s) {
        session.put("_backLink", s);
    }
    public String getBackLink() {
        return (String) session.get("_backLink");
    }
    
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    public int getVoiceTotal(String category){
    	    	
    	if(CATEGORY_OPEN.equals(category)){
    		return voiceService.getTotalByCategory(CATEGORY_OPEN);
    	}else if(CATEGORY_CLOSE.equals(category)){
    		return voiceService.getTotalByCategory(CATEGORY_CLOSE);
    	}else if(CATEGORY_ALL.equals(category)){
    		return voiceService.getTotalByCategory(CATEGORY_ALL);
    	}else{
    		return voiceService.getTotalByCategory(CATEGORY_ALL);
    	}
    }

	public List<VoiceType> getVoiceTypes() {
		return voiceService.getAllVoiceTypes();
	}
	
	protected boolean isAdmin(){
		return session.get("ownRight")!=null;
	}

}
