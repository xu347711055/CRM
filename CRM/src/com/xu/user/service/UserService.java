package com.xu.user.service;

import java.util.List;

import com.xu.common.service.BaseService;
import com.xu.role.domain.Auth;
import com.xu.role.domain.Role;
import com.xu.user.domain.User;

public interface UserService extends BaseService<User> {

	public User checkLogin(String account, String password) throws Exception;
	
	public List<Auth> getAuthByUser(Integer userId);
	public List<Role> getRoleByUser(Integer userId);
}
