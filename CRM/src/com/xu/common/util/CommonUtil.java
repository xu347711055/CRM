package com.xu.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

	public static String dateConvert(Date date){
		if(date!=null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return format.format(date).replace(" ", "T");
		}
		return null;
	}
}
