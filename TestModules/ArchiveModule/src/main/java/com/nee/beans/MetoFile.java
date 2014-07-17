package com.nee.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * JDO-annotated model class for storing movie properties; movie's promotional
 * image is stored as a Blob (large byte array) in the image field.
 */
@XmlRootElement(name = "data")
public class MetoFile {
	
	private String metaData;

	private String ingestion;

	public String getMetaData() {
		return metaData;
	}

	@XmlElement
	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}

	public String getIngestion() {
		return ingestion;
	}

	@XmlElement
	public void setIngestion(String ingestion) {
		this.ingestion = ingestion;
	}

	@Override
	public String toString() {
		return "MetoFile [metaData=" + metaData + ", ingestion=" + ingestion
				+ "]";
	}
	
	
	
}
