package com.xu.main.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.xu.common.Constants.Constant;
import com.xu.common.page.PageVO;
import com.xu.common.util.CommonUtil;
import com.xu.contact.domain.Contact;
import com.xu.contact.service.ContactService;
import com.xu.customer.domain.Customer;
import com.xu.customer.service.CustService;
import com.xu.user.domain.User;

@ParentPackage("cms")
@Result(name="success",location="main.jsp")
@Action("loadMainAction")
public class LoadMainAction {
	@Autowired
	private ContactService contService;
	@Autowired
	private CustService custService;
	private int birthdayCont;
	private int contCust;
	private PageVO<Customer> custPagevo = new PageVO<>();
	private PageVO<Contact> contPagevo = new PageVO<>();
	private String username;
	private String resultType;
	
	public String execute() throws ParseException{
		
		User user = (User) ActionContext.getContext().getSession().get("user");
		username = user.getName(); 
		
		Map<String,String> alias = new HashMap<>();
		alias.put("owner", "o");
		Map<String,Object> conditionsEq = new HashMap<>();
		conditionsEq.put("o.id", user.getId());
		Map<String, Date> timeScope = CommonUtil.getTodayTimeScope();
		//查询今天需要联系的客户
		custPagevo = custService.listCustByUserEqAndLike(alias, conditionsEq, 
				custPagevo, null, null, "contactDate", timeScope.get("begin"), timeScope.get("end"));
		resultType = Constant.ResultType_ListCustMain;	
		
		//查询今天生日的联系人
		Map<String,Object> contactConditionEq = new HashMap<>();
		contactConditionEq.put("u.id", user.getId());
		contPagevo = contService.getContacts(contPagevo, contactConditionEq, null, null, null);
		List<Contact> data = contPagevo.getData();

		List<Contact> filteredDate = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd");
		String dateStr = format.format(new Date());
		String[] split = dateStr.split("-");
		int curMonth = Integer.parseInt(split[0]);
		int curDay = Integer.parseInt(split[1]);
		for(Contact c : data){
			if(c.getBirthday()!=null){
				String birthStr = format.format(c.getBirthday());
				String[] splitBirth = birthStr.split("-");
				int month = Integer.parseInt(splitBirth[0]);
				int day = Integer.parseInt(splitBirth[1]);
				if(curMonth==month&&curDay==day){
					filteredDate.add(c);
				}
			}
		}
		contPagevo.setData(filteredDate);
		contPagevo.setTotalRecord(filteredDate.size());
		return "success";
	}
	public static void main(String[] args) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		System.out.println(GregorianCalendar.MONTH);
	}
	public PageVO<Contact> getContPagevo() {
		return contPagevo;
	}

	public void setContPagevo(PageVO<Contact> contPagevo) {
		this.contPagevo = contPagevo;
	}

	public CustService getCustService() {
		return custService;
	}

	public void setCustService(CustService custService) {
		this.custService = custService;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getBirthdayCont() {
		return birthdayCont;
	}

	public void setBirthdayCont(int birthdayCont) {
		this.birthdayCont = birthdayCont;
	}

	public int getContCust() {
		return contCust;
	}

	public void setContCust(int contCust) {
		this.contCust = contCust;
	}

	public PageVO<Customer> getCustPagevo() {
		return custPagevo;
	}

	public void setCustPagevo(PageVO<Customer> custPagevo) {
		this.custPagevo = custPagevo;
	}
	
}
