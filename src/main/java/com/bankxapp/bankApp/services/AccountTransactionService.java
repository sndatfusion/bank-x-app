package com.bankxapp.bankApp.services;

import com.bankxapp.bankApp.dto.AccountTransactionDTO;

public interface AccountTransactionService {

	/**
	 * this method for transaction one account to another account
	 * @param accountTransactionDTO
	 */
	public void transaction(AccountTransactionDTO accountTransactionDTO)  throws Exception;
}
