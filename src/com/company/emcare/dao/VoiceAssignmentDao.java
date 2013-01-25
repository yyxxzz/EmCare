package com.company.emcare.dao;

import java.util.List;

import com.company.emcare.model.Person;
import com.company.emcare.model.VoiceAssignment;

public interface VoiceAssignmentDao {
	public void saveVoiceAssignment(VoiceAssignment voiceAssignment);
	
	public List<VoiceAssignment> getVoiceAssigmentsByPerson(Person person);
	

	void deleteVoiceAssigment(VoiceAssignment voiceAssigments);

	public void deleteVoiceAssigment(List<VoiceAssignment> vaList); 
}
