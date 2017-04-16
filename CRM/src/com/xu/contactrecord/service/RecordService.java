package com.xu.contactrecord.service;

import com.xu.common.page.PageVO;
import com.xu.common.service.BaseService;
import com.xu.contactrecord.domain.ContactRecord;

public interface RecordService extends BaseService<ContactRecord> {

	public PageVO<ContactRecord> getRecordsByUser(int userId, PageVO<ContactRecord> pagevo);
}
