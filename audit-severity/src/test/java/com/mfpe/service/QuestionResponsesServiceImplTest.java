package com.mfpe.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mfpe.feign.AuditBenchmarkFeign;
import com.mfpe.model.AuditBenchmark;
import com.mfpe.model.AuditDetail;
import com.mfpe.model.AuditQuestion;
import com.mfpe.model.AuditRequest;
import com.mfpe.model.AuditResponse;

@ExtendWith(MockitoExtension.class)
class QuestionResponsesServiceImplTest {
	
	@Mock
	private AuditResponseService auditResponseService;
	
	@Mock
	private AuditBenchmarkFeign auditBenchmarkFeign;
	
	@InjectMocks
	private QuestionResponsesServiceImpl qservice;



	@Test
	void questionresponseTest() {

		List<AuditQuestion> questions = new ArrayList<AuditQuestion>();
		questions.add(new AuditQuestion(1, "question", "auditType", "response"));
		
		Date date = new Date();
		AuditDetail ad = new AuditDetail();
		ad.setAuditDate(date);
		ad.setAuditQuestions(questions);
		ad.setAuditType("sox");
		
		AuditRequest obj = new AuditRequest();
		obj.setAuditDetail(ad);
		obj.setManagerName("SN");
		obj.setProjectName("project1");
		
		List<AuditBenchmark> benchmark = new ArrayList<AuditBenchmark>();
		benchmark.add(new AuditBenchmark(18,"auditType",1));
		
		when(auditBenchmarkFeign.getAuditBenchmark("jwt")).thenReturn(benchmark);
//		when(auditResponseService.saveAuditResponse(new AuditResponse(), obj));
		qservice.questionresponse(obj, "jwt");
	}

}
