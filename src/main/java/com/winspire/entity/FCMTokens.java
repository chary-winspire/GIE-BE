package com.winspire.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.jdbc.object.UpdatableSqlQuery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "FCM_TOKENS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FCMTokens {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int ID;
	
	private String DeviceID;
	private String FCMToken;
	@Column(updatable=false)
	private String CreatedDate;
	private String LastModifiedDate;
	
	
	public String getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}
	public String getLastModifiedDate() {
		return LastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		LastModifiedDate = lastModifiedDate;
	}
	public String getDeviceID() {
		return DeviceID;
	}
	public void setDeviceID(String deviceID) {
		DeviceID = deviceID;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getFCMToken() {
		return FCMToken;
	}
	public void setFCMToken(String fCMToken) {
		FCMToken = fCMToken;
	}
	
}
