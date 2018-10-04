package com.winspire.schedulers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.winspire.service.PushService;


@Configuration
@EnableScheduling
public class SendNotificationsCron {
	
	@Autowired
	private PushService pushService;

	@Scheduled(cron = "0 0 0/2 ? * *") //second, minute, hour, day of month, month, day(s) of week
    public void sendReminders() throws JSONException, ClientProtocolException, IOException{
		
	pushService.sendNotifications();
		
	}
	
	
}
