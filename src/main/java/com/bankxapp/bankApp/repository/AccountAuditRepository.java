package com.bankxapp.bankApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankxapp.bankApp.model.AccountAuditModel;

public interface AccountAuditRepository  extends JpaRepository<AccountAuditModel, Long> {

}
