package com.xu.common.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.xu.common.domain.Dictionary;
import com.xu.common.service.BaseService;

/**
 * 读取数据字典
 * @author xu
 *
 */
@ParentPackage("json-default")
@Action("getDicData")
@Result(name="success",type="json",params={"root","data"})
public class GetDicDataAction {
	@Autowired
	@Qualifier("baseService")
	private BaseService<Dictionary> baseService;
	private Map<Object,List<Dictionary>> data = new HashMap<>();
	private String code;
	
	public String execute(){
		List<Object> codeList = baseService.listBySql("SELECT DISTINCT code FROM dictionary");
		Iterator<Object> iterator = codeList.iterator();
		while(iterator.hasNext()){
			Object code = iterator.next();
			Map<String, Object> conditions = new HashMap<>();
			conditions.put("code", code);
			Map<String,String> orders = new HashMap<>();
			orders.put("id", "asc");
			List<Dictionary> list = baseService.list(conditions, Dictionary.class, orders);
			data.put(code, list);
		}
		System.out.println(data);		
		return "success";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Map<Object, List<Dictionary>> getData() {
		return data;
	}

	public void setData(Map<Object, List<Dictionary>> data) {
		this.data = data;
	}

}
