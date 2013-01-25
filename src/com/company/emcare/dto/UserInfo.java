package com.company.emcare.dto;

import java.util.Date;
import java.util.Set;

import com.company.emcare.model.Person;
import com.company.emcare.model.Role;

public class UserInfo {
	private Person person;
	private String username;
	private Date loginTime;
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	public Set<Role> getPersonRoles(){
		return getPerson().getPersonRoles();
	}
	
	public boolean isPersonInRole(String roleType){
		//TO-DO: judge person role from string which indicates specific role type
		return false;
	}
	
	public boolean isPersonInRole(int roleType){
		for(Role role:getPersonRoles()){
			if(role.getRoleType()==roleType)
				return true;
		}
		return false;
	}
	
}
