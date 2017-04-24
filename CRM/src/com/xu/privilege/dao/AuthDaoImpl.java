package com.xu.privilege.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.xu.common.dao.BaseDaoImp;
import com.xu.privilege.domain.Auth;

@Repository
public class AuthDaoImpl extends BaseDaoImp<Auth> implements AuthDao {

	@Override
	public List<Auth> listAuthByRoleId(int roleId) {
		return this.template.execute(new HibernateCallback<List<Auth>>() {
			
			@Override
			public List<Auth> doInHibernate(Session session) throws HibernateException {
				String sql = "SELECT * FROM auth a,role_auth r_a WHERE a.id=r_a.auths_id AND r_a.roles_id=?";
				SQLQuery query = session.createSQLQuery(sql);
				query.addEntity(Auth.class);
				query.setInteger(0, roleId);
				return query.list();
			}
		});
	}

}
