package com.xu.customer.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.xu.common.dao.BaseDaoImp;
import com.xu.common.domain.PieChartData;
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
				System.out.println("****************************");
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
			Map<String, Object> conditionsEq, Map<String, Object> conditionsLike,
			String betweenPropertyName, Object betweenBegin, Object betweenEnd, Order... orders) {
		return this.template.execute(new HibernateCallback<List<Customer>>() {

			@Override
			public List<Customer> doInHibernate(Session session) throws HibernateException {
				Criteria c = session.createCriteria(Customer.class);
				if(alias!=null){
					for(Map.Entry<String, String> entry:alias.entrySet()){
						c.createAlias(entry.getKey(), entry.getValue());
					}
				}
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
				if(betweenPropertyName!=null&&betweenBegin!=null&&betweenEnd!=null){
					c.add(Restrictions.between(betweenPropertyName, betweenBegin, betweenEnd));
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

	@Override
	public Integer countEqAndLike(Map<String, String> alias, Map<String, Object> conditionsEq,
			Map<String, Object> conditionsLike, String betweenPropertyName, Object betweenBegin, Object betweenEnd) {
		return this.template.execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				Criteria c = session.createCriteria(Customer.class);
				if(alias!=null){
					for(Map.Entry<String, String> entry:alias.entrySet()){
						c.createAlias(entry.getKey(), entry.getValue());
					}
				}
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
				if(betweenPropertyName!=null&&betweenBegin!=null&&betweenEnd!=null){
					c.add(Restrictions.between(betweenPropertyName, betweenBegin, betweenEnd));
				}
				c.setProjection(Projections.rowCount());
				Long countL = (Long)c.uniqueResult();
				return countL.intValue();
			}
		});
	}

	@Override
	public List<PieChartData> listCustsByUserGroup(String custAlias, String userAlias, 
			Map<String, Object> conditions, String groupCol) throws Exception{
		return this.template.execute(new HibernateCallback<List<PieChartData>>() {

			@Override
			public List<PieChartData> doInHibernate(Session session) {
				
				ResultSet resultSet = session.doReturningWork(new ReturningWork<ResultSet>() {

					@Override
					public ResultSet execute(Connection conn) throws SQLException {
						StringBuilder sql = new StringBuilder();
						sql.append("select count(*) count,"+groupCol+" from ");
						sql.append("customer ").append(custAlias).append(",customer_owner ");
						sql.append("where ").append(custAlias).append(".id=customer_owner.cust ");
						if(conditions!=null){
							int size = conditions.entrySet().size();
							for(Entry<String,Object> entry : conditions.entrySet()){
								sql.append("and ").append(entry.getKey()+"="+entry.getValue()+" ");
								size--;
								if(size>0){
									sql.append("and ");
								}
							}
						}
						if(!StringUtils.isEmpty(groupCol)){
							sql.append("group by "+groupCol);
						}
						System.out.println("sql:"+sql);
						PreparedStatement preparedStatement=conn.prepareStatement(sql.toString());
						ResultSet resultSet=preparedStatement.executeQuery();
						return resultSet;
					}
				});
				List<PieChartData> pieDatas = new ArrayList<>();
				try {
					while(resultSet.next()){
						PieChartData data = new PieChartData();
						data.setY(resultSet.getInt("count"));
						data.setName(resultSet.getString(groupCol));
						pieDatas.add(data);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return pieDatas;
			}
		});
	}

	@Override
	public void delCust(int custId, int userId) {
		this.template.execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				String sql = "delete from customer_owner where cust=? and owner=?";
				SQLQuery query = session.createSQLQuery(sql);
				query.setInteger(0, custId);
				query.setInteger(1, userId);
				return query.executeUpdate();
			}
		});
	}

	@Override
	public void adminDelCust(int custId) {
		this.template.execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				String sql = "update customer set state=0 where id=?";
				SQLQuery query = session.createSQLQuery(sql);
				query.setInteger(0, custId);
				return query.executeUpdate();
			}
		});
	}

	@Override
	public List<Customer> listByPageWithState(int state, int pageSize, int offset) {
		return this.template.execute(new HibernateCallback<List<Customer>>() {

			@Override
			public List<Customer> doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery("select * from customer where state=? limit ?,?");
				query.addEntity(Customer.class);
				query.setInteger(0, state);
				query.setInteger(1, offset);
				query.setInteger(2, pageSize);
				return query.list();
			}
			
		});
	}

	@Override
	public Integer countByPageWithState(int state) {
		return this.template.execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery("select count(id) from customer where state=?");
				query.setInteger(0, state);
				BigInteger result = (BigInteger) query.uniqueResult();
				return result.intValue();
			}
		});
	}
	
}
