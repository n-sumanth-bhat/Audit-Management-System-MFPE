package com.mfpe.service;

import com.mfpe.model.AuditRequest;
import com.mfpe.model.AuditResponse;

public interface QuestionResponsesService {

	public AuditResponse questionresponse(AuditRequest auditRequest,String jwt);
}
