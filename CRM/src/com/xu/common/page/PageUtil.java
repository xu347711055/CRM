package com.xu.common.page;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.xu.common.dao.BaseDao;

public class PageUtil {
	public static <T> void executePaging(BaseDao<T> dao,
			Criterion[] criterions, Order[] orders, PageVO<T> page,
			Class<T> type) {
		page.setData(dao.listByPage(criterions, type,
				page.getOffset(), page.getPageSize(), orders));
		page.setTotalRecord(dao.count(criterions, type));
		return;
	}
	public static Criterion likeFull(String propertyName,Object value){
		if(value!=null&&!"".equals(value)){
			return Restrictions.like(propertyName,"%"+value+"%");
		}
		return null;
	}
	
	public static Criterion likeRight(String propertyName,Object value){
		if(value!=null&&!"".equals(value)){
			return Restrictions.like(propertyName,value+"%");
		}
		return null;
	}
	
	public static Criterion likeLeft(String propertyName,Object value){
		if(value!=null&&!"".equals(value)){
			return Restrictions.like(propertyName,"%"+value);
		}
		return null;
	}
	
	
	public static Criterion eq(String propertyName,Object value){
		if(value!=null&&!"".equals(value)){
			return Restrictions.eq(propertyName,value);
		}
		return null;
	}
	
	public static Criterion lt(String propertyName,Object value){
		if(value!=null&&!"".equals(value)){
			return Restrictions.lt(propertyName,value);
		}
		return null;
	}
	
	public static Criterion le(String propertyName,Object value){
		if(value!=null&&!"".equals(value)){
			return Restrictions.le(propertyName,value);
		}
		return null;
	}
	
	public static Criterion gt(String propertyName,Object value){
		if(value!=null&&!"".equals(value)){
			return Restrictions.gt(propertyName,value);
		}
		return null;
	}
	
	public static Criterion ge(String propertyName,Object value){
		if(value!=null&&!"".equals(value)){
			return Restrictions.ge(propertyName,value);
		}
		return null;
	}
	
	
	
}
