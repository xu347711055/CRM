package com.xu.common.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.common.domain.City;
import com.xu.common.domain.Province;
import com.xu.common.service.BaseService;

@Action("getCityData")
@ParentPackage("json-default")
@Result(name="success",type="json",params={"root","city","excludeNullProperties","true"})
public class GetCityDataAction {
	private List<City> city = new ArrayList<>();
	private String id;
	@Autowired
	private BaseService<City> baseService;
	
	public String execute(){
		System.out.println(id);
		Map<String,String> orders = new HashMap<String, String>();
		orders.put("id", "asc");
		Map<String,Object> conditions = new HashMap<>();
		Province province = new Province();
		province.setId( Integer.valueOf(id));
		conditions.put("province",province);
		city = baseService.list(conditions, City.class, orders);
		return "success";
	}

	public List<City> getCity() {
		return city;
	}

	public void setCity(List<City> city) {
		this.city = city;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BaseService<City> getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService<City> baseService) {
		this.baseService = baseService;
	}

	
}
