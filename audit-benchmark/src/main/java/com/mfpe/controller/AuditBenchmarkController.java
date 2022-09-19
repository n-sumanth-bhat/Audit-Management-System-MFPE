package com.mfpe.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.exception.ValidationException;
import com.mfpe.model.AuditBenchmark;
import com.mfpe.service.AuditBenchmarkService;
import com.mfpe.service.AuthorizationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/benchmark")
@CrossOrigin(origins = "*")
@Slf4j
public class AuditBenchmarkController {

	@Autowired
	private AuthorizationService authorizationService;

	@Autowired
	private AuditBenchmarkService auditBenchmarkService;

	/**
	 * 
	 * @param jwt
	 * @return auditBenchmarks
	 */
	@GetMapping("/audit-benchmark")
	public List<AuditBenchmark> getAuditBenchmark(@RequestHeader("Authorization") String jwt) {
		List<AuditBenchmark> auditBenchmarks = new ArrayList<>();

		// checking if the jwt is valid or not
		log.info("from header JWT :: {}",jwt);

		if (jwt.length() > 0 && authorizationService.validateJwt(jwt)) {
			auditBenchmarks = auditBenchmarkService.getAuditBenchmarkList();
		} else {
			throw new ValidationException("The jwt token is not valid");
		}
		return auditBenchmarks;
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/health-check")
	public String healthCheck() {
		return "Audit Benchmark Microservice is Active";
	}

}
