package com.xu.contact.dao;

import java.util.List;
import java.util.Map;

import com.xu.common.dao.BaseDao;
import com.xu.contact.domain.Contact;

public interface ContactDao extends BaseDao<Contact> {

	public void updateMainContact(int cust_id);
	
	public List<Contact> listContactByUser(int userId, int offset, int pageSize);
	/**
	 * 按条件查询某个用户拥有的联系人
	 * @param userId
	 * @param offset
	 * @param pageSize
	 * @param conditionsEq
	 * @param conditionLike
	 * @param orLike
	 * @return
	 */
	public List<Contact> listContacts(int offset, int pageSize
			, Map<String,Object> conditionsEq, Map<String,Object> conditionLike, 
			Map<String,List<Object>> orLike, Map<String, List<Object>> orEq);
}
