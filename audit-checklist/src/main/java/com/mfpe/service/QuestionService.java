package com.mfpe.service;

import java.util.List;

import com.mfpe.model.Question;

public interface QuestionService {

	public List<Question> getQuestionsByAuditType(String auditType);
}
