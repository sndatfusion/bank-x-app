package com.bankxapp.bankApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankxapp.bankApp.model.NotificationModel;
import com.bankxapp.bankApp.repository.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	NotificationRepository notificationRepository;
	
	@Override
	public List<NotificationModel> getNotificationByUser(String email) {
		return notificationRepository.findByEmailAddress(email);
	}

}
