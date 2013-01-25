package com.company.emcare.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";
	public static final String DATE_FULL_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String VERY_BEGINING = "2012-01-01 00:00:00";
	
	public static final String TIME_UNIT_YEAR = "year";
	public static final String TIME_UNIT_MONTH = "month";
	
	public static Timestamp date2Timestamp(String date) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
		return new Timestamp((format.parse(date)).getTime());
	}
	
	public static String getDateString(Date date){
		SimpleDateFormat format = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
		return format.format(date);
	}
	
	public static String getDateStringWithPatten(Date date, String patten){
		SimpleDateFormat format = new SimpleDateFormat(patten);
		return format.format(date);
	}
	
	public static Integer getMonth(Timestamp time){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time.getTime());
		return cal.get(Calendar.MONTH);
	}
	
	public static Integer getYear(Timestamp time){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time.getTime());
		return cal.get(Calendar.YEAR);
	}
	
	 public static int daysBetween(String smdate,String bdate) {  
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	        Calendar cal = Calendar.getInstance();    
	        try {
				cal.setTime(sdf.parse(smdate));
			} catch (ParseException e) {
				e.printStackTrace();
			}    
	        long time1 = cal.getTimeInMillis();                 
	        try {
				cal.setTime(sdf.parse(bdate));
			} catch (ParseException e) {
				e.printStackTrace();
			}    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	       return Integer.parseInt(String.valueOf(between_days));     
	    }
}
