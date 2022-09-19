package com.mfpe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpe.model.AuditBenchmark;
import com.mfpe.repository.AuditBenchmarkRepo;

@Service
public class AuditBenchmarkServiceImpl implements AuditBenchmarkService {

	@Autowired
	AuditBenchmarkRepo auditBenchmarkRepo;

	public List<AuditBenchmark> getAuditBenchmarkList() {
		return auditBenchmarkRepo.findAll();
	}

}
