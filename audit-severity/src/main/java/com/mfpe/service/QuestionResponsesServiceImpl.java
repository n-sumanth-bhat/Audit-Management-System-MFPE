package com.mfpe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpe.feign.AuditBenchmarkFeign;
import com.mfpe.model.AuditBenchmark;
import com.mfpe.model.AuditQuestion;
import com.mfpe.model.AuditRequest;
import com.mfpe.model.AuditResponse;
import com.mfpe.model.AuditType;

@Service

public class QuestionResponsesServiceImpl implements QuestionResponsesService{

	@Autowired
	private AuditResponseService auditResponseService;
	

	@Autowired
	private AuditBenchmarkFeign auditBenchmarkFeign;

	public AuditResponse questionresponse(AuditRequest auditRequest,String jwt) {
		
		List<AuditBenchmark> benchmarkList = auditBenchmarkFeign.getAuditBenchmark(jwt);
		AuditResponse auditResponse = new AuditResponse();

		AuditType auditType = new AuditType();

		auditType.setAuditType(auditRequest.getAuditDetail().getAuditType()); // setting auditType

		// getting responses back from RequestBody
		List<AuditQuestion> questionResponses = auditRequest.getAuditDetail().getAuditQuestions();

		// create Audit-response
		auditResponse = auditResponseService.getAuditResponse(benchmarkList, auditType.getAuditType(),
				questionResponses);

		// saving response in DB
		auditResponse = auditResponseService.saveAuditResponse(auditResponse, auditRequest);
		return auditResponse;

	}

}
