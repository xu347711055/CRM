package com.xu.customer.service;

import java.util.List;
import java.util.Map;

import com.xu.common.domain.Dictionary;
import com.xu.common.domain.PieChartData;
import com.xu.common.page.PageVO;
import com.xu.common.service.BaseService;
import com.xu.customer.domain.Customer;

public interface CustService extends BaseService<Customer> {
	
	public PageVO<Customer> listCustByUser(Map<String,String> alias,
			Map<String,Object> conditions, PageVO<Customer> pagevo, Map<String, String> orders);

	public List<Customer> listCustByUser(Map<String,String> alias,
			Map<String,Object> conditions, Map<String, String> orders);
	
	public int getCountByDic(Dictionary dic);

	public PageVO<Customer> listCustByUserEqAndLike(Map<String,String> alias, 
			Map<String,Object> conditionsEq, PageVO<Customer> pagevo, 
			Map<String, String> orders,Map<String,Object> conditionsLike,
			String betweenPropertyName, Object betweenBegin, Object betweenEnd);
	
	public int addStrategy(int custId, String content);
	
	public List<PieChartData> getPieChartData(String custAlias, String userAlias, Map<String,Object> conditions
			, String groupCol) throws Exception;
	
	public void delCust(int custId, int userId);
	
	public void adminDelCust(int custId);
	
	public PageVO<Customer> listByPageWithState(PageVO<Customer> pagevo, int state);
}
