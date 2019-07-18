package com.sw1408.util;


import java.awt.print.Printable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class SeatUtil {

	public static String getNeedPath(String path, int begin,int end){
	   String pattern = ".*("+begin+".*"+end+").*";
	   Pattern r = Pattern.compile(pattern); 
//	   boolean isMatch = Pattern.matches(pattern, s);
	   Matcher m = r.matcher(path);
	   if (m.find()) {
	         return m.group(1);
	   }
		return null;
	}
	
	public static boolean isUsable(String path, int begin,int end){	 
	   String pattern = ".*"+begin+"(.*)"+end+".*";
	   Pattern r = Pattern.compile(pattern); 
	   Matcher m = r.matcher(path);
	   if (m.find()) {
	         String a=m.group(1);
	         if (a.indexOf("+")!=-1){
	        	 return false;
	         }else{
	        	 return true;
	         }
	    }	   
	  return false;
	}
	
	public static String changeUsable(String path,int begin,int end){	
		String pattern = ".*"+begin+"(.*)"+end+".*";
		System.out.println("s");
		Pattern r = Pattern.compile(pattern);
		 Matcher m = r.matcher(path);
		   if (m.find()) {
		         String a=m.group(1);
		         if (a.indexOf("+")==-1){
		        	 String b = a.replace("-", "+");
		        	return path.replace(a, b);
		         }else{
		        	 throw new NumberFormatException();  
		         }
		   }else{
			   throw new NumberFormatException();  
		   }
	}
	public static String caculateTime(Date end,Date begin){
	    try  
	    {  
	      long diff = end.getTime() - begin.getTime();//这样得到的差值是微秒级别  
	      long days = diff / (1000 * 60 * 60 * 24);  
	      long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);  
	      long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);  
	      if(days>0){
	    	  return ""+days+"天"+hours+"小时"+minutes+"分";
	      }else if(minutes>0){
	    	  return hours+"小时"+minutes+"分";
	      }else{
	    	  return minutes+"分"; 
	      }
	        
	    }catch (Exception e)  
	    {  
	    	return null;
	    }  
	}
}
