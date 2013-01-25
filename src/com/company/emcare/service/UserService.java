package com.company.emcare.service;

import java.util.List;

import com.company.emcare.model.Person;
import com.company.emcare.model.Role;

public interface UserService {
	
	public List<Person> getAllUser(int firstResult,	int maxResult, String query);
	
	public void addUser(Person person);
	
	public void updateUser(Person person);
	
	public void deleteUser(Person person);
	
	public Person getUser(Long id);
	
	public Role getRoleByRoleType(int roleType);
	
	public List<Person> getPersonByRole(int type);

	public int getTotalAcount(String query);
	
	public boolean checkUserNameExist(String username);
}
