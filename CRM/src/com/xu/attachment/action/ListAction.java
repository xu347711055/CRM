package com.xu.attachment.action;

import java.util.HashMap;
import java.util.List;
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
import com.xu.customer.domain.Customer;
import com.xu.customer.service.CustService;
import com.xu.user.domain.User;

@ParentPackage("cms")
@Namespace("/attachment")
@Action("list")
@Result(name="success",location="list.jsp")
public class ListAction implements ModelDriven<PageVO<Customer>>{
	@Autowired
	private CustService custService;
	private List<Customer> custList;
	private PageVO<Customer> pagevo = new PageVO<>();
	private String resultType = Constant.ResultType_ListCustAttach;
	
	public String execute(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		Map<String,String> orders = new HashMap<>();
		orders.put("id", "asc");
		Map<String,Object> conditions = new HashMap<>();
		if(user.getAdmin()==1){
			pagevo = custService.listByPageWithState(pagevo, 1);
			
		}else{
			Map<String,String> alias = new HashMap<>();
			alias.put("owner", "o");
			conditions.put("state", 1);
			conditions.put("o.id", user.getId());
			pagevo = custService.listCustByUser(alias, conditions, pagevo, orders);
		}
		custList = pagevo.getData();
		return "success";
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public List<Customer> getCustList() {
		return custList;
	}

	public void setCustList(List<Customer> custList) {
		this.custList = custList;
	}

	public PageVO<Customer> getPagevo() {
		return pagevo;
	}

	public void setPagevo(PageVO<Customer> pagevo) {
		this.pagevo = pagevo;
	}

	@Override
	public PageVO<Customer> getModel() {
		return this.pagevo;
	}
	
}
