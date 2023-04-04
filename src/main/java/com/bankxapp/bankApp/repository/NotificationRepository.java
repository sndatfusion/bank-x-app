package com.bankxapp.bankApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankxapp.bankApp.model.NotificationModel;

public interface NotificationRepository extends JpaRepository<NotificationModel, Long> {
	
	List<NotificationModel> findByEmailAddress(String emailAddress);

}
