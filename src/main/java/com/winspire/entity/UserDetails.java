package com.winspire.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "USER_DETAILS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails implements Serializable{

	private static final long serialVersionUID = -7988799579036225137L;
	 @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
    private String UserName;
    private int UserID;
  
    private String Email;
    private int ContactNo;
    private int AndroidVersion;
    private String DeviceID;
    private String LoginType;
    private String IsActive;
    private String CreatedBy;
    private String CreatedDate;
    private String LastModifiedBy;
    private String LastModifiedDate;
    private String FCMToken;
    private String AndroidVersionType;
    private String DateOfBirth;
    private String Gender;
    private String FirstName;
    private String MiddleName;
    private String LastName;
    private String AccountUrl;
    private String ImageUrl;
    private String FGID;

    public String getAndroidVersionType() {
		return AndroidVersionType;
	}

	public void setAndroidVersionType(String androidVersionType) {
		AndroidVersionType = androidVersionType;
	}

	public String getFGID() {
        return FGID;
    }

    public void setFGID(String FGID) {
        this.FGID = FGID;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAccountUrl() {
        return AccountUrl;
    }

    public void setAccountUrl(String accountUrl) {
        AccountUrl = accountUrl;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getContactNo() {
        return ContactNo;
    }

    public void setContactNo(int contactNo) {
        ContactNo = contactNo;
    }

    public int getAndroidVersion() {
        return AndroidVersion;
    }

    public void setAndroidVersion(int androidVersion) {
        AndroidVersion = androidVersion;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getLoginType() {
        return LoginType;
    }

    public void setLoginType(String loginType) {
        LoginType = loginType;
    }

    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String isActive) {
        IsActive = isActive;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getLastModifiedBy() {
        return LastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        LastModifiedBy = lastModifiedBy;
    }

    public String getLastModifiedDate() {
        return LastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        LastModifiedDate = lastModifiedDate;
    }

    public String getFCMToken() {
        return FCMToken;
    }

    public void setFCMToken(String FCMToken) {
        this.FCMToken = FCMToken;
    }

}
