package com.mfpe.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class AuditQuestionTest {

	@Test
	void GetterAndSetterTest() {
		AuditQuestion question = new AuditQuestion();
		question.setAuditType("auditType");
		question.setQuestion("question");
		question.setQuestionId(1);
		question.setResponse("response");
		
		
		String auditType = question.getAuditType();
		String question2 = question.getQuestion();
		int questionId = question.getQuestionId();
		String response = question.getResponse();
		
		assertEquals(auditType,"auditType");
		assertEquals(question2,"question");
		assertEquals(questionId,1);
		assertEquals(response,"response");

	}

}
