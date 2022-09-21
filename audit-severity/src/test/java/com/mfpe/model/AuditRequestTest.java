package com.mfpe.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class AuditRequestTest {

	@Test
	void GetterAndSetterTest() {
		List<AuditQuestion> questions = new ArrayList<AuditQuestion>();
		questions.add(new AuditQuestion(1, "question", "auditType", "response"));
		
		Date date = new Date();
		AuditDetail ad = new AuditDetail();
		ad.setAuditDate(date);
		ad.setAuditQuestions(questions);
		ad.setAuditType("sox");
		
		
		AuditRequest obj = new AuditRequest();
		obj.setAuditDetail(ad);
		obj.setManagerName("SN");
		obj.setProjectName("project1");
		
		
		AuditDetail auditDetail = obj.getAuditDetail();
		String managerName = obj.getManagerName();
		String projectName = obj.getProjectName();
		
		assertEquals(auditDetail,ad);
		assertEquals(managerName,"SN");
		assertEquals(projectName,"project1");
	}

}
