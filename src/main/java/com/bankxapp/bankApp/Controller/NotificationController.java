package com.bankxapp.bankApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankxapp.bankApp.model.NotificationModel;
import com.bankxapp.bankApp.services.NotificationService;

@RestController
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;

	@GetMapping("/getNotificationByUser")
	public ResponseEntity<Object> getNotificationByUser(@RequestParam String email) {
		
		try {
			List<NotificationModel> notificationList =  notificationService.getNotificationByUser(email);
			return ResponseEntity.ok(notificationList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok("Notification not found");
		}	
	}
	
	
	
}
