package com.xu.contact.dao;

import java.util.List;

import com.xu.common.dao.BaseDao;
import com.xu.contact.domain.Contact;

public interface ContactDao extends BaseDao<Contact> {

	public void updateMainContact();
	
	public List<Contact> listContactByUser(int userId, int offset, int pageSize);
}
