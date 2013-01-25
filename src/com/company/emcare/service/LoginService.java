package com.company.emcare.service;

import com.company.emcare.dto.UserInfo;

public interface LoginService {
	public UserInfo login(String username,String password);
}
