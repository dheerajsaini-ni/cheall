package com.nee.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.appengine.api.blobstore.BlobKey;

@Entity
public class PersistenceIngestionEntry {

	@Id
	private String productType;
	
	private BlobKey blobData;

	public PersistenceIngestionEntry(){
		
	}
	
	public PersistenceIngestionEntry(String productType, BlobKey blobData) {
		super();
		this.productType = productType;
		this.blobData = blobData;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public BlobKey getBlobData() {
		return blobData;
	}

	public void setBlobData(BlobKey blobData) {
		this.blobData = blobData;
	}

}
