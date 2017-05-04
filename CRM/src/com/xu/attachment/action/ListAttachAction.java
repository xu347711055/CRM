package com.xu.attachment.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.xu.attachment.domain.Attachment;
import com.xu.attachment.service.AttachService;
import com.xu.common.page.PageVO;
import com.xu.customer.domain.Customer;

@ParentPackage("cms")
@Namespace("/attachment")
@Action("listAttach")
@Result(name="success",location="attachment.jsp")
@InterceptorRef("privilegeStack")
public class ListAttachAction implements ModelDriven<PageVO<Attachment>>{
	@Autowired
	private AttachService attachService;
	private PageVO<Attachment> pagevo = new PageVO<>();
	public int custId;
	
	public String execute(){
		Map<String,Object> conditions = new HashMap<>();
		Customer cust = new Customer();
		cust.setId(custId);
		conditions.put("cust", cust);
		Map<String,String> orders = new HashMap<>();
		orders.put("updateTime", "desc");
		pagevo = attachService.listByPage(Attachment.class, pagevo, conditions, orders);
		System.out.println(pagevo.getData());
		return "success";
	}

	public PageVO<Attachment> getPagevo() {
		return pagevo;
	}

	public void setPagevo(PageVO<Attachment> pagevo) {
		this.pagevo = pagevo;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	@Override
	public PageVO<Attachment> getModel() {
		return this.pagevo;
	}
	
}
