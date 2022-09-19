package com.mfpe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfpe.model.AuditResponse;

public interface AuditResponseRepo extends JpaRepository<AuditResponse, Integer> {

}
