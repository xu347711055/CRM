package com.xu.customer.action;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.xu.common.Constants.Constant;
import com.xu.common.page.PageVO;
import com.xu.common.util.CommonUtil;
import com.xu.customer.domain.Customer;
import com.xu.customer.service.CustService;
import com.xu.user.domain.User;

/**
 * 按照联系日期查询客户
 * @author xu
 *
 */
@ParentPackage("cms")
@Namespace("/customer")
@Result(name="success",location="custList.jsp")
@Action("doSearchTodayCust")
public class DoSearchTodayCustAction {
	@Autowired
	private CustService custService;
	private List<Customer> custList;
	private PageVO<Customer> pagevo = new PageVO<>();
	private String resultType = Constant.ResultType_ListCustManage;
	
	public String execute() throws ParseException{
		Map<String, Date> timeScope = CommonUtil.getTodayTimeScope();
		User user = (User) ActionContext.getContext().getSession().get("user");
		Map<String,String> orders = new HashMap<>();
		orders.put("id", "asc");
		if(user.getAdmin()==1){
			Map<String,Object> conditionEq = new HashMap<>();
			conditionEq.put("state", 1);
			pagevo = custService.listCustByUserEqAndLike(null, conditionEq, pagevo, orders, null,
					"contactDate", timeScope.get("begin"), timeScope.get("end"));
		}else{
			Map<String,Object> conditions = new HashMap<>();
			Map<String,String> alias = new HashMap<>();
			alias.put("owner", "o");
			conditions.put("o.id", user.getId());
			conditions.put("state", 1);
			pagevo = custService.listCustByUserEqAndLike(alias, conditions, pagevo, orders, null, 
					"contactDate", timeScope.get("begin"), timeScope.get("end"));
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
	
	
}
