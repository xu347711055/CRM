package test;

import java.util.HashMap;
import java.util.Map;

import com.xu.common.util.SpringUtil;
import com.xu.privilege.service.AuthService;
import com.xu.role.domain.Role;
import com.xu.role.service.RoleService;
import com.xu.user.domain.User;
import com.xu.user.service.UserService;

public class Test {
	private UserService userService = SpringUtil.getBean(UserService.class);
	private AuthService authService = SpringUtil.getBean(AuthService.class);
	private RoleService roleService = SpringUtil.getBean(RoleService.class);
	
	@org.junit.Test
	public void test(){
		userService.getAuthByUser(1);
	}
	
}
