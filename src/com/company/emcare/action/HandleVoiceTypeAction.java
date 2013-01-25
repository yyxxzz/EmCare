package com.company.emcare.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.company.emcare.model.VoiceType;
import com.company.emcare.service.VoiceTypeService;

@Controller
@Scope("prototype")
public class HandleVoiceTypeAction extends BaseAction {
	
	@Resource
	private VoiceTypeService voiceTypeService;
	
	private VoiceType voiceType;
	
	private List<VoiceType> vtList = new ArrayList<VoiceType>(); 
	
	public String handleVoiceType(){
		
		vtList = voiceTypeService.getAllVoiceType();
		return SUCCESS;
		
	}
	
	public void deleteVoiceType() throws Exception{
		
		JSONObject obj = new JSONObject();
		VoiceType type = voiceTypeService.getVoiceTypeById(voiceType.getId());
		if(type.getVoices().size()>0){
			obj.put("msg", "This voice type has been associated with some voices, can not be deleted.");
			response.getWriter().print(obj.toString());
		}else{
			voiceTypeService.deleteVoiceType(type);
			response.getWriter().print(obj.toString());
		}
	}
	
	public String saveVoiceType(){
		
		if(!checkVoiceType()){
			return INPUT;
		}
		voiceTypeService.addVoiceType(voiceType);
		return SUCCESS;
	}
	
	public String viewVoiceType(){
		
		VoiceType vt = voiceTypeService.getVoiceTypeById(voiceType.getId());
		vtList.add(vt);
		return SUCCESS;
		
	}
	
	public String updateVoiceType() throws Exception{
		
		if(!checkVoiceType()){
			return INPUT;
		}
		
		VoiceType type = voiceTypeService.getVoiceTypeById(voiceType.getId());
		type.setName(voiceType.getName().trim());
		voiceTypeService.updateVoiceType(type);
		return SUCCESS;
	}

	private boolean checkVoiceType(){
		if(voiceType.getName()== null || voiceType.getName().trim().equals("")){
			request.setAttribute("typeError", "Type name can not be empty.");
			return false;
		}
		
		VoiceType existed = voiceTypeService.getVoiceTypeByName(voiceType.getName().trim());
		if(existed != null){
			request.setAttribute("typeError", "Type with the same name existed.");
			return false;
		}
		
		return true;
	}
	
	public VoiceType getVoiceType() {
		return voiceType;
	}

	public void setVoiceType(VoiceType voiceType) {
		this.voiceType = voiceType;
	}



	public List<VoiceType> getVtList() {
		return vtList;
	}



	public void setVtList(List<VoiceType> vtList) {
		this.vtList = vtList;
	}
}
