package com.xu.customer.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.customer.service.CustService;

/**
 * 按照联系日期查询客户
 * @author xu
 *
 */
@ParentPackage("cms")
@Namespace("/customer")
@Result(name="success",location="custList.jsp")
@Action("doSearchCustByDate")
public class DoSearchCustByDateAction {
	@Autowired
	private CustService custService;
	private String searchType;
	
	public String execute(){
		
		return "success";
	}
}
