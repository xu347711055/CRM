package com.xu.common.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.common.domain.Province;
import com.xu.common.service.BaseService;

@Action("getProvinceData")
@ParentPackage("json-default")
@Result(name="success",type="json",params={"root","province","excludeNullProperties","true"})
public class GetProvinceDataAction {
	private List<Province> province = new ArrayList<>();
	@Autowired
	private BaseService<Province> baseService;
	
	public String execute(){
		Map<String,String> orders = new HashMap<String, String>();
		orders.put("id", "asc");
		province = baseService.list(null, Province.class, orders);
		return "success";
	}

	public List<Province> getProvince() {
		return province;
	}

	public void setProvince(List<Province> province) {
		this.province = province;
	}

	public BaseService<Province> getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService<Province> baseService) {
		this.baseService = baseService;
	}
	
}
