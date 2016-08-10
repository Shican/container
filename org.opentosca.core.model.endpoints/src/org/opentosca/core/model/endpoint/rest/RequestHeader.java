package org.opentosca.core.model.endpoint.rest;

/**
 * 
 * Copyright 2012 IAAS University of Stuttgart <br>
 * <br>
 * 
 * @author Matthias Fetzer - fetzerms@studi.informatik.uni-stuttgart.de
 * 
 */
public class RequestHeader {
	
	private String header;
	private boolean required;
	
	
	public boolean isRequired() {
		return this.required;
	}
	
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	public String getHeader() {
		return this.header;
	}
	
	public void setHeader(String header) {
		this.header = header;
	}
	
}
