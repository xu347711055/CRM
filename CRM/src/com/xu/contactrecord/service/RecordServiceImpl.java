package com.xu.contactrecord.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xu.common.page.PageVO;
import com.xu.common.service.BaseServiceImpl;
import com.xu.common.util.SpringUtil;
import com.xu.contactrecord.dao.RecordDao;
import com.xu.contactrecord.domain.ContactRecord;

@Service
public class RecordServiceImpl extends BaseServiceImpl<ContactRecord> implements RecordService {
	
	@Autowired
	private RecordDao recordDao;
	
	@Override
	public PageVO<ContactRecord> getRecordsByUser(int userId, PageVO<ContactRecord> pagevo) {
		List<ContactRecord> list = this.recordDao.listRecordByUser(userId, pagevo.getOffset(), pagevo.getPageSize());
		pagevo.setData(list);
		return pagevo;
	}

	@Override
	public PageVO<ContactRecord> getRecordsByConditions(PageVO<ContactRecord> pagevo, Map<String, Object> conditionsEq,
			Map<String, Object> conditionLike, Map<String, List<Object>> orLike, Map<String, List<Object>> orEq) {
		 List<ContactRecord> contacts = recordDao.listContacts(pagevo.getOffset(), pagevo.getPageSize(), 
				conditionsEq, conditionLike, orLike, orEq);
		 pagevo.setData(contacts);
		 return pagevo;
	}

	public static void main(String[] args) {
		RecordService service = SpringUtil.getBean(RecordService.class);
		PageVO<ContactRecord> pagevo = new PageVO<>();
		Map<String, Object> conditionsEq = new HashMap<>();
//		conditionsEq.put("u.id", 1);
		Map<String, Object> conditionLike = new HashMap<>();
		conditionLike.put("contactName", "小");
		Map<String, List<Object>> orLike = new HashMap<>();
		Map<String, List<Object>> orEq = new HashMap<>();
		
		pagevo = service.getRecordsByConditions(pagevo, conditionsEq, conditionLike, orLike, orEq);
		for(ContactRecord record:pagevo.getData()){
			System.out.println(record);
		}
	}
}
