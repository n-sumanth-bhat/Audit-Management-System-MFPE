package com.mfpe.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mfpe.feign.AuditBenchmarkFeign;
import com.mfpe.model.AuditBenchmark;
import com.mfpe.model.AuditDetail;
import com.mfpe.model.AuditQuestion;
import com.mfpe.model.AuditRequest;
import com.mfpe.model.AuditResponse;
import com.mfpe.service.AuditResponseService;
import com.mfpe.service.AuthorizationService;

@SpringBootTest
class AuditSeverityControllerTests {

	@Mock
	AuditResponseService auditResponseService;

	@Mock
	AuthorizationService authorizationService;

	@Mock
	AuditBenchmarkFeign auditBenchmarkFeign;

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

}
