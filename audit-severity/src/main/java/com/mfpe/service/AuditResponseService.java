package com.mfpe.service;

import java.util.List;

import com.mfpe.model.AuditBenchmark;
import com.mfpe.model.AuditQuestion;
import com.mfpe.model.AuditRequest;
import com.mfpe.model.AuditResponse;

public interface AuditResponseService {

	// public List<AuditResponse> getAuditResponses(List<AuditBenchmark>
	// benchmarkList,List<AuditQuestion> questionListInternal,List<AuditQuestion>
	// questionListSox);

	public AuditResponse getAuditResponse(List<AuditBenchmark> benchmarkList, String auditType,
			List<AuditQuestion> questionResponses);

	public AuditResponse saveAuditResponse(AuditResponse auditResponse, AuditRequest auditRequest);

}
