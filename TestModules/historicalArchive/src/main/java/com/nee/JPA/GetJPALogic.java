package com.nee.JPA;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.appengine.repackaged.com.google.api.client.util.Preconditions;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.nee.beans.MetoDataJPA;

/**
 * This class retrieves weather data from database and returns a list to calling servlet.
 * @author neelam.kapoor
 *
 */
public enum GetJPALogic {
	INSTANCE;
	
	/**
	 * This method returns weather data from fromDate to toDate for single/list of regions.
	 * @param fromDate
	 * @param toDate
	 * @param regions
	 * @return list of MetoDataJPA objects
	 */
	//search weather data  between two dates
	@SuppressWarnings({ "unchecked" })
	public List<MetoDataJPA> getWeatherBetweenDates(Date fromDate, Date toDate, Optional<String[]> regions){
		 
		Preconditions.checkNotNull(fromDate,"'fromDate' must be present");
		Preconditions.checkNotNull(toDate,"'toDate' must be present");
		Preconditions.checkNotNull(regions,"'regions' must be present");
		
		List<MetoDataJPA> list = Lists.newArrayList();
		
		System.out.println("received fromDate and toDate" + fromDate + toDate);
		System.out.println("list of regions" + regions);
		
		//TODO null check for regions
		for(String reg: regions.get()){
			EntityManager em = EMFService.get().createEntityManager();
			try {
				Query query = em.createQuery("SELECT m FROM MetoDataJPA m WHERE m.weatherDate BETWEEN :startDate AND :endDate AND m.regions = :locations ORDER BY m.regions");
				query.setParameter("locations", reg);
				query.setParameter("startDate", fromDate);
				query.setParameter("endDate", toDate);
				
				System.out.println("printing query" + query.toString());
				
				list.addAll(query.getResultList());
				System.out.println(list.toString());
			}finally{
				em.close();
			}
		}
		return list;
	}
	
	public static boolean isNullOrEmpty(){
		return false;
		
	}

}

	

