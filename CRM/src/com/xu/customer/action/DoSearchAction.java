package com.xu.customer.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.xu.common.Constants.Constant;
import com.xu.common.page.PageVO;
import com.xu.customer.domain.Customer;
import com.xu.customer.service.CustService;
import com.xu.user.domain.User;

@ParentPackage("cms")
@Namespace("/customer")
@Results({@Result(name=Constant.ResultType_ListCustManage,location="custList.jsp"),
	@Result(name=Constant.ResultType_ListCustAttach,location="/WEB-INF/page/attachment/list.jsp")})
@Action("doSearch")
public class DoSearchAction {
	@Autowired
	private CustService custService;
	private PageVO<Customer> pagevo = new PageVO<>();
	private List<Customer> custList;
	private String cnumber;
	private String cname;
	private String telephone;
	private String companyNature;
	private String level;
	private Date contactDate;
	private String source;
	private String resultType;
	
	public String execute(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		Map<String,String> orders = new HashMap<>();
		orders.put("id", "asc");
		Map<String,Object> conditions = new HashMap<>();
		conditions.put("state", 1);
		Map<String,Object> conditionsLike = new HashMap<>();
		if(!StringUtils.isEmpty(cnumber)){
			conditions.put("cnumber", cnumber);
		}
		if(!StringUtils.isEmpty(cname)){
			System.out.println("*********cname-Action:"+cname);
			conditionsLike.put("cname", cname);
		}
		if(!StringUtils.isEmpty(companyNature)){
			conditions.put("companyNature", companyNature);
		}
		if(!StringUtils.isEmpty(telephone)){
			conditions.put("telephone", telephone);
		}
		if(!StringUtils.isEmpty(contactDate)){
			conditions.put("contactDate", contactDate);
		}
		if(!StringUtils.isEmpty(source)){
			conditions.put("source", source);
		}
		if(!StringUtils.isEmpty(level)){
			conditions.put("level", level);
		}
		if(user.getAdmin()==1){
			pagevo = custService.listCustByUserEqAndLike(null, conditions, pagevo, orders, conditionsLike,null,null,null);
		}else{
			conditions.put("o.id", user.getId());
			Map<String,String> alias = new HashMap<>();
			alias.put("owner", "o");
			pagevo = custService.listCustByUserEqAndLike(alias, conditions, pagevo, orders, conditionsLike,null,null,null);
		}
		custList = pagevo.getData();
		return resultType;
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

	public String getCnumber() {
		return cnumber;
	}

	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCompanyNature() {
		return companyNature;
	}

	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getContactDate() {
		return contactDate;
	}

	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}
