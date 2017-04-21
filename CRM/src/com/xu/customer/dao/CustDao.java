package com.xu.customer.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.xu.common.dao.BaseDao;
import com.xu.common.domain.PieChartData;
import com.xu.customer.domain.Customer;

public interface CustDao extends BaseDao<Customer> {
	
	public List<Customer> listCustByUser(Map<String,String> alias, Criterion[] criterion, int offset, int pageSize, Order...orders);
	
	public List<Customer> listCustByUser(Map<String,String> alias, Criterion[] criterion, Order...orders);

	public int getCountByDic(String key , String value);
	
	public List<Customer> listCustByUserEqAndLike(Map<String,String> alias,int offset, int pageSize,  
			 Map<String, Object> conditionsEq, Map<String, Object> conditionsLike, 
			 String betweenPropertyName, Object betweenBegin, Object betweenEnd,Order...orders);
	public Integer countEqAndLike(Map<String,String> alias, Map<String, Object> conditionsEq,
			Map<String, Object> conditionsLike, String betweenPropertyName, Object betweenBegin, 
			Object betweenEnd);
		
	public List<PieChartData> listCustsByUserGroup(String custAlias, String userAlias, 
			Map<String, Object> conditions , String groupCol) throws Exception;
	
	public Integer addStrategy(int custId, String content);
	
	public void delCust(int custId, int userId);
	
	public void adminDelCust(int custId);
	
	public List<Customer> listByPageWithState(int state ,int pageSize, int offset);
	
	public Integer countByPageWithState(int state);
}
