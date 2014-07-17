package com.nee.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public static void main( String args[] ){

	      // String to be scanned to find the pattern.
	     //String line = "/tto/hello/again/article1234.ece";
	      //String line = "/tto/hello/again/new?adfsadsa&safad";
	     String line = "/tto/public/sitesearch.do?querystring=london&p=tto&pf=all&bl=on";
	      String pattern = "(article)(\\d+)(.ece)";

	      String url = null;
	      String artId = null;
	      String query = null;
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);

	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find( )) {
	    	 url = line.substring(0, line.indexOf(m.group(0))).replace("/tto", "");
	    	 artId = m.group(2);
	      } else {
	    	  url = line.substring(0, (line.indexOf("?") == -1 ? line.length() : line.indexOf("?"))).replace("/tto", "");
	      }
	      int inde = line.indexOf("querystring=") + 12;
	      query = line.substring(inde, line.indexOf("&", inde));
	      
		     System.out.println(url);
	         System.out.println(artId);
	         System.out.println(query);
	   }
}
