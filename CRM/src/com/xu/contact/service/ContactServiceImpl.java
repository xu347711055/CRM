package com.xu.contact.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.criterion.Order;
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
		contactDao.updateMainContact(contact.getCust().getId());
		if(contact.getId()!=0){
			contactDao.merge(contact);
		}else{
			contactDao.add(contact);
		}
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

	@Override
	public PageVO<Contact> listContactEqAndLike(PageVO<Contact> pagevo, Map<String, Object> conditionEq,
			Map<String, Object> conditionLike, Map<String, String> orders) {
		Order[] ordersArray = toOrderArray(orders);
		List<Contact> custs = this.contactDao.listByPageEqAndLike(Contact.class,pagevo.getOffset(), 
				pagevo.getPageSize(), conditionEq, conditionLike, ordersArray);
		pagevo.setData(custs);
		return pagevo;
	}
	
	@Override
	public PageVO<Contact> listContactEqAndLike(PageVO<Contact> pagevo, Map<String, Object> conditionEq,
			Map<String, Object> conditionLike, Map<String,List<Object>> orLike, 
			Map<String,List<Object>> orEq,Map<String, String> orders) {
		Order[] ordersArray = toOrderArray(orders);
		List<Contact> custs = this.contactDao.listByPageEqAndLike(Contact.class,pagevo.getOffset(), 
				pagevo.getPageSize(), conditionEq, conditionLike, orLike, orEq, ordersArray);
		pagevo.setData(custs);
		return pagevo;
	}
	
	private Order[] toOrderArray(Map<String, String> orders) {
		List<Order> orderList = new ArrayList<Order>();
		Order[] orderArray = new Order[] {};
		if (orders != null) {
			for (Map.Entry<String, String> entry : orders.entrySet()) {
				if ("asc".equalsIgnoreCase(entry.getValue())) {
					orderList.add(Order.asc(entry.getKey()));
				}

			}
			orderArray = orderList.toArray(new Order[orders.size()]);
		}
		return orderArray;
	}

	@Override
	public PageVO<Contact> getContacts(PageVO<Contact> pagevo, Map<String, Object> conditionEq,
			Map<String, Object> conditionLike, Map<String, List<Object>> orLike, Map<String,List<Object>> orEq) {
		
		pagevo.setData(this.contactDao.listContacts(pagevo.getOffset(), pagevo.getPageSize()
				, conditionEq, conditionLike, orLike, orEq));
		return pagevo;
	}
	
	public static void main(String[] args) {
		ContactService service = SpringUtil.getBean(ContactService.class);
		PageVO<Contact> pagevo = new PageVO<>();
		HashMap<String, Object> conditionEq = new HashMap<>();
		conditionEq.put("qqNum", "124785436");
		HashMap<String, Object> conditionLike = new HashMap<>();
		conditionLike.put("telephone", "1324784245");
		HashMap<String, List<Object>> orEq = new HashMap<>();
		List<Object> list = new ArrayList<>();
		Customer customer = new Customer();
		customer.setId(1);
		list.add(customer);
		orEq.put("cust", list);
		pagevo = service.listContactEqAndLike(pagevo, null, null,null, orEq, null);
		for(Contact c : pagevo.getData()){
			System.out.println(c);
		}
	}
	
}
