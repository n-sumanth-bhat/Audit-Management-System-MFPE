package com.mfpe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mfpe.model.Question;
import com.mfpe.service.AuthorizationService;
import com.mfpe.service.QuestionService;

@SpringBootTest
public class AuditChecklistControllerTests {

	@Mock
	QuestionService questionService;

	@Mock
	AuthorizationService authorizationService;

	@InjectMocks
	AuditChecklistController controller;

	@Test
	public void contextLoads() {
		assertNotNull(controller);
	}

	@Test
	public void testHealthCheck() {
		assertEquals("Audit Checklist Microservice is Active", controller.healthCheck());
	}

	@Test
	public void testAuditChecklistQuestions() {
		List<Question> questions = new ArrayList<Question>();
		questions.add(new Question(1, "question", "auditType", "response"));

		when(authorizationService.validateJwt("jwt")).thenReturn(true);

		// AuditType auditType = new AuditType("auditType");
		String auditType = null;
		when(questionService.getQuestionsByAuditType(auditType)).thenReturn(questions);

		assertEquals(questions, controller.auditCheckListQuestions("jwt", auditType));
	}
}
