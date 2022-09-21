package com.mfpe.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class AuditDetailTest {

	@Test
	void GetterAndSetterTest() {
		
		List<AuditQuestion> questions = new ArrayList<AuditQuestion>();
		questions.add(new AuditQuestion(1, "question", "auditType", "response"));
		
		
		Date date = new Date();
		AuditDetail ad = new AuditDetail();
		ad.setAuditDate(date);
		ad.setAuditQuestions(questions);
		ad.setAuditType("sox");
		
		Date auditDate = ad.getAuditDate();
		List<AuditQuestion> auditQuestions = ad.getAuditQuestions();
		String auditType = ad.getAuditType();
		
		assertEquals(auditDate,date);
		assertEquals(auditQuestions,questions);
		assertEquals(auditType,"sox");


		
		
	}

}
