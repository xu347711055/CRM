package com.xu.privilege.dao;

import java.util.List;

import com.xu.common.dao.BaseDao;
import com.xu.privilege.domain.Auth;

public interface AuthDao extends BaseDao<Auth> {

	public List<Auth> listAuthByRoleId(int roleId);
}
