package com.company.emcare.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import com.company.emcare.dao.PersonDao;
import com.company.emcare.dto.PageBean;
import com.company.emcare.dto.UserInfo;
import com.company.emcare.model.Person;
import com.company.emcare.model.Role;
import com.company.emcare.model.Voice;
import com.company.emcare.service.UserService;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction {
	
	@Resource
	private UserService userService;
	@Resource
	private PersonDao personDao;
	private PageBean page;
	private Integer pageNo;
	private Integer newVoiceSize;
	private List<Person> users = new ArrayList<Person>();
	private Person user;
	private String roles;
	private String query;
	

	public String getAllUsers()throws Exception{
		if(!StringUtils.isEmpty(query))
			query = new String(query.getBytes("iso-8859-1"),"UTF-8");
		newVoiceSize = userService.getTotalAcount(query);
		page = new PageBean(Person.class);
		page.setPageUrl("/EmCare/admin/getAllUser?query="+(query==null?"":query));
		page.setCurrentPage(getPageNo());
		page.setRecordSize(newVoiceSize);
		users = userService.getAllUser(page.getFirstResult(),page.getPageSize(),query);
		return SUCCESS;
	}
	
	public String saveUser(){
		if(roles!=null){
			String[] str = roles.split(",");
			Set<Role> roleSet = new HashSet<Role>();
			for(int i=0;i<str.length;i++){
				Role role = userService.getRoleByRoleType(Integer.parseInt(str[i]));
				roleSet.add(role);
			}
			user.setPersonRoles(roleSet);
		}
		userService.addUser(user);
		return SUCCESS;
	}
	
	public String udpateUser(){
		if(roles!=null && !roles.equals("")){
			String[] str = roles.split(",");
			Set<Role> roleSet = new HashSet<Role>();
			for(int i=0;i<str.length;i++){
				Role role = userService.getRoleByRoleType(Integer.parseInt(str[i]));
				roleSet.add(role);
			}
			user.setPersonRoles(roleSet);
		}
		Person person = userService.getUser(user.getPersonId());
		person.setEmail(user.getEmail());
		person.setPassword(user.getPassword());
		person.setPersonRoles(user.getPersonRoles());
		/*for(Role role:user.getPersonRoles()){
			if(!person.getPersonRoles().contains(role)){
				person.getPersonRoles().add(role);
			}
		}*/
		person.setRealname(user.getRealname());
		userService.updateUser(person);
		return SUCCESS;
	}
	
	public String viewUser(){
		Person puser = userService.getUser(user.getPersonId());
		users.add(puser);
		return SUCCESS;
	}
	
	public String deleteUser(){
		userService.deleteUser(user);
		return SUCCESS;
	}

	public PageBean getPage() {
		return page;
	}

	public void setPage(PageBean page) {
		this.page = page;
	}

	public Integer getPageNo() {
		return pageNo!=null?pageNo:1;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public List<Person> getUsers() {
		return users;
	}

	public void setUsers(List<Person> users) {
		this.users = users;
	}

	public Person getUser() {
		return user;
	}

	public void setUser(Person user) {
		this.user = user;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Integer getNewVoiceSize() {
		return newVoiceSize;
	}

	public void setNewVoiceSize(Integer newVoiceSize) {
		this.newVoiceSize = newVoiceSize;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
}
