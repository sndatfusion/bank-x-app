package com.bankxapp.bankApp.services;

import javax.management.RuntimeErrorException;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankxapp.bankApp.dto.AccountTransactionDTO;
import com.bankxapp.bankApp.model.AccountAuditModel;
import com.bankxapp.bankApp.model.AccountModel;
import com.bankxapp.bankApp.model.AccountTransactionModel;
import com.bankxapp.bankApp.model.NotificationModel;
import com.bankxapp.bankApp.repository.AccountAuditRepository;
import com.bankxapp.bankApp.repository.AccountRepository;
import com.bankxapp.bankApp.repository.AccountTransactionRepository;
import com.bankxapp.bankApp.repository.NotificationRepository;
import com.bankxapp.bankApp.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {
	
	@Autowired
	private AccountTransactionRepository accountTransactionRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AccountAuditRepository accountAuditRepository;
	
	@Autowired
	NotificationRepository notificationRepository;
	
	@Transactional
	public void transaction(AccountTransactionDTO accountTransactionDTO)  throws Exception {
		
		AccountModel fromAccount = accountRepository.findByAccountNumber(accountTransactionDTO.getAccountNumberFrom());	
		
		if(fromAccount.getAccountType().equals("Current")) {
		
			if(fromAccount.getBalance() > accountTransactionDTO.getAmount()) {
			
				AccountTransactionModel accountTransactionModel = new AccountTransactionModel();		
				BeanUtils.copyProperties(accountTransactionDTO, accountTransactionModel);
				ObjectMapper objectMapper = new ObjectMapper();
				
				accountTransactionModel.setAmountWithDiscount(accountTransactionDTO.getAmount() + Util.getReceiverAmountDiscount(fromAccount.getBalance()));
				accountTransactionRepository.save(accountTransactionModel);
				
				AccountAuditModel accountAuditModel =  new AccountAuditModel();
				String accountTransactionModelString = objectMapper.writeValueAsString(accountTransactionModel);
				accountAuditModel.setContentJson(accountTransactionModelString);
				accountAuditRepository.save(accountAuditModel);
				
				AccountModel toAccount = accountRepository.findByAccountNumber(accountTransactionDTO.getAccountNumberTo());
				toAccount.setBalance(toAccount.getBalance() + accountTransactionModel.getAmountWithDiscount());
				accountRepository.save(toAccount);
				
				NotificationModel notificationModelTo = new NotificationModel();
				notificationModelTo.setAccountNumber(toAccount.getAccountNumber());
				notificationModelTo.setEmailAddress(toAccount.getEmailAddress());
				
				String toAccountString = objectMapper.writeValueAsString(toAccount);
				JSONObject jsonObject = new JSONObject(toAccountString);
				jsonObject.put("receivedAmount", accountTransactionModel.getAmountWithDiscount());
				notificationModelTo.setContentJson(jsonObject.toString());
				
				notificationModelTo.setTitle("Account credit");
				notificationRepository.save(notificationModelTo);
				
				Double changes = Util.getSenderChangesAmount(accountTransactionModel.getAmount());
				fromAccount.setBalance(fromAccount.getBalance() - (accountTransactionModel.getAmount()+changes));
				accountRepository.save(fromAccount);
				
				NotificationModel notificationModelFrom = new NotificationModel();
				notificationModelFrom.setAccountNumber(fromAccount.getAccountNumber());
				notificationModelFrom.setEmailAddress(fromAccount.getEmailAddress());
				notificationModelFrom.setTitle("Account debit");
				String fromAccountString = objectMapper.writeValueAsString(fromAccount);
				JSONObject jsonObjectFrom = new JSONObject(fromAccountString);
				jsonObjectFrom.put("deductedAmount", (accountTransactionModel.getAmount()+changes));
				jsonObjectFrom.put("charges", changes);
				notificationModelFrom.setContentJson(jsonObjectFrom.toString());
				notificationRepository.save(notificationModelFrom);
				
			} else {
				throw new ArithmeticException("Insufficient balance for this transaction.");    
			}
		} else {
			throw new RuntimeException("Only Current Account allowed for transaction.");
		}
		
	}

}
