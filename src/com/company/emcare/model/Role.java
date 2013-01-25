package com.company.emcare.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;
	@Column
	private String roleName;
	@Column
	private int roleType;
	@OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "person_role_link",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "personId"))
	private Set<Person> personMembers;
	public final static int ROLE_SYS_ADMIN=0x1;
	public final static int ROLE_SUM=0x2;
	public final static int ROLE_LM=0x3;
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getRoleType() {
		return roleType;
	}
	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}
	public Set<Person> getPersonMembers() {
		return personMembers;
	}
	public void setPersonMembers(Set<Person> personMembers) {
		this.personMembers = personMembers;
	}
	
	public String toString(){
		switch(roleType){
		case ROLE_SYS_ADMIN:
			return "SA";
		case ROLE_SUM:
			return "S";
		case ROLE_LM:
			return "L";
			default:
		return "";
		}
	}
}
