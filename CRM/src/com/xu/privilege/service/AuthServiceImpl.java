package com.xu.privilege.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xu.common.service.BaseServiceImpl;
import com.xu.privilege.dao.AuthDao;
import com.xu.privilege.domain.Auth;

@Service
public class AuthServiceImpl extends BaseServiceImpl<Auth> implements AuthService {
	
	@Autowired
	private AuthDao authDao;
	@Override
	public List<Auth> listAuthByRoleId(int roleId) {
		return this.authDao.listAuthByRoleId(roleId);
	}

	
}
