package com.xu.customer.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xu.common.domain.City;
import com.xu.customer.domain.Customer;
import com.xu.customer.service.CustService;
import com.xu.user.domain.User;
import com.xu.user.service.UserService;

@ParentPackage("cms")
@Namespace("/customer")
@Action("doAddCust")
@Results({@Result(name="success",location="custManage.action",type="redirect"),
	@Result(name="fail",location="addCust.action",type="redirect")})
public class DoAddCustAction implements ModelDriven<Customer>{
	@Autowired
	private CustService custService;
	@Autowired
	private UserService userService;
	private Customer customer = new Customer();
	private String cityId;
	
	public String execute(){
		User creator = (User) ActionContext.getContext().getSession().get("user");
		/*User owner = userService.get(User.class, "account", account);
		if(owner==null){
			System.err.println("该员工号不存在");
			return "fail";
		}*/
		City city = new City();
		city.setId(Integer.valueOf(cityId));
		customer.setCity(city);
		customer.setState(1);	//状态为可用
		customer.setCreator(creator);
		customer.setUpdater(creator);
		List<User> owners = new ArrayList<>();
		owners.add(creator);
		customer.setOwner(owners);
		
		custService.add(customer);
		return "success";
	}

	
	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustService getCustService() {
		return custService;
	}


	public void setCustService(CustService custService) {
		this.custService = custService;
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	@Override
	public Customer getModel() {
		return this.customer;
	}

}
