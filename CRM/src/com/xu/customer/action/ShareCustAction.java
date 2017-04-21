package com.xu.customer.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.xu.common.Constants.Constant;
import com.xu.customer.domain.Customer;
import com.xu.customer.service.CustService;
import com.xu.dept.domain.Department;
import com.xu.user.domain.User;
import com.xu.user.service.UserService;

@Namespace("/customer")
@Action("shareCust")
@Result(name="success",location="custManage.action",type="redirect")
public class ShareCustAction {
	@Autowired
	private UserService userService;
	@Autowired
	private CustService custService;
	private int deptId;
	private int userId;
	private int custId;
	private String shareType;
	
	public String execute(){
		Customer cust = custService.get(Customer.class, custId);
		User curUser = (User) ActionContext.getContext().getSession().get("user");
		List<User> owners = cust.getOwner();
		if(userId!=0){
			User user = new User();
			user.setId(userId);
			
			owners.add(user);
		}else{
			Map<String,String> orders = new HashMap<>();
			orders.put("id", "asc");
			Map<String,Object> conditions = new HashMap<>();
			Department dept = new Department();
			dept.setId(deptId);
			conditions.put("dept", dept);
			List<User> userList = userService.list(conditions, User.class, orders);	//根据部门找出该部门下所有用户
			
			owners.addAll(userList);
		}
		if(Constant.ShareType_ChangeOwner.equals(shareType)){
			owners.remove(curUser);
			System.out.println(owners);
		}
		custService.update(cust);
		return "success";
	}

	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
