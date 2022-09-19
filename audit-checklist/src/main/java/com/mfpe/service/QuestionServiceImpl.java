package com.mfpe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpe.model.Question;
import com.mfpe.repository.QuestionRepo;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepo questionRepo;

	// Service for the Endpoint /AuditCheckListQuestions
	public List<Question> getQuestionsByAuditType(String auditType) {
		List<Question> qlist = questionRepo.getQuestionsByAuditType(auditType);
		return qlist;
	}

}
