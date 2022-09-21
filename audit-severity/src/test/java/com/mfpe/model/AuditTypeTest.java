package com.mfpe.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AuditTypeTest {

	@Test
	void GetterAndSetterTest() {
		AuditType type = new AuditType();
		type.setAuditType("sox");
		String auditType = type.getAuditType();
		
		assertEquals(auditType,"sox");
	}

}
