package com.bankxapp.bankApp.dto;

public class AccountTransactionDTO {

	private Long accountNumberFrom;
	
	private Long accountNumberTo;
	
	private Double amount;

	public Long getAccountNumberFrom() {
		return accountNumberFrom;
	}

	public void setAccountNumberFrom(Long accountNumberFrom) {
		this.accountNumberFrom = accountNumberFrom;
	}

	public Long getAccountNumberTo() {
		return accountNumberTo;
	}

	public void setAccountNumberTo(Long accountNumberTo) {
		this.accountNumberTo = accountNumberTo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
