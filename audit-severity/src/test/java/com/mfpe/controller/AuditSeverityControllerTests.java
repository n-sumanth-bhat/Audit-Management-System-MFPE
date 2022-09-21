package com.mfpe.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mfpe.exception.ValidationException;
import com.mfpe.feign.AuditBenchmarkFeign;
import com.mfpe.model.AuditBenchmark;
import com.mfpe.model.AuditDetail;
import com.mfpe.model.AuditQuestion;
import com.mfpe.model.AuditRequest;
import com.mfpe.model.AuditResponse;
import com.mfpe.service.AuditResponseService;
import com.mfpe.service.AuthorizationService;
import com.mfpe.service.QuestionResponsesServiceImpl;

@SpringBootTest
class AuditSeverityControllerTests {

	@Mock
	AuditResponseService auditResponseService;

	@Mock
	AuthorizationService authorizationService;

	@Mock
	AuditBenchmarkFeign auditBenchmarkFeign;
	
	@Mock
	private QuestionResponsesServiceImpl questionResponsesService;

	@InjectMocks
	AuditSeverityController severityController;

	@Test
	public void testSeverityController() throws Exception {
		assertThat(severityController).isNotNull();
	}


	@Test
	public void testAuditHealthCheck() {
		assertEquals(severityController.healthCheck(), "Audit Severity Microservice is Active");
	}
	
	@Test
	public void auditSeverityTest() {
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
		
		AuditResponse auditResponse = new AuditResponse(1, "ManagerName", "ProjectName", new Date(),
				"ProjectExecutionStatus", "RemedialActionDuration");
		
		when(authorizationService.validateJwt("jwt")).thenReturn(true);
		
		when(questionResponsesService.questionresponse(obj, "jwt")).thenReturn(auditResponse);
		
		severityController.auditSeverity("jwt",obj);
	}
	
	
	@Test
	public void auditSeverityTest1() {
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
		
	
		when(authorizationService.validateJwt("jwt")).thenReturn(false);
		
		
		assertThrows(ValidationException.class,()-> severityController.auditSeverity("jwt",obj));
	}

}
