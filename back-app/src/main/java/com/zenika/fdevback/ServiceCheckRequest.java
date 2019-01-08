package com.zenika.fdevback;


import org.hibernate.validator.constraints.URL;
import org.json.JSONObject;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ServiceCheckRequest {
	@NotNull(message = "serviceName must be a valid String")
	@Pattern(regexp = "^[a-zA-Z]{2,}$", message = "serviceName must be a valid String")
	private String serviceName;

	@NotEmpty(message = "serviceURI must be a valid URI")
	@NotNull(message = "serviceURI must be a valid URI")
	@Pattern(regexp = "(https?:\\/\\/)([^:^\\/]*)(:\\\\d*)?(.*)?", message = "serviceURI must be a valid URI")
	private String serviceURI;


	public ServiceCheckRequest() {}

	public ServiceCheckRequest(String serviceName, String serviceURI) {
		this.serviceName = serviceName;
		this.serviceURI = serviceURI;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceURI() {
		return serviceURI;
	}

	public void setServiceURI(String serviceURI) {
		this.serviceURI = serviceURI;
	}

	public String toJson() {
		JSONObject json = new JSONObject();
		json.put("serviceName", this.serviceName);
		json.put("serviceURI", this.serviceURI);
		return json.toString();
	}
}
