package com.company.emcare.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.company.emcare.model.Voice;
import com.company.emcare.model.VoiceComment;
import com.company.emcare.service.VoiceService;

@Scope("prototype")
@Controller
public class PostVoiceCommentAction extends BaseAction{
	@Autowired
	private VoiceService voiceService;
	private String comment;
	private String author;
	private Long voiceId;
	public String postComment(){
		Voice voice = voiceService.getVoiceById(voiceId).getVoice();
		VoiceComment voiceComment = new VoiceComment(voice,comment,author);
		voiceService.saveVoiceComment(voiceComment); 
		return SUCCESS;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Long getVoiceId() {
		return voiceId;
	}
	public void setVoiceId(Long voiceId) {
		this.voiceId = voiceId;
	}
}
