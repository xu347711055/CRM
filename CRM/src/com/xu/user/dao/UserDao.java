package com.xu.user.dao;

import java.util.List;

import com.xu.common.dao.BaseDao;
import com.xu.role.domain.Auth;
import com.xu.role.domain.Role;
import com.xu.user.domain.User;

public interface UserDao extends BaseDao<User> {

	public List<Auth> getAuthByUser(Integer userId);
	public List<Role> getRoleByUser(Integer userId);
}
