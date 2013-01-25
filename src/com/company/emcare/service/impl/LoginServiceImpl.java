package com.company.emcare.service.impl;


import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.emcare.dao.PersonDao;
import com.company.emcare.dto.UserInfo;
import com.company.emcare.model.Person;
import com.company.emcare.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Resource
	private PersonDao personDao;

	@Override
	public UserInfo login(String username, String password) {
		UserInfo userInfo = new UserInfo();
		Person person = personDao.login(username, password);
		userInfo.setLoginTime(new Date());
		userInfo.setPerson(person);
		if(person!=null)
		userInfo.setUsername(person.getRealname());
		return userInfo;
	}

}
