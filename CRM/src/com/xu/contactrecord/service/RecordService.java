package com.xu.contactrecord.service;

import java.util.List;
import java.util.Map;

import com.xu.common.page.PageVO;
import com.xu.common.service.BaseService;
import com.xu.contactrecord.domain.ContactRecord;

public interface RecordService extends BaseService<ContactRecord> {

	public PageVO<ContactRecord> getRecordsByUser(int userId, PageVO<ContactRecord> pagevo);
	
	public PageVO<ContactRecord> getRecordsByConditions(PageVO<ContactRecord> pagevo, Map<String, Object> conditionsEq,
			Map<String, Object> conditionLike, Map<String, List<Object>> orLike, Map<String, List<Object>> orEq);
}
