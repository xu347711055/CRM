package com.xu.contact.service;

import java.util.List;

import com.xu.common.page.PageVO;
import com.xu.common.service.BaseService;
import com.xu.contact.domain.Contact;

public interface ContactService extends BaseService<Contact> {

	public void saveContact(Contact contact);
		
	public List<Contact> getContactsByUser(int userId);
	
	public PageVO<Contact> getContactsByUser(int userId, PageVO<Contact> pagevo);
}
