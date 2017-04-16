package com.xu.statistic.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.xu.common.page.PageVO;
import com.xu.customer.domain.Customer;
import com.xu.customer.service.CustService;
import com.xu.user.domain.User;

/**
 * 跳转到客户档案管理页面
 * @author xu
 *
 */
@Namespace("/statistic")
@Result(name="success",location="custList.jsp")
@Action("listCust")
public class ListCustAction {
	@Autowired
	private CustService custService;
	private List<Customer> custList;
	
	public String execute(){
		PageVO<Customer> pagevo = new PageVO<>();
		Map<String,Object> conditions = new HashMap<>();
		User user = (User) ActionContext.getContext().getSession().get("user");
		conditions.put("o.id", user.getId());
		Map<String,String> alias = new HashMap<>();
		alias.put("owner", "o");
		pagevo = custService.listCustByUser(alias, conditions, pagevo, null);
		custList = pagevo.getData();
		return "success";
	}

	public List<Customer> getCustList() {
		return custList;
	}

	public void setCustList(List<Customer> custList) {
		this.custList = custList;
	}
	
}
