package com.app.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	public final static int FIELD_YEAR   = Calendar.YEAR;
	public final static int FIELD_MONTH  = Calendar.MONTH;
	public final static int FIELD_DAY    = Calendar.DAY_OF_MONTH;
	public final static int FIELD_HOUR   = Calendar.HOUR_OF_DAY;
	public final static int FIELD_MINUTE = Calendar.MINUTE;
	public final static int FIELD_SECOND = Calendar.SECOND;

	public final static String FM_PATTEN_YYYY                = "yyyy";
	public final static String FM_PATTEN_MM                  = "MM";
	public final static String FM_PATTEN_DD                  = "dd";
	public final static String FM_PATTEN_HH                  = "HH";
	public final static String FM_PATTEN_HHMM                = "HHmm";
	public final static String FM_PATTEN_YYYYMM              = "yyyyMM";
	public final static String FM_PATTEN_YYYYMMDD            = "yyyyMMdd";
	public final static String FM_PATTEN_YYYYMMDDHH          = "yyyyMMddHH";
	public final static String FM_PATTEN_YYYYMMDDHHMI        = "yyyyMMddHHmm";
	public final static String FM_PATTEN_YYYYMMDDHHMISS      = "yyyyMMddHHmmss";
	public final static String FM_PATTEN_YYYYMMDDHHMISSSSS      = "yyyyMMddHHmmssSSS";
	public final static String FM_PATTEN_YYYY_MM             = "yyyy-MM";
	public final static String FM_PATTEN_YYYY_MM_DD          = "yyyy-MM-dd";
	public final static String FM_PATTEN_YYYY_MM_DD_HH       = "yyyy-MM-dd HH";
	public final static String FM_PATTEN_YYYY_MM_DD_HH_MI    = "yyyy-MM-dd HH:mm";
	public final static String FM_PATTEN_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";
	

	public static String formatDate(Object date, String pattern){
		
		Date dt = null;
		if(date == null){
			dt = new Date();
			//return null;
		}else if(date instanceof Date){
			dt = (Date)date;
		}else if(date instanceof Calendar){
			dt = ((Calendar)date).getTime();
		}else if(date instanceof String){
			dt = convertDate((String)date);
		}

		String value = null;
		
		if(dt != null){
			SimpleDateFormat dateFormat = null;
	    	try{
	    		dateFormat = new SimpleDateFormat(pattern, Locale.KOREA);
	    	}catch(Exception e){
	    		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
	    	}
	    	
	    	
			try{
				value = dateFormat.format(dt);
			}catch(Exception e){}
		}
		
		if(value == null) value = "";
		
		return value;
	}
	
	public static Date convertDate(String date) {
		if(date == null){
			return null;
		}else{
			date = date.replaceAll("-","").replaceAll(" ","").replaceAll(":","");
		}
		
		if(date.length() > 14)
			date = date.substring(0, 14);
		
		Calendar cal = Calendar.getInstance(Locale.KOREA);
		cal.set(1, 0, 1, 0, 0, 0);	//�ϴ� 0001-01-01�� �ʱ�ȭ ���� ���´�. 2011.09.15.kwbae
		

		try{
			switch(date.length()){
				case 4:
					cal.set(Integer.parseInt(date)
							,0
							,1
							,0
							,0
							,0);
					break;
				
				case 6:
					cal.set(Integer.parseInt(date.substring(0,4))
							,Integer.parseInt(date.substring(4))-1
							,1
							,0
							,0
							,0);
					break;
					
				case 8:
					cal.set(Integer.parseInt(date.substring(0,4))
							,Integer.parseInt(date.substring(4,6))-1
							,Integer.parseInt(date.substring(6))
							,0
							,0
							,0);
					break;
					
				case 10:
					cal.set(Integer.parseInt(date.substring(0,4))
							,Integer.parseInt(date.substring(4,6))-1
							,Integer.parseInt(date.substring(6,8))
							,Integer.parseInt(date.substring(8))
							,0
							,0);
					break;
					
				case 12:
					cal.set(Integer.parseInt(date.substring(0,4))
							,Integer.parseInt(date.substring(4,6))-1
							,Integer.parseInt(date.substring(6,8))
							,Integer.parseInt(date.substring(8,10))
							,Integer.parseInt(date.substring(10))
							,0);
					break;
					
				case 14:				
					cal.set(Integer.parseInt(date.substring(0,4))
							,Integer.parseInt(date.substring(4,6))-1
							,Integer.parseInt(date.substring(6,8))
							,Integer.parseInt(date.substring(8,10))
							,Integer.parseInt(date.substring(10,12))
							,Integer.parseInt(date.substring(12)));
					break;
			}
		}catch(Exception e){}
		
		return cal.getTime();
	}
	
	public static Calendar convertCalendar(String date){
		if(date == null){
			date = "";
		}else{
			date = date.replaceAll("-","").replaceAll(" ","").replaceAll(":","");
		}
		Calendar cal = Calendar.getInstance(Locale.KOREA);

		try{
			switch(date.length()){
				case 4:
					cal.set(Integer.parseInt(date)
							,0
							,1
							,0
							,0
							,0);
					break;
				
				case 6:
					cal.set(Integer.parseInt(date.substring(0,4))
							,Integer.parseInt(date.substring(4))-1
							,1
							,0
							,0
							,0);
					break;
					
				case 8:
					cal.set(Integer.parseInt(date.substring(0,4))
							,Integer.parseInt(date.substring(4,6))-1
							,Integer.parseInt(date.substring(6))
							,0
							,0
							,0);
					break;
					
				case 10:
					cal.set(Integer.parseInt(date.substring(0,4))
							,Integer.parseInt(date.substring(4,6))-1
							,Integer.parseInt(date.substring(6,8))
							,Integer.parseInt(date.substring(8))
							,0
							,0);
					break;
					
				case 12:
					cal.set(Integer.parseInt(date.substring(0,4))
							,Integer.parseInt(date.substring(4,6))-1
							,Integer.parseInt(date.substring(6,8))
							,Integer.parseInt(date.substring(8,10))
							,Integer.parseInt(date.substring(10))
							,0);
					break;
					
				case 14:
					cal.set(Integer.parseInt(date.substring(0,4))
							,Integer.parseInt(date.substring(4,6))-1
							,Integer.parseInt(date.substring(6,8))
							,Integer.parseInt(date.substring(8,10))
							,Integer.parseInt(date.substring(10,12))
							,Integer.parseInt(date.substring(12)));
					break;
			}
		}catch(Exception e){}
		
		return cal;
	}
	

	/**
	 * "00001122" - > "0000-11-22"
	 * @param date
	 * @return
	 */
	public static String format(String date){
		return format(date, false);
	}
	
	/**
	 *
	 * @param date
	 * @param toSystemFormat
	 * @return
	 */
	public static String format(String date, boolean toSystemFormat){
		String[] sep = null;
		if(toSystemFormat){
			sep = new String[]{"","",""};
		}else{
			sep = new String[]{"-"," ",":"};
		}
		
		try{
			date = date.replaceAll("-","").replaceAll(" ","").replaceAll(":","");
			Long.parseLong(date);
			
			switch(date.length()){
				case 6:
					date = date.substring(0,4) + sep[0] + date.substring(4);
					break;
					
				case 8:
					date = date.substring(0,4) + sep[0] + date.substring(4,6) + sep[0] + date.substring(6);
					break;
					
				case 10:
					date = date.substring(0,4) + sep[0] + date.substring(4,6) + sep[0] + date.substring(6,8) + sep[1]
					      + date.substring(8);
					break;
					
				case 12:
					date = date.substring(0, 4) + sep[0] + date.substring(4,6) + sep[0] + date.substring(6,8) + sep[1]
					      + date.substring(8,10) + sep[2] + date.substring(10);
					break;
					
				case 14:
					date = date.substring(0, 4) + sep[0] + date.substring( 4, 6) + sep[0] + date.substring(6,8) + sep[1]
					      + date.substring(8,10) + sep[2] + date.substring(10,12) + sep[2] + date.substring(12);
					break;
				
			}
		}catch(Exception e){
			date = "";
		}
		
		return date;
	}
	
	public static String getDate(){
		return formatDate(null, FM_PATTEN_YYYY_MM_DD);
	}
	
	public static String getBeforeDate(int before){
		Calendar cal = Calendar.getInstance(Locale.KOREA);
		cal.add(FIELD_DAY, -before);
		return formatDate(cal, FM_PATTEN_YYYY_MM_DD);
	}
	
	public static String getBeforeMonth(int before){
		Calendar cal = Calendar.getInstance(Locale.KOREA);
		cal.add(FIELD_MONTH, -before);
		return formatDate(cal, FM_PATTEN_YYYY_MM_DD);
	}
	
	public static String getBeforeDate(String date, int before){
		Calendar cal = Calendar.getInstance(Locale.KOREA);
		cal.setTime(convertDate(date));
		cal.add(FIELD_DAY, -before);
		return formatDate(cal, FM_PATTEN_YYYY_MM_DD);
	}
	
	public static String getBeforeMonth(String date, int before){
		Calendar cal = Calendar.getInstance(Locale.KOREA);
		cal.setTime(convertDate(date));
		cal.add(FIELD_MONTH, -before);
		return formatDate(cal, FM_PATTEN_YYYY_MM_DD);
	}
	
	public static int getLastDate(int  yyyy, int mm, int dd) {
	      //int []id=new int[]{yyyy,mm,dd};
	      Calendar cal = Calendar.getInstance();
	      cal.set(yyyy, mm-1, dd);
	      return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	
	public static boolean isLastDayOfMonth(Calendar cal) {
		
		if(cal.get(Calendar.DAY_OF_MONTH) == cal.getActualMaximum(Calendar.DAY_OF_MONTH))
			return true;
		else
			return false;
	}
	
	public static String toTime(int duration) {
		int s = duration%60;
		int m = duration/60%60;
		int h = duration/60/60;
		
		return String.format("%02d:%02d:%02d", h, m, s);
	}	
	
	public static Date getIntervalDate(Date date)
	{
		if(date == null) return null;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		int interval = (cal.get(FIELD_MINUTE)/15) * 15;
		cal.set(FIELD_MINUTE, interval);
		cal.set(FIELD_SECOND, 0);
		
		return cal.getTime();
	}
	
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		return cal.get(Calendar.YEAR);
	}
	
	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		return cal.get(Calendar.MONTH);
	}
	
	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		return cal.get(Calendar.DATE);
	}
}