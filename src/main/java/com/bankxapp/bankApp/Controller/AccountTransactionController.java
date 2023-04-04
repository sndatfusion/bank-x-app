package com.bankxapp.bankApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankxapp.bankApp.dto.AccountTransactionDTO;
import com.bankxapp.bankApp.services.AccountTransactionService;

@RestController
public class AccountTransactionController {
	
	@Autowired
	private AccountTransactionService accountTransactionService;

	@PostMapping("/transaction")
	public ResponseEntity<String> transaction(@RequestBody AccountTransactionDTO accountTransactionDTO)  {
		
		try {
			accountTransactionService.transaction(accountTransactionDTO);
		} catch (Exception e) {
			 return ResponseEntity.ok(e.getMessage());
		}
		 return ResponseEntity.ok("Transaction done successfully.");
		
	}
}
