package com.company.emcare.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "person")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long personId;
	@Column
	private String username;
	@Column
	private String realname;
	@Column
	private String password;
	@Column
	private String email;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "person_role_link", joinColumns = @JoinColumn(name = "personId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	private Set<Role> personRoles;
	@OneToOne(mappedBy="voice")
	private VoiceAssignment assignment;
	
	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Role> getPersonRoles() {
		if(personRoles==null){
		personRoles= new HashSet<Role>();	
		}
		return personRoles;
	}

	public void setPersonRoles(Set<Role> personRoles) {
		this.personRoles = personRoles;
	}

	public VoiceAssignment getAssignment() {
		return assignment;
	}

	public void setAssignment(VoiceAssignment assignment) {
		this.assignment = assignment;
	}
	
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isInRoleOf(String roleStr){
		for(Role role:getPersonRoles()){
			if(role.toString().equals(roleStr)){
				return true;
			}
		}
		return false;
	}
	
}
