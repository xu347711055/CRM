package com.xu.contact.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.xu.common.dao.BaseDaoImp;
import com.xu.contact.domain.Contact;

@Repository
public class ContactDaoImpl extends BaseDaoImp<Contact> implements ContactDao {

	@Override
	public void updateMainContact() {
		this.template.execute(new HibernateCallback<Contact>() {

			@Override
			public Contact doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery("update contact set mainContact=0 where mainContact=1");
				query.executeUpdate();
				return null;
			}
		});
	}

	@Override
	public List<Contact> listContactByUser(int userId, int offset, int pageSize) {
		return this.template.execute(new HibernateCallback<List<Contact>>() {

			@Override
			public List<Contact> doInHibernate(Session session) throws HibernateException {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT * FROM contact ct,customer cust,`user` u,customer_owner co ");
				sql.append("WHERE ct.cust_id=cust.id AND u.id=co.`owner` AND cust.id=co.cust ");
				sql.append("AND u.id=?");
				Query query = session.createSQLQuery(sql.toString()).addEntity("ct", Contact.class);
				query.setInteger(0, userId);
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
	}
	
}
