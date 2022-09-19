package com.mfpe.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.exception.ValidationException;
import com.mfpe.model.Question;
import com.mfpe.service.AuthorizationService;
import com.mfpe.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/checklist")
@CrossOrigin(origins = "*")
@Slf4j
public class AuditChecklistController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private AuthorizationService authorizationService;

	/**
	 * 
	 * @param jwt
	 * @param auditType
	 * @return questions
	 */
	@RequestMapping(value = "/auditchecklistquestions/{auditType}", method = { RequestMethod.GET })
	public List<Question> auditCheckListQuestions(@RequestHeader("Authorization") String jwt,
			@PathVariable String auditType) {
		List<Question> questions = new ArrayList<Question>();

		log.info("from header JWT :: {}", jwt);

		// checking if the jwt is valid or not

		if (jwt.length() > 0 && authorizationService.validateJwt(jwt)) {
			questions = questionService.getQuestionsByAuditType(auditType);
		} else {
			throw new ValidationException("The jwt token is not valid");
		}
		return questions;
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/health-check")
	public String healthCheck() {
		return "Audit Checklist Microservice is Active";
	}
}
