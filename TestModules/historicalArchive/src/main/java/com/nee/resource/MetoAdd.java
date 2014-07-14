package com.nee.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nee.JPA.AddJPALogic;
import com.nee.beans.MetoDataJPA;
import com.nee.beans.MetoResponse;
import com.nee.utils.MetoHelper;

/**
 * 
 * @author neelam.kapoor
 *
 */
@Path("/addfile")
public class MetoAdd {

	@POST  
    @Path("/csv")  
    @Consumes(MediaType.TEXT_PLAIN)   
	@Produces(MediaType.TEXT_PLAIN)
    public String postOnlyCSV(String incomingCSV) { 
		
		System.out.println("inside postOnlyCSV method");
		MetoResponse metoResponse = null;
		List<MetoDataJPA> metoDataList = new ArrayList<MetoDataJPA>();
        try {
			System.out.println("incomingCSV :" + incomingCSV);  
			metoDataList = createList(incomingCSV);
			metoResponse = AddJPALogic.INSTANCE.add(metoDataList);
		} catch (Exception e) {
			System.out.println("error in posting csv" + e);
		}
        return "SUCCESS";  
    }  
	
	// String is of type 01072014,SE8,1.0,2.0,3.0
	/**
	 * 
	 * @param incomingString that is similar to 
	 * 		01072014,LON,1.0,2.0,3.0
	 * 		01072014,SE_ENG,1.0,2.0,3.0
	 * 		01072014,SE_ENG,1.0,2.0,3.0
	 * @return list of MetoDataJPA objects.
	 */
	private List<MetoDataJPA> createList(String incomingString){
		System.out.println("inside createList method");
		List<MetoDataJPA> metoDataList = new ArrayList<>();
		
		MetoDataJPA tmpMetoData = null;
		String[] line = incomingString.split("\n");
		for(String temp:line){
			String[] split = temp.split(",");
			Date date = MetoHelper.convertStringIntoDate(split[0], "yyyymmdd");
			tmpMetoData = new MetoDataJPA(date + ":" + split[1],date, split[1], split[2], split[3]);
			metoDataList.add(tmpMetoData);
			System.out.println("adding csv data to list");
		}
	
		return metoDataList;
	}
}
