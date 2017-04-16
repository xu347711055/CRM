package com.xu.contact.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xu.common.page.PageVO;
import com.xu.common.service.BaseServiceImpl;
import com.xu.common.util.SpringUtil;
import com.xu.contact.dao.ContactDao;
import com.xu.contact.domain.Contact;
import com.xu.customer.domain.Customer;

@Service
public class ContactServiceImpl extends BaseServiceImpl<Contact> implements ContactService {
	@Autowired
	private ContactDao contactDao;

	@Transactional
	@Override
	public void saveContact(Contact contact) {
		contactDao.updateMainContact();
		contactDao.add(contact);
	}

	@Override
	public List<Contact> getContactsByUser(int userId) {
		List<Integer> custIds = contactDao.getCustByUser(userId);
		List<Contact> contacts = new ArrayList<>();
		Customer cust = new Customer();
		for(int id : custIds){
			cust.setId(id);
			Contact contact = contactDao.get(Contact.class, "cust", cust);
			if(contact!=null){
				contacts.add(contact);
			}
		}
		return contacts;
	}
	
	public static void main(String[] args) {
		SpringUtil.init("application.xml");
		ContactService service = SpringUtil.getBean(ContactService.class);
		PageVO<Contact> pageVO = new PageVO<Contact>();
		pageVO.setCurPage(1);
		pageVO.setPageSize(1);
		pageVO = service.getContactsByUser(1,pageVO);
		for(Contact cont:pageVO.getData()){
			System.out.println(cont);
		}
	}
	
	@Override
	public PageVO<Contact> getContactsByUser(int userId, PageVO<Contact> pagevo) {
		/*List<Integer> custIds = contactDao.getCustByUser(userId, pagevo.getPageSize(), pagevo.getOffset());
		List<Contact> contacts = new ArrayList<>();
		Customer cust = new Customer();
		Criterion[] criterions = new Criterion[1];
		for(int id : custIds){
			cust.setId(id);
			SimpleExpression eq = Restrictions.eq("cust", cust);
			criterions[0] = eq;
			List<Contact> contact = contactDao.list(criterions, Contact.class, null);
			if(contact!=null){
				contacts.addAll(contact);
			}
		}
		pagevo.setData(contacts);
		return pagevo;*/
		List<Contact> list = contactDao.listContactByUser(userId, pagevo.getOffset(), pagevo.getPageSize());
		pagevo.setData(list);
		return pagevo;
	}
	
}
