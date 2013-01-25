package com.company.emcare.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * key "voiceId" "title" "startTime" "endTime" "status" "type" "assignTo"
 * ........
 */
public class VoiceSearchOption {
	private Long voiceId;
	private String title;
	private Timestamp startTime;
	private Timestamp endTime;
	private int status;
	private Long type;
	private Long assignTo;
	
	public Long getVoiceId() {
		return voiceId;
	}
	public void setVoiceId(Long voiceId) {
		this.voiceId = voiceId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getStartTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		if(this.startTime != null) {
			return sdf.format(startTime);
		}else {
			return null;
		}
		
	}
	
	public void setStartTime(String startTime) {
		if(StringUtils.isNotEmpty(startTime)) {
			startTime +=" 00:00:00";
			this.startTime = Timestamp.valueOf(startTime);
		}else {
			this.startTime = null;
		}
	}
	
	public String getEndTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(this.endTime != null) {
			return sdf.format(endTime);
		}else { 
			return null;
		}
	}
	
	public void setEndTime(String endTime) {
		if(StringUtils.isNotEmpty(endTime)) {
			endTime +=" 00:00:00";
			this.endTime = Timestamp.valueOf(endTime);
		}else {
			this.endTime = null;
		}
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		if(StringUtils.isNotEmpty(status)) {
			this.status = Integer.valueOf(status);
		}else {
			this.status = 0;
		}
	}
	
	public Long getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(Long assignTo) {
		this.assignTo = assignTo;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public Long getType() {
		return type;
	}
	
}
