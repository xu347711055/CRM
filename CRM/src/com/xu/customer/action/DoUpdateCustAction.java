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
import com.xu.common.Constants.Constant;
import com.xu.common.domain.City;
import com.xu.customer.domain.Customer;
import com.xu.customer.service.CustService;
import com.xu.user.domain.User;
import com.xu.user.service.UserService;

@ParentPackage("cms")
@Namespace("/customer")
@Action("doUpdateCust")
@Results({@Result(name=Constant.ResultType_ListCustMain, location="/"+Constant.ResultType_ListCustMain+".action",type="redirect"),
	@Result(name=Constant.ResultType_ListCustManage, location=Constant.ResultType_ListCustManage+".action",type="redirect"),
	@Result(name="fail",location="updateCust.action",type="redirect")})
public class DoUpdateCustAction implements ModelDriven<Customer> {
	@Autowired
	private CustService custService;
	@Autowired
	private UserService userService;
	private Customer customer = new Customer();
	private String cityId;
	private String[] account;
	private String creater;
	private String resultType;
	
	public String execute(){
		System.out.println(customer);
		User updater = (User) ActionContext.getContext().getSession().get("user");
		List<User> owners = new ArrayList<>();
		for(String acc:account){
			User owner = userService.get(User.class, "account", acc);
			if(owner==null){
				return "fail";
			}
			owners.add(owner);
		}
		City city = new City();
		city.setId(Integer.valueOf(cityId));
		customer.setOwner(owners);
		customer.setCity(city);
		if(creater!=null){
			customer.setCreator(userService.get(User.class, Integer.valueOf(creater)));
		}
		customer.setUpdater(updater);
		customer.setState(1);
		custService.merge(customer);
		return resultType;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}


	public String[] getAccount() {
		return account;
	}

	public void setAccount(String[] account) {
		this.account = account;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Override
	public Customer getModel() {
		return this.customer;
	}
	
}
