package com.bankxapp.bankApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankxapp.bankApp.model.AccountModel;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long> {
	
	public AccountModel findByAccountNumber(Long accountNumber);

}
