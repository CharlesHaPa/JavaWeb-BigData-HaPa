package com.sw1408.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;




public class DateUtil {
	public static void main(String[] args){
		try {
			System.out.println(caculateDate("2017-07-21", 6));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String caculateDate(String dateStr,int days) throws ParseException{
		Date date = new Date();
        //注意format的格式要与日期String的格式相匹配
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dBefore = new Date();
        date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -days);  //设置为前一天
        dBefore = calendar.getTime();  
        dateStr = sdf.format(dBefore);
        return dateStr;
	}
}
