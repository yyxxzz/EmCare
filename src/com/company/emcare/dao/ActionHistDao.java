package com.company.emcare.dao;

import java.util.List;

import com.company.emcare.model.ActionHist;
import com.company.emcare.model.Person;
import com.company.emcare.model.Voice;

public interface ActionHistDao {
	public List<ActionHist> getActionHistForVoice(Voice voice);
	public ActionHist getLastestActionHistForVoice(Voice voice);
	public void addActionHist(ActionHist hist);
	public  List<ActionHist> getActionHistByPerson(Person person);
	public void deleteActionHista(List<ActionHist> actionHists);
}
