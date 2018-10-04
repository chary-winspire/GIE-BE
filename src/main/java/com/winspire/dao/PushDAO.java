package com.winspire.dao;

import java.text.ParseException;
import java.util.List;

import com.winspire.entity.FCMTokens;
import com.winspire.entity.NotificationDetails;
import com.winspire.entity.Questionnaire;
import com.winspire.entity.UserDetails;


public interface PushDAO {

	List<FCMTokens> getAllFCMTokens();

	List<NotificationDetails> getNotificationDetails();

	void insertFcmToken(FCMTokens tokenObj);

	void registerUser(UserDetails user);

	FCMTokens getFCMDetails(String deviceID);

	List<Questionnaire> getQuestionnaire(int i, String type);


	
}
