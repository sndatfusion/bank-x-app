package com.bankxapp.bankApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_audit")
public class AccountAuditModel {
	
	@Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Long id;
	
	
	private String contentJson;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContentJson() {
		return contentJson;
	}

	public void setContentJson(String contentJson) {
		this.contentJson = contentJson;
	}
}
