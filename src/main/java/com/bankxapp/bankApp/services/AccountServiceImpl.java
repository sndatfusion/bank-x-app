package com.bankxapp.bankApp.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankxapp.bankApp.dto.AccounDTO;
import com.bankxapp.bankApp.model.AccountAuditModel;
import com.bankxapp.bankApp.model.AccountModel;
import com.bankxapp.bankApp.model.NotificationModel;
import com.bankxapp.bankApp.repository.AccountAuditRepository;
import com.bankxapp.bankApp.repository.AccountRepository;
import com.bankxapp.bankApp.repository.NotificationRepository;
import com.bankxapp.bankApp.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AccountAuditRepository accountAuditRepository;
	
	@Autowired
	NotificationRepository notificationRepository;

	@Override
	@Transactional
	public void createAccount(AccounDTO accounDTO) throws Exception {
		
		AccountModel accountModel = new AccountModel();
		
		BeanUtils.copyProperties(accounDTO, accountModel);

		if(accounDTO.getAccountType().equalsIgnoreCase("Savings")) {
			accountModel.setBalance(500.00);
		} else {
			accountModel.setBalance(0.0);
		}
		
		accountModel.setAccountNumber(Util.getID());
		
		accountRepository.save(accountModel);
		ObjectMapper objectMapper = new ObjectMapper();
		
		AccountAuditModel accountAuditModel =  new AccountAuditModel();
		String accountModelString = objectMapper.writeValueAsString(accountModel);
		accountAuditModel.setContentJson(accountModelString);
		accountAuditRepository.save(accountAuditModel);
		
		NotificationModel notificationModel = new NotificationModel();
		notificationModel.setAccountNumber(accountModel.getAccountNumber());
		notificationModel.setEmailAddress(accountModel.getEmailAddress());
		notificationModel.setContentJson(accountModelString);
		notificationModel.setTitle("Account created");
		
		notificationRepository.save(notificationModel);
	}

}
