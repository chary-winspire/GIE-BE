package com.winspire.service.impl;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.winspire.common.Constants;
import com.winspire.dao.PushDAO;
import com.winspire.entity.FCMTokens;
import com.winspire.entity.NotificationData;
import com.winspire.entity.NotificationDetails;
import com.winspire.entity.NotificationRequestModel;
import com.winspire.entity.Questionnaire;
import com.winspire.entity.UserDetails;
import com.winspire.service.PushService;

@Service

@Transactional
public class PushServiceImpl implements PushService {
	static Logger log = Logger.getLogger(PushServiceImpl.class.getName());


	@Autowired
	private PushDAO pushDao;
	ObjectMapper objectMapper=new ObjectMapper();
	Date date= new Date();
	@Override	
	public void sendNotifications(String notificationType)  {
		
		ObjectMapper objectMapper= new ObjectMapper();
		String response=null;
		System.out.println("sendNotifications:" );
		NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
        NotificationData notificationData = new NotificationData();
        if(notificationType.equalsIgnoreCase("MOT")){
        	notificationData.setId(0);
        }else{
        	Random r = new Random();
        	int low = 1;
        	int high = 13;
        	int result = r.nextInt(high-low) + low;
        	notificationData.setId(result);
        }
        notificationData.setType(notificationType);
		List<FCMTokens> allTokens=pushDao.getAllFCMTokens();	
		for(int i=0;i<allTokens.size();i++){
			
			try {      
			        notificationData.setTitle("Welcome to Winspire Go.");
			        if(notificationType.equalsIgnoreCase("MOT")){
			        	 notificationData.setDetail("Here is the Motivation for you.");
			        }  if(notificationType.equalsIgnoreCase("PUZ")){
			        	 notificationData.setDetail("Here is the Puzzle for you.");
			        }  if(notificationType.equalsIgnoreCase("GK")){
			        	 notificationData.setDetail("Here is the GK for you.");
			        }  if(notificationType.equalsIgnoreCase("SPDM")){
			        	 notificationData.setDetail("Here is the Speed Maths for you.");
			        }  if(notificationType.equalsIgnoreCase("WORD")){
			        	 notificationData.setDetail("Here is the Word Power for you.");
			        }  
			       
			        notificationRequestModel.setData(notificationData);
			        notificationRequestModel.setTo(allTokens.get(i).getFCMToken());


			        Gson gson = new Gson();
			        Type type = new TypeToken<NotificationRequestModel>() {
			        }.getType();

			        String json = gson.toJson(notificationRequestModel, type);

			        StringEntity input = new StringEntity(json);
			        input.setContentType("application/json");
			        
			        
		        
		        DefaultHttpClient httpClient = new DefaultHttpClient();
		        HttpPost postRequest = new HttpPost(Constants.FIREBASE_API_URL);

		

		    


		        postRequest.addHeader("Authorization", "key=" +Constants. FIREBASE_SERVER_KEY);
		        postRequest.setEntity(input);

		        System.out.println("postRequest:" + EntityUtils.toString(postRequest.getEntity()));

		        HttpResponse httpResponse = httpClient.execute(postRequest);
		        System.out.println("response:" + httpResponse);
		        if (httpResponse.getStatusLine().getStatusCode() != 200) {
		            throw new RuntimeException("Failed : HTTP error code : "
		                    + httpResponse.getStatusLine().getStatusCode());
		        } else if (httpResponse.getStatusLine().getStatusCode() == 200) {
		        	response=EntityUtils.toString(httpResponse.getEntity()); 
		        }
		        
		   
		        
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}

		
	
	}
	@Override
	public String getNotificationDetails() {
		try {JSONObject obj= new JSONObject();
				List<NotificationDetails> nDetailsList=pushDao.getNotificationDetails();
				String nDetailsListStr=objectMapper.writeValueAsString(nDetailsList) ;
				obj.put("notificationDetails", nDetailsListStr);
				obj.put("getNotificationDetailsResult", true);
				return obj.toString();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	@Override
	public boolean insertFcmToken(String token,String deviceID) {
		// TODO Auto-generated method stub
		try {
			FCMTokens tokenObj= new FCMTokens();
		
			FCMTokens tokenDetails=	pushDao.getFCMDetails(token);
			if(tokenDetails!=null){
				tokenDetails.setFCMToken(token);
				tokenDetails.setDeviceID(deviceID);
				tokenDetails.setLastModifiedDate(new Timestamp(System.currentTimeMillis()).toString());
				tokenDetails.setCreatedDate(new Timestamp(System.currentTimeMillis()).toString());
				pushDao.insertFcmToken(tokenDetails);
			}else{
				tokenObj.setFCMToken(token);
				tokenObj.setDeviceID(deviceID);
				tokenObj.setLastModifiedDate(new Timestamp(System.currentTimeMillis()).toString());
				tokenObj.setCreatedDate(new Timestamp(System.currentTimeMillis()).toString());
				pushDao.insertFcmToken(tokenObj);
			}
			
		
		
		return true;
} catch (Exception e) {
	e.printStackTrace();
	// TODO: handle exception
}
return false;
	}
	@Override
	public UserDetails registerUser(UserDetails user) {
		// TODO Auto-generated method stub
	
		user.setCreatedBy(user.getUserName());
		user.setCreatedDate(new Timestamp(System.currentTimeMillis()).toString());
		user.setLastModifiedBy(user.getUserName());
		user.setLastModifiedDate(new Timestamp(System.currentTimeMillis()).toString());
		user.setIsActive(Constants.YES);
		try {
			System.out.println("UserDetails :"+objectMapper.writeValueAsString(user) );
			System.out.println("UserName :"+user.getUserName() );
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pushDao.registerUser(user);
		return user;
	}
	@Override
	public String getQuestionnaire(int i, String type) {
		// TODO Auto-generated method stub
		String questionnaireStr= null;
		List<Questionnaire> list=pushDao.getQuestionnaire(i,type);
		if(list.size()>0){
			
			try {
				questionnaireStr = objectMapper.writeValueAsString(list);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return questionnaireStr;
		}else{return null;}
		
	}
	
	
}
