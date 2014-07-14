package com.nee.JPA;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.nee.beans.MetoDataJPA;
import com.nee.beans.MetoResponse;

/**
 * This class takes weather data parameters and persists them on data stores.
 * @author neelam.kapoor
 */

public enum AddJPALogic {
	INSTANCE;
	
	/**
	 * This method persists parameters into data stores.
	 * @param date
	 * @param regions
	 * @param temperature
	 * @param pressure
	 * @return "FAILURE" or "SUCCESS" as response
	 */
	public MetoResponse add(Date date, String regions, String temperature, String pressure ){
		MetoResponse metoResponse = null;
		EntityManager em = EMFService.get().createEntityManager();
		try {			
			MetoDataJPA mJPA = new MetoDataJPA(date + ":" + regions, date, regions, temperature,pressure);			
			em.persist(mJPA);
			System.out.println("stored entity id : " + em.toString());
			 metoResponse = new MetoResponse("SUCCESS",null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 metoResponse = new MetoResponse("FAILURE",null);
		}
		finally{
			em.close();
		}
		return metoResponse;
	}

	//Method overloading
	public MetoResponse add(List<MetoDataJPA> metoDataList) {
		EntityManager em = null;
		MetoResponse metoResponse = null;
		Date date = null;
		String regions = null;
		String temperature = null;
		String pressure = null;
		em = EMFService.get().createEntityManager();
		try {
			for(MetoDataJPA tempList:metoDataList){
				date = tempList.getWeatherDate();
				regions = tempList.getRegions();
				temperature = tempList.getTemperature();
				pressure = tempList.getPressure();
				
				
				MetoDataJPA mJPA = new MetoDataJPA(date + ":" + regions, date, regions, temperature,pressure);
				em.persist(mJPA);
				System.out.println("stored entity id : " + em.toString());
				 metoResponse = new MetoResponse("SUCCESS",null);
				} 
		} catch (Exception e) {
				e.printStackTrace();
				metoResponse = new MetoResponse("FAILURE",null);
			}
			finally{
				em.close();
			}
		return metoResponse;
	}

}

//
//public Long count(Date date, String regions){
//	EntityManager em = EMFService.get().createEntityManager();
//	Long count = null;
//	try{
//		Query query = em.createQuery("SELECT COUNT(m) FROM MetoDataJPA m WHERE m.weatherDate = :storedDate AND m.regions = :locations");
//		query.setParameter("storedDate", date);
//		query.setParameter("locations", regions);
//		System.out.println("printing query" + query.toString());
//		count = (Long) query.getSingleResult();
//		System.out.println("this record is present" + count + "times");
//	}finally{
//		em.close();
//	}
//	return count;	
//}