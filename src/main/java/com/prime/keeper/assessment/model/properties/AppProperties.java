package com.prime.keeper.assessment.model.properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_properties")
public class AppProperties {

	@Id
    @Column(name = "app_key")
	private String appKey;
	
    @Column(name = "app_value")
	private String appValue;
    
    @Column(name = "description")
    private String description;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppValue() {
		return appValue;
	}

	public void setAppValue(String appValue) {
		this.appValue = appValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
