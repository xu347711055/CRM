package com.xu.contactrecord.dao;

import java.util.List;

import com.xu.common.dao.BaseDao;
import com.xu.contactrecord.domain.ContactRecord;

public interface RecordDao extends BaseDao<ContactRecord> {
	
	public List<ContactRecord> listRecordByUser(int userId, int offset, int pageSize);
}
