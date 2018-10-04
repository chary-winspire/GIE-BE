package com.winspire.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.winspire.entity.UserDetails;

public interface PushService {

	void sendNotifications() throws JSONException, UnsupportedEncodingException, ClientProtocolException, IOException;

	String getNotificationDetails();

	boolean insertFcmToken(String token, String deviceID);

	UserDetails registerUser(UserDetails user);

	String getQuestionnaire(int i, String string);}
