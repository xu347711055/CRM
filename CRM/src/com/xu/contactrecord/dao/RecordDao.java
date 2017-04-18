package com.xu.contactrecord.dao;

import java.util.List;
import java.util.Map;

import com.xu.common.dao.BaseDao;
import com.xu.contact.domain.Contact;
import com.xu.contactrecord.domain.ContactRecord;

public interface RecordDao extends BaseDao<ContactRecord> {
	
	public List<ContactRecord> listRecordByUser(int userId, int offset, int pageSize);
	
	/**
	 * 按条件查询某个用户拥有的联系记录
	 * @param userId
	 * @param offset
	 * @param pageSize
	 * @param conditionsEq
	 * @param conditionLike
	 * @param orLike
	 * @return
	 */
	public List<ContactRecord> listContacts(int offset, int pageSize
			, Map<String,Object> conditionsEq, Map<String,Object> conditionLike, 
			Map<String,List<Object>> orLike, Map<String, List<Object>> orEq);
}
