package com.bankxapp.bankApp.services;

import com.bankxapp.bankApp.dto.AccounDTO;

public interface AccountService {

	/**
	 * this method for create account
	 * @param accounDTO
	 */
	public void createAccount(AccounDTO accounDTO) throws Exception;
}
