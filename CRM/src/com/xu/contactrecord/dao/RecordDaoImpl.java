package com.xu.contactrecord.dao;

import java.util.List;

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
				sql.append("AND u.id=?");
				Query query = session.createSQLQuery(sql.toString()).addEntity("ct", ContactRecord.class);
				query.setInteger(0, userId);
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
	}

}
