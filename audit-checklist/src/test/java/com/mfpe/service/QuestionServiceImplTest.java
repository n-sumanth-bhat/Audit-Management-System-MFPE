package com.mfpe.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.mfpe.model.AuditType;
import com.mfpe.model.Question;
import com.mfpe.repository.QuestionRepo;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {
	
	@Mock
	private QuestionRepo questionRepo;
		
	@InjectMocks
	private QuestionServiceImpl qServiceImpl;

	@Test
	void getQuestionsByAuditTypeTest() {
		List<Question> abc = new ArrayList<Question>();
		AuditType auditType = new AuditType();

		Question questions = new Question();
		auditType.setAuditType("internal");
		questions.setAuditType(auditType.getAuditType());
		questions.setQuestionId(1);
		questions.setQuestion("How r u");
		questions.setResponse("No");
		abc.add(questions);
		when(questionRepo.getQuestionsByAuditType("internal")).thenReturn(abc);
		qServiceImpl.getQuestionsByAuditType("internal");
	}
	
	@Test
	void getQuestionsByAuditTypeTest2() {
		AuditType auditType1 = new AuditType("sox");
		Question questions = new Question();
		questions.setAuditType("internal");
		questions.setQuestionId(1);
		questions.setQuestion("How r u");
		questions.setResponse("No");
		
		
		String auditType = questions.getAuditType();
		int questionId = questions.getQuestionId();
		String question = questions.getQuestion();
		String response = questions.getResponse();
		assertEquals(questionId,1);
		assertEquals(auditType,"internal");
		assertEquals(question,"How r u");
		assertEquals(response,"No");

	
	}
	

}
