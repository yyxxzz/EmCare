package com.company.emcare.dao;

import java.util.List;
import java.util.Set;
 
import com.company.emcare.model.Person;
import com.company.emcare.model.Role;

public interface PersonDao {
	public Person login(String username,String password);
	
	public List<Person> getAllUser(int firstResult,	int maxResult, String query);
	
	public void addUser(Person person);
	
	public void updateUser(Person person);
	
	public void deleteUser(Person person);
	
	public Person getUser(Long id);
	
	public Set<Person> getPersonByRoleType(int roleType);
	
	public Role getRoleByRoleType(int roleType);
	
	public int getTotalAcount(String query);
	
	public Person getPersonByUserName(String username);
	
	
}

