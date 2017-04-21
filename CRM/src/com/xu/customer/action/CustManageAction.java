package com.xu.customer.action;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

/**
 * 跳转到客户档案管理页面
 * @author xu
 *
 */
@ParentPackage("cms")
@Namespace("/customer")
@Result(name="success",location="custList.jsp")
@Action("custManage")
public class CustManageAction implements ModelDriven<PageVO<Customer>>{
	@Autowired
	private CustService custService;
	private List<Customer> custList;
	private PageVO<Customer> pagevo = new PageVO<>();
	private String resultType = Constant.ResultType_ListCustManage;
	
	public String execute(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		Map<String,String> orders = new HashMap<>();
		orders.put("id", "asc");
		Map<String,Object> conditions = new HashMap<>();
		if(user.getAdmin()==1){
			pagevo = custService.listByPageWithState(pagevo, 1);
			/*List<Customer> data = pagevo.getData();
			Set<Customer> custSet = new LinkedHashSet<>();	//去重
			custSet.addAll(data);
			data.clear();
			data.addAll(custSet);*/
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

	public PageVO<Customer> getPagevo() {
		return pagevo;
	}

	public void setPagevo(PageVO<Customer> pagevo) {
		this.pagevo = pagevo;
	}

	public List<Customer> getCustList() {
		return custList;
	}

	public void setCustList(List<Customer> custList) {
		this.custList = custList;
	}

	@Override
	public PageVO<Customer> getModel() {
		return this.pagevo;
	}
	
}
