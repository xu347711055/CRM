package com.xu.user.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.xu.common.dao.BaseDaoImp;
import com.xu.privilege.domain.Auth;
import com.xu.role.domain.Role;
import com.xu.user.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImp<User> implements UserDao {

	@Override
	public List<Auth> getAuthByUser(Integer userId) {
		return this.template.execute(new HibernateCallback<List<Auth>>() {

			@Override
			public List<Auth> doInHibernate(Session session) throws HibernateException {
				StringBuilder sql = new StringBuilder();
				sql.append("select * from auth a,user u,role r,role_auth r_a ");
				sql.append("WHERE a.id=r_a.auths_id AND ");
				sql.append("r.id=r_a.roles_id AND u.id=?");
				Query query = session.createSQLQuery(sql.toString()).addEntity(Auth.class);
				query.setInteger(0,userId);
				return query.list();
				
			}
			
		});
	}
	
	@Override
	public List<Role> getRoleByUser(Integer userId) {
		return this.template.execute(new HibernateCallback<List<Role>>() {

			@Override
			public List<Role> doInHibernate(Session session) throws HibernateException {
				String sql = "select * from role r inner join user_role ur on r.id=ur.roles_id and ur.users_id=?";
				SQLQuery query = session.createSQLQuery(sql).addEntity(Role.class);
				query.setInteger(0,userId);
				return query.list();
			}
			
		});
	}
	
}
