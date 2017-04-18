package com.xu.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public interface BaseDao<T> {
	public Serializable add(T entity);
	
	public void delete(T entity);
	
	public void update(T entity);
	
	public T get(Class<T> entityClass, Serializable id);
	
	public T get(Class<T> entityClass,String propertyName,Object value);
	
	public T merge(T entity);
	/**
	 * 
	 * @param criterions 封装查询条件
	 * @param entityClass 返回类型
	 * @return
	 */
	public List<T> list(Criterion[] criterions, Class<T> entityClass,Order ...orders);
	
	public List<T> listLike(Map<String,Object> conditions, Class<T> entityClass,Order ...orders);
	
	public List<T> listByPage(Criterion[] criterions, Class<T> entityClass, int pageSize, int offset, Order ...orders);
	
	public Integer count(Criterion[] criterions, Class<T> entityClass, Order ...orders);
	/**
	 * 写sql语句
	 * @return
	 */
	public List<Object> listBySql(String sql);
	
	public List<Integer> getCustByUser(int userId, int pageSize, int offset);
	
	public List<Integer> getCustByUser(int userId);
	
	public List<T> listByPageEqAndLike(Class<T> entityClass,int offset, int pageSize,  
			 Map<String, Object> conditionsEq, Map<String, Object> conditionsLike, Order...orders);

	public List<T> listByPageEqAndLike(Class<T> entityClass,int offset, int pageSize, Map<String, Object> conditionsEq,
			Map<String, Object> conditionsLike,Map<String, List<Object>> conditionsOrLike, 
			Map<String, List<Object>> conditionsOrEq, Order... orders);
}
