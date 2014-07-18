package com.nee.resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.IOUtils;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsInputChannel;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;
import com.nee.beans.IngestionConfiguration;


/**
 * A simple resource that proxies reads and writes to its Google Cloud Storage bucket.
 */
@SuppressWarnings("serial")
@Path("/gcs")
public class MetoFileSaver{
 
	public static final String ENDPOINT = "gcs";
	public static final String ENDPOINT_BASE = "/gcs/";
	public static final String BUCKET = "endpointnee.appspot.com";
	public static final boolean SERVE_USING_BLOBSTORE_API = false;
	private static final int BUFFER_SIZE = 2 * 1024 * 1024;
  
  /**
   * This is where backoff parameters are configured. Here it is aggressively retrying with
   * backoff, up to 10 times but taking no more that 15 seconds total to do so.
   */
  private final GcsService gcsService = GcsServiceFactory.createGcsService(new RetryParams.Builder()
      .initialRetryDelayMillis(10)
      .retryMaxAttempts(10)
      .totalRetryPeriodMillis(15000)
      .build());


  /**
   * Retrieves a file from GCS and returns it in the http response.
   * If a get comes in for request path /gcs/Foo/Bar this will be interpreted as
   * a request to read the GCS file named Bar in the bucket Foo.
   */
	@GET
    @Path("get/{filename}")
	@Produces({"application/pdf"})
	public Response doGet(@PathParam("filename") String filename) {
	  
		String outputString = null;
		ResponseBuilder builder = null;
		GcsInputChannel readChannel = null;
		  
		System.out.println("Putting for the file : " + ENDPOINT_BASE + BUCKET + "/" + filename);
		GcsFilename fileName = getFileName(ENDPOINT_BASE + BUCKET + "/" + filename);

		try {
			readChannel = gcsService.openPrefetchingReadChannel(fileName, 0, BUFFER_SIZE);
			outputString = IOUtils.toString(Channels.newInputStream(readChannel), "UTF-8");
			
			// Do what you need to do with the output here
	        // Base 64 Decoding
	        Base64 b64 = new Base64();
	        byte[] outByte = b64.decode(outputString);
			
	        builder = Response
		            .ok(outByte, "application/pdf")
		            .header("content-disposition","attachment; filename = " + filename + ".pdf");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return builder.build();
  }


    @POST
    @Path("/upload/xml")
    @Consumes(MediaType.APPLICATION_XML)
    public Response uploadFile(IngestionConfiguration metoFile) throws IOException{
    	
    	ResponseBuilder builder = null;
    	String product = null;
    	GcsOutputChannel outputChannel = null;
        System.out.println("Invoked method" + metoFile);

        try{
	        String data = metoFile.getIngestionEntries().getIngestionEntry().get(0).getBlobData();
	        product = metoFile.getIngestionEntries().getIngestionEntry().get(0).getProductType();
	        GcsFileOptions fileOptions = GcsFileOptions.getDefaultInstance();
	
	        System.out.println("Putting for the file : " + ENDPOINT_BASE + BUCKET + "/" + product);
	
	        outputChannel = gcsService.createOrReplace(getFileName(ENDPOINT_BASE + BUCKET + "/" + product), fileOptions);
	        InputStream stream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
	        copy(stream, Channels.newOutputStream(outputChannel));
	        
	        builder = Response.status(200).entity("Uploaded file name : " + product);
        } catch (IOException e) {
			e.printStackTrace();
			builder = Response.status(500).entity("Unable to upload file for  : " + product);
		}
        
        return builder.build();
    }	
	
	/**
	 * 
	 * @param req
	 * @return
	 */
	private GcsFilename getFileName(String req) {
		String[] splits = req.split("/", 4);
		if (!splits[0].equals("") || !splits[1].equals(ENDPOINT)) {
			throw new IllegalArgumentException("The URL is not formed as expected. " +
			"Expecting " + ENDPOINT_BASE + BUCKET + "/<object>");
		}
		return new GcsFilename(splits[2], splits[3]);
	}

  /**
   * Transfer the data from the inputStream to the outputStream. Then close both streams.
   */
	private void copy(InputStream input, OutputStream output) throws IOException {
		try {
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = input.read(buffer);
			while (bytesRead != -1) { 
				output.write(buffer, 0, bytesRead);
				bytesRead = input.read(buffer);
			}
		} finally {
			input.close();
			output.close();
		}
	}
}
