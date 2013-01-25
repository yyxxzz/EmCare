package com.company.emcare.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.emcare.dao.ActionHistDao;
import com.company.emcare.dao.PersonDao;
import com.company.emcare.dao.VoiceAssignmentDao;
import com.company.emcare.dto.UserInfo;
import com.company.emcare.model.ActionHist;
import com.company.emcare.model.Person;
import com.company.emcare.model.Role;
import com.company.emcare.model.VoiceAssignment;
import com.company.emcare.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private PersonDao personDao;
	@Resource
	private ActionHistDao actionHistDao;
	@Resource
	private VoiceAssignmentDao voiceAssignmentDao;
	
	@Override
	public List<Person> getAllUser(int firstResult,	int maxResult, String query) {
		// TODO Auto-generated method stub
		return personDao.getAllUser(firstResult,maxResult,query);
	}

	@Override
	public void addUser(Person person) {
		// TODO Auto-generated method stub
		personDao.addUser(person);
	}

	@Override
	public void updateUser(Person person) {
		personDao.updateUser(person);
	}

	@Override
	public void deleteUser(Person person) {
		// TODO Auto-generated method stub
		List<ActionHist> ahList = actionHistDao.getActionHistByPerson(person);
		actionHistDao.deleteActionHista(ahList);
		List<VoiceAssignment> vaList = voiceAssignmentDao.getVoiceAssigmentsByPerson(person);
		voiceAssignmentDao.deleteVoiceAssigment(vaList);
		personDao.deleteUser(person);
	}

	@Override
	public Person getUser(Long id) {
		// TODO Auto-generated method stub
		return personDao.getUser(id);
	}

	@Override
	public Role getRoleByRoleType(int roleType) {
		return personDao.getRoleByRoleType(roleType);
	}

	@Override
	public List<Person> getPersonByRole(int roleType) {
		return new ArrayList<Person>(personDao.getRoleByRoleType(roleType).getPersonMembers());
	}


	@Override
	public int getTotalAcount(String query) {
		return personDao.getTotalAcount(query);
	}

	@Override
	public boolean checkUserNameExist(String username) {
		return personDao.getPersonByUserName(username)!=null;
	}

}
