package com.nee.resource;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nee.beans.MetoFile;
 
@Path("/addFile")
public class MetoFileSaver{
 
	
    @POST
    @Path("/xml")
    @Consumes(MediaType.APPLICATION_XML)
    public Response uploadFile(MetoFile metoFile) throws IOException{
        System.out.println("Invoked method");
        System.out.println("Invoked method" + metoFile);

        
        
        return Response.status(200).entity("Uploaded file name : ").build();
    }
 
}
