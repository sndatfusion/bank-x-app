package com.bankxapp.bankApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_transaction")
public class AccountTransactionModel {
	
	@Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Long id;
	
	
	private Long accountNumberFrom;
	
	private Long accountNumberTo;
	
	private Double amount;
	
	private Double amountWithDiscount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Double getAmountWithDiscount() {
		return amountWithDiscount;
	}

	public void setAmountWithDiscount(Double amountWithDiscount) {
		this.amountWithDiscount = amountWithDiscount;
	} 
}
