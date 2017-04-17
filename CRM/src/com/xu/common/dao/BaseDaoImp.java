package com.xu.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository("baseDao")
public class BaseDaoImp<T> implements BaseDao<T> {
	@Autowired
	protected HibernateTemplate template;
	
	public Serializable add(T entity){
		return template.save(entity);
	}
	
	public void delete(T entity){
		template.delete(entity);
	}
	
	public void update(T entity){
		template.update(entity);
	}
	
	public T get(Class<T> entityClass, Serializable id){
		return template.get(entityClass, id);
	}
	/**
	 * 
	 * @param criterions 封装查询条件
	 * @param entityClass 返回类型
	 * @return
	 */
	public List<T> list(Criterion[] criterions, Class<T> entityClass,Order ...orders){
		return template.execute(new HibernateCallback<List<T>>() {

			@Override
			public List<T> doInHibernate(Session session) throws HibernateException {
				Criteria c = session.createCriteria(entityClass);
				if(criterions!=null){
					for(Criterion crion: criterions){
						if(crion!=null){
							c.add(crion);
						}
					}
				}
				if(orders!=null){
					for( Order order: orders){
						c.addOrder(order);
					}
				}
				return c.list();
			}
		});
	}
	
	public List<T> listByPage(Criterion[] criterions, Class<T> entityClass, int pageSize, int offset, Order ...orders){
		return template.execute(new HibernateCallback<List<T>>() {

			@Override
			public List<T> doInHibernate(Session session) throws HibernateException {
				Criteria c = session.createCriteria(entityClass);
				if(criterions!=null){
					for(Criterion crion: criterions){
						if(crion!=null){
							c.add(crion);
						}
					}
				}
				if(orders!=null){
					for( Order order: orders){
						c.addOrder(order);
					}
				}
				c.setFirstResult(offset);//设置分页开始位置
				c.setMaxResults(pageSize);//设置分页大小
				return c.list();
			}
		});
	}
	
	public Integer count(Criterion[] criterions, Class<T> entityClass, Order ...orders){
		return template.execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				Criteria c = session.createCriteria(entityClass);
				if(criterions!=null){
					for(Criterion crion: criterions){
						if(crion!=null){
							c.add(crion);
						}
					}
				}
				c.setProjection(Projections.rowCount());
				Long countL = (Long)c.uniqueResult();
				return countL.intValue() ;
			}
		});
	}

	@Override
	public T get(Class<T> entityClass, String propertyName, Object value) {
		return  this.template.execute(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				Criteria ca=session.createCriteria(entityClass);
				ca.add(Restrictions.eq(propertyName, value));
				return (T)ca.uniqueResult();
			}
		});
	}

	@Override
	public List<Object> listBySql(String sql) {
		return this.template.execute(new HibernateCallback<List<Object>>() {

			@Override
			public List<Object> doInHibernate(Session session) throws HibernateException {
				List<Object> list = session.createSQLQuery(sql).list();
				return list;
			}
	
		});
	}

	@Override
	public T merge(T entity) {
		return this.template.merge(entity);
	}
	
	@Override
	public List<Integer> getCustByUser(int userId) {
		return this.template.execute(new HibernateCallback<List<Integer>>() {

			@Override
			public List<Integer> doInHibernate(Session session) throws HibernateException {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT co.cust FROM ");
				sql.append("customer_owner co WHERE owner=?");
				Query query = session.createSQLQuery(sql.toString());
				query.setInteger(0, userId);
				List<Integer> list = query.list();
				return list;
			}
		});
	}

	@Override
	public List<Integer> getCustByUser(int userId, int pageSize, int offset) {
		return this.template.execute(new HibernateCallback<List<Integer>>() {

			@Override
			public List<Integer> doInHibernate(Session session) throws HibernateException {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT co.cust FROM ");
				sql.append("customer_owner co WHERE owner=? limit ?,?");
				Query query = session.createSQLQuery(sql.toString());
				query.setInteger(0, userId);
				query.setInteger(1, offset);
				query.setInteger(2, pageSize);
				List<Integer> list = query.list();
				return list;
			}
		});
	}
	
	@Override
	public List<T> listByPageEqAndLike(Class<T> entityClass,int offset, int pageSize, Map<String, Object> conditionsEq,
			Map<String, Object> conditionsLike, Order... orders) {
		return this.template.execute(new HibernateCallback<List<T>>() {

			@Override
			public List<T> doInHibernate(Session session) throws HibernateException {
				Criteria c = session.createCriteria(entityClass);
				if(conditionsEq!=null){
					for(Entry<String, Object> entry : conditionsEq.entrySet()){
						c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
					}
				}
				if(conditionsLike!=null){
					for(Entry<String, Object> entry : conditionsLike.entrySet()){
						c.add(Restrictions.like(entry.getKey(), entry.getValue()+"%"));
					}
				}
				if(orders!=null){
					for( Order order: orders){
						c.addOrder(order);
					}
				}
				return c.list();
			}
		});
	}
	
	@Override
	public List<T> listByPageEqAndLike(Class<T> entityClass,int offset, int pageSize, Map<String, Object> conditionsEq,
			Map<String, Object> conditionsLike,Map<String, List<Object>> conditionsOrLike, 
			Map<String, List<Object>> conditionsOrEq, Order... orders) {
		return this.template.execute(new HibernateCallback<List<T>>() {

			@Override
			public List<T> doInHibernate(Session session) throws HibernateException {
				Criteria c = session.createCriteria(entityClass);
				if(conditionsEq!=null){
					for(Entry<String, Object> entry : conditionsEq.entrySet()){
						c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
					}
				}
				if(conditionsLike!=null){
					for(Entry<String, Object> entry : conditionsLike.entrySet()){
						c.add(Restrictions.like(entry.getKey(), entry.getValue()+"%"));
					}
				}
				if(conditionsOrLike!=null){
					for(Entry<String, List<Object>> entry : conditionsOrLike.entrySet()){
						for(Object value : entry.getValue()){
							c.add(Restrictions.or(Restrictions.like(entry.getKey(), value+"%")));
						}
					}
				}
				if(conditionsOrEq!=null){
					for(Entry<String, List<Object>> entry : conditionsOrEq.entrySet()){
						for(Object value : entry.getValue()){
							c.add(Restrictions.or(Restrictions.eq(entry.getKey(), value)));
						}
					}
				}
				if(orders!=null){
					for( Order order: orders){
						c.addOrder(order);
					}
				}
				return c.list();
			}
		});
	}
}
