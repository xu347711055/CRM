package com.xu.contactrecord.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xu.common.page.PageVO;
import com.xu.common.service.BaseServiceImpl;
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

}
