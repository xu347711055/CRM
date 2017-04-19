package com.xu.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommonUtil {

	public static String dateConvert(Date date){
		if(date!=null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return format.format(date).replace(" ", "T");
		}
		return null;
	}
	
	public static Map<String,Date> getTodayTimeScope() throws ParseException{
		SimpleDateFormat formateToDay = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formateToSecond = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String curDate = formateToDay.format(new Date());
		Date begin = formateToSecond.parse(curDate+" 00:00:00");
		Date end = formateToSecond.parse(curDate+" 23:59:59");
		Map<String,Date> timeMap = new HashMap<>();
		timeMap.put("begin", begin);
		timeMap.put("end", end);
		return timeMap;
	}
}
