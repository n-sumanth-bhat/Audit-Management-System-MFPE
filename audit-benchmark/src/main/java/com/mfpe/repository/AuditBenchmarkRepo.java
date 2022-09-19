package com.mfpe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfpe.model.AuditBenchmark;

public interface AuditBenchmarkRepo extends JpaRepository<AuditBenchmark, Integer> {

}
