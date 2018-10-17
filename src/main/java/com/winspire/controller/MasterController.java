package com.winspire.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

		ObjectMapper objectMapper = new ObjectMapper();

		String fileName = imageName;
		// applicationType = CMSUtil.getApplicationName(applicationType);
		File dir = new File(Constants.LOCAL_FILE_FOLDER_PATH);
		File file = new File(dir.getCanonicalPath() + File.separator + fileName);
		System.out.println("path2" + dir.getCanonicalPath() + File.separator + fileName);
		if (!file.exists()) {
			String errorMessage = "Sorry. The file you are looking for does not exist";
			
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return;
		}
		String mimeType = WinspireUtil.getMimeType(file);
		if (mimeType == null) {
			System.out.println("mimetype is not detectable, will take default");
			String errorMessage = "Sorry. The file you are looking for does not exist";
			System.out.println(errorMessage);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return;
		} else {
			System.out.println("mimetype : " + mimeType);
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", String.format("inline; filename=\"%s\"", file.getName()));
			response.setContentLength((int) file.length());
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
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
