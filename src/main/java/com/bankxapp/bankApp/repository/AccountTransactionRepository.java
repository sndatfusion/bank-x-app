package com.bankxapp.bankApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankxapp.bankApp.model.AccountTransactionModel;

public interface AccountTransactionRepository  extends JpaRepository<AccountTransactionModel, Long>{

}
