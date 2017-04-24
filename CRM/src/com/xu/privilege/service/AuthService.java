package com.xu.privilege.service;

import java.util.List;

import com.xu.common.service.BaseService;
import com.xu.common.util.SpringUtil;
import com.xu.privilege.domain.Auth;

public interface AuthService extends BaseService<Auth> {

	public List<Auth> listAuthByRoleId(int roleId);
	
	public static void main(String[] args) {
		AuthService service = SpringUtil.getBean(AuthService.class);
		List<Auth> list = service.listAuthByRoleId(1);
			System.out.println(list);
	}
}
