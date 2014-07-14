package com.nee.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author neelam.kapoor
 *
 */
public class MetoHelper {
	
	public static Date convertStringIntoDate(String date){
		
	    DateFormat df = new SimpleDateFormat("yyyy/mm/dd"); 
	    Date startDate = null;
	    try {
	        startDate = df.parse(date);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		return startDate;
	}	
	
	public static Date convertStringIntoDate(String date, String format){
		
	    DateFormat df = new SimpleDateFormat(format); 
	    Date startDate = null;
	    try {
	        startDate = df.parse(date);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		return startDate;
	}	
}