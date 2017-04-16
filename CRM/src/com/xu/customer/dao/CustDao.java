package com.xu.customer.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.xu.common.dao.BaseDao;
import com.xu.customer.domain.Customer;

public interface CustDao extends BaseDao<Customer> {
	
	public List<Customer> listCustByUser(Map<String,String> alias, Criterion[] criterion, int offset, int pageSize, Order...orders);
	
	public List<Customer> listCustByUser(Map<String,String> alias, Criterion[] criterion, Order...orders);

	public int getCountByDic(String key , String value);
	
	public List<Customer> listCustByUserEqAndLike(Map<String,String> alias,int offset, int pageSize,  
			 Map<String, Object> conditionsEq, Map<String, Object> conditionsLike, Order...orders);
}
