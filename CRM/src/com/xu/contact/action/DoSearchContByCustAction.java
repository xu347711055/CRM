package com.xu.contact.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.common.page.PageVO;
import com.xu.contact.domain.Contact;
import com.xu.contact.service.ContactService;
import com.xu.customer.domain.Customer;
import com.xu.customer.service.CustService;

@ParentPackage("cms")
@Namespace("/customer")
@Result(name="success",location="contactList.jsp")
@Action("doSearchContByCust")
public class DoSearchContByCustAction {
	@Autowired
	private ContactService contService;
	@Autowired
	private CustService custService;
	//跳转类型
	private String resultType;
	//按客户查询
	private String custId;
	private Customer customer;
	private PageVO<Contact> pagevo = new PageVO<>();
	//查询条件
	private String contName;
	private String telephone;
	private String email;
	private String qqNum;
	
	public String execute(){
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("state", 1);
		Map<String, Object> conditionsLike = new HashMap<>();
		if(!StringUtils.isEmpty(contName)){	//按联系人姓名查询
			System.out.println("查询条件：name="+contName);
			conditionsLike.put("name", contName);
		}
		if(!StringUtils.isEmpty(telephone)){	//按联系人手机查询
			System.out.println("查询条件：telephone="+telephone);
			conditionsLike.put("telephone", telephone);
		}
		if(!StringUtils.isEmpty(qqNum)){	//按联系人qq查询
			System.out.println("查询条件：qqNum="+qqNum);
			conditionsLike.put("qqNum", qqNum);
		}
		if(!StringUtils.isEmpty(email)){	//按联系人邮箱查询
			System.out.println("查询条件：email="+email);
			conditionsLike.put("email", email);
		}

		int customerId = Integer.valueOf(custId);
		Customer cust = new Customer();
		if(custId!=null){
			cust.setId(customerId);
		}
		conditions.put("cust", cust);
		pagevo = contService.listContactEqAndLike(pagevo, conditions, conditionsLike, null, null, null);
		customer = custService.get(Customer.class, customerId);
		return "success";
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
