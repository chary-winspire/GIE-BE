package com.winspire.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.jboss.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.StringUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.winspire.common.Constants;
import com.winspire.entity.UserDetails;
import com.winspire.service.PushService;
import com.winspire.util.WinspireUtil;

@Controller
public class MasterController {
	
	
	@Autowired
	private PushService pushService;
	ObjectMapper objectMapper= new ObjectMapper();
	
	public MasterController() {
		System.out.println("controller");
	}

	@RequestMapping("/")
	public ModelAndView formOneAcknowledgement() {
		System.out.println("page");
		ModelAndView model = new ModelAndView("index");
		return model;
	}
	@RequestMapping("/getNotificationDetails")
	public ResponseEntity<String> getNotificationDetails() {
		String notificationDetailsStr=pushService.getNotificationDetails();
		System.out.println("notificationDetailsStr :"+notificationDetailsStr);
		return new ResponseEntity<String>(notificationDetailsStr, HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/getImage" }, method = RequestMethod.GET)
	
	public void getImage(@RequestParam("imageName") String imageName,  HttpServletResponse response) throws JSONException, IOException {

		AWSCredentials credentials = new BasicAWSCredentials("AKIAIBQZ77AZPCKDPFJA", "aJey2BTVy5UO/+cKW/qYNptqpHbKM8ZR4FHzeudH");
		AmazonS3 s3Client = new AmazonS3Client(credentials);
		List<Bucket> buckets = s3Client.listBuckets();
		for (Bucket bucket : buckets) {
		        System.out.println(bucket.getName() + "\t" +
		                StringUtils.fromDate(bucket.getCreationDate()));
		}
		GetObjectRequest request = new GetObjectRequest("winspireindia.in",
				imageName);
			  S3Object object = s3Client.getObject(request);
			  S3ObjectInputStream objectContent = object.getObjectContent();
			  try {
				//IOUtils.copy(objectContent, new FileOutputStream("C://files//test.jpg"));
				//InputStream inputStream = new BufferedInputStream(new FileInputStream(request.getServletContext().getRealPath("resources/images/"+imageName)));
				FileCopyUtils.copy(objectContent, response.getOutputStream());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	@RequestMapping("/insertFcmToken")
	public ResponseEntity<String> insertFcmToken(@RequestParam("token") String token,@RequestParam("deviceID") String deviceID) throws JSONException {
		System.out.println("token :"+token);
		System.out.println("deviceID :"+deviceID);
		boolean result=pushService.insertFcmToken(token,deviceID);
		JSONObject resultObj= new JSONObject();
		resultObj.put("insertFcmTokenResult", result);
		return new ResponseEntity<String>(resultObj.toString(), HttpStatus.OK);
	}
	
	@RequestMapping("/registerUser")
	public ResponseEntity<String> registerUser(@RequestBody String userData) throws JSONException, JsonParseException, JsonMappingException, IOException {
		System.out.println("userData :"+userData);
		JSONObject inputObj=new JSONObject(userData);
		String userStr=inputObj.getString("details");
		UserDetails user=objectMapper.readValue(userStr, UserDetails.class);
		user =pushService.registerUser(user);
		JSONObject resultObj= new JSONObject();
		if(user!=null){
			boolean result=true;
			
			resultObj.put("registerUserResult", result);
			resultObj.put("userDetails", objectMapper.writeValueAsString(user));
			return new ResponseEntity<String>(resultObj.toString(), HttpStatus.OK);
		}else{
			boolean result=false;
			
			resultObj.put("registerUserResult", result);
			return new ResponseEntity<String>(resultObj.toString(), HttpStatus.OK);
		}
		
	}
	
	//
	@RequestMapping("/getQuestionnaire")
	public ResponseEntity<String> getQuestionnaire(@RequestParam("id") int id,@RequestParam("type") String Type) throws JSONException {
		System.out.println("userData :"+ id + Type);
	String questionnaireStr=pushService.getQuestionnaire(id,Type);
		JSONObject resultObj= new JSONObject();
		if(questionnaireStr!=null){
			resultObj.put("getQuestionnaireResult", true);
			resultObj.put("questionnaireStr", questionnaireStr);
		}else{
			resultObj.put("getQuestionnaireResult", false);

		}
		
		
		return new ResponseEntity<String>(resultObj.toString(), HttpStatus.OK);
	}
	
}
