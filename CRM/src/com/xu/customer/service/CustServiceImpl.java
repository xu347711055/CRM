package com.xu.customer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xu.common.domain.Dictionary;
import com.xu.common.page.PageVO;
import com.xu.common.service.BaseServiceImpl;
import com.xu.common.util.SpringUtil;
import com.xu.customer.dao.CustDao;
import com.xu.customer.domain.Customer;

@Service
public class CustServiceImpl extends BaseServiceImpl<Customer>  implements CustService {
	@Autowired
	private CustDao custDao;
	
	@Override
	public PageVO<Customer> listCustByUser(Map<String,String> alias, 
			Map<String,Object> conditions, PageVO<Customer> pagevo, Map<String, String> orders) {
		Criterion[] criterion = null;
		if (conditions != null) {
			criterion = new Criterion[] { Restrictions.allEq(conditions) };
		}
		List<Customer> custs = this.custDao.listCustByUser(alias, criterion, pagevo.getOffset(), pagevo.getPageSize(), toOrderArray(orders));
		pagevo.setData(custs);
		return pagevo;
	}
	
	
	
	private Order[] toOrderArray(Map<String, String> orders) {
		List<Order> orderList = new ArrayList<Order>();
		Order[] orderArray = new Order[] {};
		if (orders != null) {
			for (Map.Entry<String, String> entry : orders.entrySet()) {
				if ("asc".equalsIgnoreCase(entry.getValue())) {
					orderList.add(Order.asc(entry.getKey()));
				}

			}
			orderArray = orderList.toArray(new Order[orders.size()]);
		}
		return orderArray;
	}

	@Override
	public List<Customer> listCustByUser(Map<String, String> alias, Map<String, Object> conditions,
			Map<String, String> orders) {
		Criterion[] criterion = null;
		if (conditions != null) {
			criterion = new Criterion[] { Restrictions.allEq(conditions) };
		}
		return this.custDao.listCustByUser(alias, criterion, toOrderArray(orders));
		
	}

	@Override
	public int getCountByDic(Dictionary dic) {
		return this.custDao.getCountByDic(dic.getCode(), dic.getValue());
	}



	@Override
	public PageVO<Customer> listCustByUserEqAndLike(Map<String, String> alias, Map<String, Object> conditionsEq,
			PageVO<Customer> pagevo, Map<String, String> orders, Map<String, Object> conditionsLike,
			String betweenPropertyName, Object betweenBegin, Object betweenEnd ) {
		Order[] ordersArray = toOrderArray(orders);
		List<Customer> custs = this.custDao.listCustByUserEqAndLike(alias, pagevo.getOffset(),
				pagevo.getPageSize(), conditionsEq, conditionsLike, betweenPropertyName, betweenBegin, betweenEnd, ordersArray);
		pagevo.setData(custs);
		pagevo.setTotalRecord(this.custDao.countEqAndLike(alias, conditionsEq, conditionsLike, betweenPropertyName, betweenBegin, betweenEnd));
		return pagevo;
	}

	/**
	 * 添加客户策略
	 */

	@Override
	public int addStrategy(int custId, String content) {
		return this.custDao.addStrategy(custId, content);
	}
	
	public static void main(String[] args) {
		CustService service = SpringUtil.getBean(CustService.class);
		PageVO<Customer> pagevo = new PageVO<>();
		Map<String, Object> conditions = new HashMap<>();
		java.sql.Date date = new java.sql.Date(new Date().getTime());
		System.out.println(date);
		conditions.put("contactDate", date);
		pagevo = service.listByPage(Customer.class, pagevo, conditions, null);
		for(Customer c : pagevo.getData()){
			System.out.println(c);
		}
	}
}
