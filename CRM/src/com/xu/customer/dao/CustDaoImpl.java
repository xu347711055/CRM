package com.xu.customer.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.xu.common.dao.BaseDaoImp;
import com.xu.customer.domain.Customer;

@Repository
public class CustDaoImpl extends BaseDaoImp<Customer> implements CustDao {

	@Override
	public List<Customer> listCustByUser(Map<String,String> alias, Criterion[] criterion, int offset, int pageSize, Order...orders) {
		return this.template.execute(new HibernateCallback<List<Customer>>() {

			@Override
			public List<Customer> doInHibernate(Session session) throws HibernateException {
				Criteria c = session.createCriteria(Customer.class);
				if(alias!=null){
					for(Map.Entry<String, String> entry:alias.entrySet()){
						c.createAlias(entry.getKey(), entry.getValue());
					}
				}
				
				if(criterion!=null){
					for(Criterion crion: criterion){
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

	@Override
	public List<Customer> listCustByUser(Map<String, String> alias, Criterion[] criterion, Order... orders) {
		return this.template.execute(new HibernateCallback<List<Customer>>() {

			@Override
			public List<Customer> doInHibernate(Session session) throws HibernateException {
				Criteria c = session.createCriteria(Customer.class);
				if(alias!=null){
					for(Map.Entry<String, String> entry:alias.entrySet()){
						c.createAlias(entry.getKey(), entry.getValue());
					}
				}
				if(criterion!=null){
					for(Criterion crion: criterion){
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

	@Override
	public int getCountByDic(String key, String value) {
		return this.template.execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				System.out.println("value--"+value);
				String sql = "select count(id) from customer where ?=?";
				SQLQuery query = session.createSQLQuery(sql);
				query.setString(0, key);
				query.setString(1, value);
				BigInteger result = (BigInteger) query.uniqueResult();
				return result.intValue();
			}
		});
	}

	@Override
	public List<Customer> listCustByUserEqAndLike(Map<String, String> alias, int offset, int pageSize,
			Map<String, Object> conditionsEq, Map<String, Object> conditionsLike, Order... orders) {
		return this.template.execute(new HibernateCallback<List<Customer>>() {

			@Override
			public List<Customer> doInHibernate(Session session) throws HibernateException {
				Criteria c = session.createCriteria(Customer.class);
				if(alias!=null){
					for(Map.Entry<String, String> entry:alias.entrySet()){
						c.createAlias(entry.getKey(), entry.getValue());
					}
				}
				for(Entry<String, Object> entry : conditionsEq.entrySet()){
					c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
				}
				for(Entry<String, Object> entry : conditionsLike.entrySet()){
					System.out.println("*************like:"+entry.getValue()+"%");
					c.add(Restrictions.like(entry.getKey(), entry.getValue()+"%"));
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
	public Integer addStrategy(int custId, String content) {
		return  this.template.execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				String sql = "update customer set strategy=? where id=?";
				SQLQuery query = session.createSQLQuery(sql);
				query.setString(0, content);
				query.setInteger(1, custId);
				return query.executeUpdate();
			}
		});
		
	}
	
}
