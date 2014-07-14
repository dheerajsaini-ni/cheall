package com.nee.resource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/fetch")
public class MetoGetResource {
	// The URL hit would be http://<DOMAIN>/meto/add/csv/ddmmyyyy/ddmmyyyy/id1-id2-id3
		@GET
	    @Path("csv/{fromDate}/{toDate}/{regions}")
	    @Produces("text/plain")
	    public Response getPDF(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate,  
				@PathParam("regions") String regions) {
			InputStream is = null;
			String outputHeader = "attachment; filename="+"export.csv";
			try{
				System.out.println("FromDate : " + fromDate + ", toDate : " + toDate + ", regions : " + regions);
				// Use fromDate, toDate and regions and return str which is the full data
				String str = "1,2,3,4";
				is = new ByteArrayInputStream(str.getBytes("UTF-8"));
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
			return Response.ok().entity(is).header("Content-Disposition", outputHeader).build(); 
	    } 
}
