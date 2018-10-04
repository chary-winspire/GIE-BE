package com.winspire.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "NOTIFICATION_DETAILS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationDetails implements Serializable {
	private static final long serialVersionUID = -7988799579036225137L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int NotificationID;
	private String NotificationTitle;
	private String NotificationComments;
	private String NotificationDescription;
	private String NotificationImage;
	private String UserName;
	private int  UserID;
	private String IsNotified;
	private String CreatedDate;
	private String CreatedBy;
	private String LastModifiedDate;
	private String LastModifiedBy;
	public int getNotificationID() {
		return NotificationID;
	}
	
	public String getNotificationDescription() {
		return NotificationDescription;
	}

	public void setNotificationDescription(String notificationDescription) {
		NotificationDescription = notificationDescription;
	}

	public void setNotificationID(int notificationID) {
		NotificationID = notificationID;
	}
	public String getNotificationTitle() {
		return NotificationTitle;
	}
	public void setNotificationTitle(String notificationTitle) {
		NotificationTitle = notificationTitle;
	}
	public String getNotificationComments() {
		return NotificationComments;
	}
	public void setNotificationComments(String notificationComments) {
		NotificationComments = notificationComments;
	}
	public String getNotificationImage() {
		return NotificationImage;
	}
	public void setNotificationImage(String notificationImage) {
		NotificationImage = notificationImage;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getIsNotified() {
		return IsNotified;
	}
	public void setIsNotified(String isNotified) {
		IsNotified = isNotified;
	}
	public String getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}
	public String getLastModifiedDate() {
		return LastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		LastModifiedDate = lastModifiedDate;
	}
	public String getLastModifiedBy() {
		return LastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		LastModifiedBy = lastModifiedBy;
	}

}
