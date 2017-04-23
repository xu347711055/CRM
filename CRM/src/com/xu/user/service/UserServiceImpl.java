package com.xu.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xu.common.service.BaseServiceImpl;
import com.xu.common.util.MD5Util;
import com.xu.privilege.domain.Auth;
import com.xu.role.domain.Role;
import com.xu.user.dao.UserDao;
import com.xu.user.domain.User;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserDao userDao; 
	
	@Override
	public User checkLogin(String account, String password) throws Exception {
		User user = this.get(User.class, "account", account);
		if(user==null || user.getStatus()==0){
			return null;
		}
		if(MD5Util.md5(password).equals(user.getPassword())){
			return user;
		}
		return null;
	}

	@Override
	public List<Auth> getAuthByUser(Integer userId) {
		return userDao.getAuthByUser(userId);
	}

	@Override
	public List<Role> getRoleByUser(Integer userId) {
		return userDao.getRoleByUser(userId);
	}
	
}
