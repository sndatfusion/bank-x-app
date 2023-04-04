package com.bankxapp.bankApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankxapp.bankApp.dto.AccounDTO;
import com.bankxapp.bankApp.services.AccountService;

@RestController
public class AccountCotroller {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/createAccount")
	public ResponseEntity<String> create(@RequestBody AccounDTO accounDTO)  {
		
		try {	
			accountService.createAccount(accounDTO);			
		} catch (Exception e) {
			e.printStackTrace();
			 return ResponseEntity.ok("Account not created.");
		}
		
		 return ResponseEntity.ok("Account created successfully.");
	}
}
