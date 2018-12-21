package com.winspire.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "USER_QUESTIONNAIRE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Questionnaire implements Serializable {
	private static final long serialVersionUID = -7988799579036225137L;
	 @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
   private String Type;
 
 
   private String Question;
   private int SID;
 
   private String Option1;
   private String Option2;
   private String Option3;
   private String CreatedBy;
   private String CreatedDate;
   private String LastModifiedBy;
   private String LastModifiedDate;
   private String Option4;
   private String UserAnswer;
   private String Answer;
   private String Comment;
   public String getComment() {
	return Comment;
}
public void setComment(String comment) {
	Comment = comment;
}
private String ImageUrl;

public int getID() {
	return ID;
}
public void setID(int iD) {
	ID = iD;
}
public String getType() {
	return Type;
}
public void setType(String type) {
	Type = type;
}
public String getQuestion() {
	return Question;
}
public void setQuestion(String question) {
	Question = question;
}
public int getSID() {
	return SID;
}
public void setSID(int sID) {
	SID = sID;
}
public String getOption1() {
	return Option1;
}
public void setOption1(String option1) {
	Option1 = option1;
}
public String getOption2() {
	return Option2;
}
public void setOption2(String option2) {
	Option2 = option2;
}
public String getOption3() {
	return Option3;
}
public void setOption3(String option3) {
	Option3 = option3;
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
public String getOption4() {
	return Option4;
}
public void setOption4(String option4) {
	Option4 = option4;
}
public String getUserAnswer() {
	return UserAnswer;
}
public void setUserAnswer(String userAnswer) {
	UserAnswer = userAnswer;
}
public String getAnswer() {
	return Answer;
}
public void setAnswer(String answer) {
	Answer = answer;
}
public String getImageUrl() {
	return ImageUrl;
}
public void setImageUrl(String imageUrl) {
	ImageUrl = imageUrl;
}

}
