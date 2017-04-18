package com.xu.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xu.common.page.PageVO;

public interface BaseService<T> {

	public Serializable add(T entity);

	public void delete(T entity);

	public void update(T entity);

	public T merge(T entity);
	
	public T get(Class<T> entityClass, Serializable id);
	
	public T get(Class<T> entityClass,String propertyName,Object value);

	public List<T> list(Map<String,Object> conditions, Class<T> entityClass,
			Map<String,String> orders);
	
	public List<T> listLike(Map<String,Object> conditions, Class<T> entityClass,
			Map<String,String> orders);

	public PageVO<T> listByPage(Class<T> entityClass,PageVO<T> pagevo,Map<String,Object> conditions,Map<String, String> orders);
	/**
	 * 写sql语句
	 * @return
	 */
	public List<Object> listBySql(String sql);
	
}
