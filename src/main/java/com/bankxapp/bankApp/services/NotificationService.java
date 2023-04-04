package com.bankxapp.bankApp.services;

import java.util.List;

import com.bankxapp.bankApp.model.NotificationModel;

public interface NotificationService {

	public List<NotificationModel> getNotificationByUser(String email);
}
