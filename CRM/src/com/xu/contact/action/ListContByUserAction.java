package com.xu.contact.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xu.common.Constants.Constant;
import com.xu.common.page.PageVO;
import com.xu.contact.domain.Contact;
import com.xu.contact.service.ContactService;
import com.xu.user.domain.User;

@ParentPackage("cms")
@Action("/listContByUser")
@Namespace("/customer")
@Result(name="success",location="userContactList.jsp")
public class ListContByUserAction implements ModelDriven<PageVO<Contact>>{
	@Autowired
	private ContactService contactService;
	private PageVO<Contact> pagevo = new PageVO<>();
	private User user;
	private String resultType;
	
	public String execute(){
		user = (User) ActionContext.getContext().getSession().get("user");
		if(user.getAdmin()==1){
			Map<String,String> orders = new HashMap<>();
			orders.put("id", "asc");
			pagevo = contactService.listByPage(Contact.class, pagevo, null, orders);
		}else{
			pagevo = contactService.getContactsByUser(user.getId(), pagevo);
		}
		this.resultType = Constant.ResultType_ListContByUser;
		return "success";
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public PageVO<Contact> getPagevo() {
		return pagevo;
	}


	public void setPagevo(PageVO<Contact> pagevo) {
		this.pagevo = pagevo;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public PageVO<Contact> getModel() {
		return this.pagevo;
	}
	
}
