package com.mfpe.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.Test;

class AuditResponseTest {

	@Test
	void GetterAndSetterTest() {
		
//		AuditResponse obj = new AuditResponse(1, "ManagerName", "ProjectName", new Date(),
//				"ProjectExecutionStatus", "RemedialActionDuration");
		AuditResponse obj = new AuditResponse();
		Date date = new Date();
		
		obj.setAuditId(1);
		obj.setCreationDateTime(date);
		obj.setManagerName("SN");
		obj.setProjectExecutionStatus("ProjectExecutionStatus");
		obj.setRemedialActionDuration("RemedialActionDuration");
		
		int auditId = obj.getAuditId();
		Date creationDateTime = obj.getCreationDateTime();
		String managerName = obj.getManagerName();
		String remedialActionDuration = obj.getRemedialActionDuration();
		String projectExecutionStatus = obj.getProjectExecutionStatus();
		
		assertEquals(auditId,1);
		assertEquals(creationDateTime,date);
		assertEquals(managerName,"SN");
		assertEquals(remedialActionDuration,"RemedialActionDuration");
		assertEquals(projectExecutionStatus,"ProjectExecutionStatus");

	}
	

}
