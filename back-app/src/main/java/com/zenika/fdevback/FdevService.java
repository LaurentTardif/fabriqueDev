package com.zenika.fdevback;

import javax.persistence.*;

@Entity
@Table(name = "services")
public class FdevService {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;


	private String serviceName;
	private String serviceIconUri;
	private String serviceDescriptionShort;
	private String serviceMdInstallationGuide;
	private Boolean installed;

	public FdevService() {}

	public FdevService(String serviceName, String serviceIconUri, String serviceDescriptionShort, String serviceMdInstallationGuide, Boolean installed) {
		this.serviceName = serviceName;
		this.serviceIconUri = serviceIconUri;
		this.serviceDescriptionShort = serviceDescriptionShort;
		this.serviceMdInstallationGuide = serviceMdInstallationGuide;
		this.installed = installed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean isInstalled() {
		return installed;
	}

	public void setInstalled(Boolean installed) {
		this.installed = installed;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceIconUri() {
		return serviceIconUri;
	}

	public void setServiceIconUri(String serviceIconUri) {
		this.serviceIconUri = serviceIconUri;
	}

	public String getServiceDescriptionShort() {
		return serviceDescriptionShort;
	}

	public void setServiceDescriptionShort(String serviceDescriptionShort) {
		this.serviceDescriptionShort = serviceDescriptionShort;
	}

	public String getServiceMdInstallationGuide() {
		return serviceMdInstallationGuide;
	}

	public void setServiceMdInstallationGuide(String serviceMdInstallationGuide) {
		this.serviceMdInstallationGuide = serviceMdInstallationGuide;
	}
}