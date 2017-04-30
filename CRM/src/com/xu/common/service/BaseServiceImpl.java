package com.xu.common.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xu.common.dao.BaseDao;
import com.xu.common.page.PageVO;
@Service("baseService")
public class BaseServiceImpl<T> implements BaseService<T> {
	@Autowired
	@Qualifier("baseDao")
	private BaseDao<T> baseDao;
	@Transactional
	@Override
	public Serializable add(T entity) {
		return baseDao.add(entity);
	}

	@Transactional
	@Override
	public void delete(T entity) {
		baseDao.delete(entity);
	}

	@Transactional
	@Override
	public void update(T entity) {
		baseDao.update(entity);

	}

	@Override
	public T get(Class<T> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public T get(Class<T> entityClass, String propertyName, Object value) {
		return baseDao.get(entityClass, propertyName, value);
	}

	@Override
	public List<T> list(Map<String, Object> conditions, Class<T> entityClass,
			Map<String, String> orders) {
		Criterion[] criterions = null;
		if (conditions != null) {
			criterions = new Criterion[] { Restrictions.allEq(conditions) };
		}
		return baseDao.list(criterions, entityClass, toOrderArray(orders));
	}

	@Override
	public PageVO<T> listByPage(Class<T> entityClass, PageVO<T> pagevo,
			Map<String, Object> conditions, Map<String, String> orders) {
		Criterion[] criterions = null;
		if (conditions != null) {
			criterions = new Criterion[] { Restrictions.allEq(conditions) };
		}
		List<T> data = baseDao.listByPage(criterions,entityClass, pagevo.getPageSize(),
				pagevo.getOffset(), toOrderArray(orders));
		int count = baseDao.count(criterions, entityClass);
		pagevo.setData(data);
		pagevo.setTotalRecord(count);
		return pagevo;
	}

	private Order[] toOrderArray(Map<String, String> orders) {
		List<Order> orderList = new ArrayList<Order>();
		Order[] orderArray = new Order[] {};
		if (orders != null) {
			for (Map.Entry<String, String> entry : orders.entrySet()) {
				if ("asc".equalsIgnoreCase(entry.getValue())) {
					orderList.add(Order.asc(entry.getKey()));
				}else if("desc".equalsIgnoreCase(entry.getValue())){
					orderList.add(Order.desc(entry.getKey()));
				}
			}
			orderArray = orderList.toArray(new Order[orders.size()]);
		}
		return orderArray;
	}

	@Override
	public List<Object> listBySql(String sql) {
		return this.baseDao.listBySql(sql);
		
	}

	@Transactional
	@Override
	public T merge(T entity) {
		return this.baseDao.merge(entity);
	}

	@Override
	public List<T> listLike(Map<String, Object> conditions, Class<T> entityClass, Map<String, String> orders) {
		return this.baseDao.listLike(conditions, entityClass, toOrderArray(orders));
	}
	
}
