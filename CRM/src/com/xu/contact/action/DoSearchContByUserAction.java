package com.xu.contact.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.xu.common.page.PageVO;
import com.xu.contact.domain.Contact;
import com.xu.contact.service.ContactService;
import com.xu.customer.domain.Customer;
import com.xu.customer.service.CustService;
import com.xu.user.domain.User;

@ParentPackage("cms")
@Namespace("/customer")
@Result(name="success",location="userContactList.jsp")
@Action("doSearchContByUser")
public class DoSearchContByUserAction {
	@Autowired
	private ContactService contService;
	@Autowired
	private CustService custService;
	//跳转类型
	private String resultType;
	//按用户查询
	private User user;
	//按客户查询
	private PageVO<Contact> pagevo = new PageVO<>();
	//查询条件
	private String contName;
	private String custName;
	private String telephone;
	private String email;
	private String qqNum;
	
	public String execute(){
		Map<String, Object> conditions = new HashMap<>();
		Map<String, Object> conditionsLike = new HashMap<>();
		Map<String, List<Object>> orEq = new HashMap<>();
		
		user = (User) ActionContext.getContext().getSession().get("user");//查询当前用户
		//开始封装查询条件
		if(!StringUtils.isEmpty(contName)){	//按联系人姓名查询
			System.out.println("查询条件：name="+contName);
			conditionsLike.put("ct.name", contName);
		}
		if(!StringUtils.isEmpty(telephone)){	//按联系人手机查询
			System.out.println("查询条件：telephone="+telephone);
			conditionsLike.put("ct.telephone", telephone);
		}
		if(!StringUtils.isEmpty(qqNum)){	//按联系人qq查询
			System.out.println("查询条件：qqNum="+qqNum);
			conditionsLike.put("qqNum", qqNum);
		}
		if(!StringUtils.isEmpty(email)){	//按联系人邮箱查询
			System.out.println("查询条件：email="+email);
			conditionsLike.put("ct.email", email);
		}
		if(!StringUtils.isEmpty(custName)){	//按所属客户查询
			System.out.println("查询条件：cname="+custName);
			Map<String,Object> custNameCondition = new HashMap<>();
			custNameCondition.put("cname", custName);
			//先根据客户名称查询出该名字的客户
			List<Customer> custList = custService.listLike(custNameCondition, Customer.class, null);
			if(custList.isEmpty()){	//如果查询结果为空，则返回
				return "success";
			}
			List<Object> custIds = new ArrayList<>();
			for(Customer cust : custList){
				custIds.add(cust.getId());	
			}
			orEq.put("cust_id", custIds);
		}
		
		if(user.getAdmin()==1){
			Map<String,String> orders = new HashMap<>();
			orders.put("id", "asc");
			pagevo = contService.getContacts(pagevo, conditions, conditionsLike, null, orEq);
		}else{
			conditions.put("u.id", user.getId());
			pagevo = contService.getContacts(pagevo, conditions, conditionsLike, null, orEq);
		}
		return "success";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQqNum() {
		return qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	public PageVO<Contact> getPagevo() {
		return pagevo;
	}

	public void setPagevo(PageVO<Contact> pagevo) {
		this.pagevo = pagevo;
	}
	
}
