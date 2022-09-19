package com.mfpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.exception.ValidationException;
import com.mfpe.feign.AuditBenchmarkFeign;
import com.mfpe.model.AuditRequest;
import com.mfpe.model.AuditResponse;
import com.mfpe.service.AuthorizationService;
import com.mfpe.service.QuestionResponsesServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/severity")
@CrossOrigin(origins = "*")
@Slf4j
public class AuditSeverityController {

	@Autowired
	private AuditBenchmarkFeign auditBenchmarkFeign;

	@Autowired
	private AuthorizationService authorizationService;

	@Autowired
	private QuestionResponsesServiceImpl questionResponsesService;

	/**
	 * 
	 * @return
	 */
	@GetMapping("/health-check")
	public String healthCheck() {
		return "Audit Severity Microservice is Active";
	}

	// This is to check the severity of the audit and it returns the execution
	// status of the project
	/**
	 * 
	 * @param jwt
	 * @param auditRequest
	 * @return response
	 */
	@PostMapping("/projectexecutionstatus")
	public ResponseEntity<?> auditSeverity(@RequestHeader("Authorization") String jwt,
			@RequestBody AuditRequest auditRequest) {

		AuditResponse auditResponse = new AuditResponse();

		ResponseEntity<AuditResponse> response = null;

		// checking if the jwt is valid or not
		// creating auditResponse according to auditRequest

		if (jwt.length() > 0 && authorizationService.validateJwt(jwt)) {
			log.info("Successfully validated the JWT :: {}", jwt);

			// getting benchmark list from Benchmark-MS

			auditResponse = questionResponsesService.questionresponse(auditRequest, jwt);

			log.info("AuditResponse successfully created!!");

			response = new ResponseEntity<AuditResponse>(auditResponse, HttpStatus.OK);
		} else {
			throw new ValidationException("The jwt token is not valid");
		}
		return response;

	}
}
