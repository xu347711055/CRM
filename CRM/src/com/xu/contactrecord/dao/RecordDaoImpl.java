package com.xu.contactrecord.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.xu.common.dao.BaseDaoImp;
import com.xu.contactrecord.domain.ContactRecord;

@Repository
public class RecordDaoImpl extends BaseDaoImp<ContactRecord> implements RecordDao {

	@Override
	public List<ContactRecord> listRecordByUser(int userId, int offset, int pageSize) {
		return this.template.execute(new HibernateCallback<List<ContactRecord>>() {

			@Override
			public List<ContactRecord> doInHibernate(Session session) throws HibernateException {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT * FROM contactrecord ct,customer cust,`user` u,customer_owner co ");
				sql.append("WHERE ct.cust_id=cust.id AND u.id=co.`owner` AND cust.id=co.cust ");
				sql.append("AND u.id=? AND ct.state=1");
				Query query = session.createSQLQuery(sql.toString()).addEntity("ct", ContactRecord.class);
				query.setInteger(0, userId);
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
	}

	@Override
	public List<ContactRecord> listContacts(int offset, int pageSize, Map<String, Object> conditionsEq,
			Map<String, Object> conditionLike, Map<String, List<Object>> orLike, Map<String, List<Object>> orEq) {
		return this.template.execute(new HibernateCallback<List<ContactRecord>>() {

			@Override
			public List<ContactRecord> doInHibernate(Session session) throws HibernateException {
				StringBuilder sql = new StringBuilder();
				
				sql.append("SELECT * FROM contactrecord ct,customer cust,`user` u,customer_owner co ");
				sql.append("WHERE ct.cust_id=cust.id AND u.id=co.`owner` AND cust.id=co.cust ");
				if(conditionsEq!=null){
					for(Entry<String,Object> entry : conditionsEq.entrySet()){
						sql.append("and "+entry.getKey()+"='"+entry.getValue()+"' ");
					}
				}
				if(conditionLike!=null){
					for(Entry<String,Object> entry : conditionLike.entrySet()){
						sql.append("and "+entry.getKey()+" like '"+entry.getValue()+"%' ");
					}
				}
				if(orLike!=null && orLike.size()>0){
					sql.append("and(");
					Set<Entry<String,List<Object>>> entrySet = orLike.entrySet();
					for(Entry<String,List<Object>> entry : entrySet){
						int count = entry.getValue().size();
						for(Object value : entry.getValue()){
							sql.append(entry.getKey()+" like '"+value+"%' ");
							count--;
							if(count!=0){	//最后一个条件后面不需要or
								sql.append("or ");
							}
						}
					}
					sql.append(")");
				}
				if(orEq!=null && orEq.size()>0){
					sql.append("and(");
					Set<Entry<String,List<Object>>> entrySet = orEq.entrySet();
					for(Entry<String,List<Object>> entry : entrySet){
						List<Object> values = entry.getValue();
						int count = values.size();
						for(Object value : values){
							sql.append(entry.getKey()+"='"+value+"' ");
							count--;
							if(count!=0){	//最后一个条件后面不需要or
								sql.append("or ");
							}
						}
						if(values.isEmpty()){	
							sql.append(entry.getKey()+"='"+null+"' ");
						}
					}
					sql.append(")");
				}
				System.out.println("**********sql:"+sql);
				
				Query query = session.createSQLQuery(sql.toString()).addEntity("ct", ContactRecord.class);
				
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
	}
	
}
