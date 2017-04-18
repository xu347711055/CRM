package com.xu.contact.service;

import java.util.List;
import java.util.Map;

import com.xu.common.page.PageVO;
import com.xu.common.service.BaseService;
import com.xu.contact.domain.Contact;

public interface ContactService extends BaseService<Contact> {

	public void saveContact(Contact contact);
		
	public List<Contact> getContactsByUser(int userId);
	
	public PageVO<Contact> getContactsByUser(int userId, PageVO<Contact> pagevo);
	
	public PageVO<Contact> getContacts(PageVO<Contact> pagevo,Map<String, Object> conditionEq,
			Map<String, Object> conditionLike,  Map<String, List<Object>> orLike, 
			Map<String,List<Object>> orEq);

	public PageVO<Contact> listContactEqAndLike(PageVO<Contact> pagevo, Map<String,Object> conditionEq,
			Map<String,Object> conditionLike, Map<String,String> orders);
	/**
	 * 
	 * @param pagevo
	 * @param conditionEq	and连接的eq条件
	 * @param conditionLike	and连接的like条件
	 * @param orLike	or连接的like条件
	 * @param orEq	or连接的eq条件
	 * @param orders	排序
	 * @return
	 */
	public PageVO<Contact> listContactEqAndLike(PageVO<Contact> pagevo, Map<String, Object> conditionEq,
			Map<String, Object> conditionLike, Map<String, List<Object>> orLike, 
			Map<String, List<Object>> orEq, Map<String, String> orders);
	
}
