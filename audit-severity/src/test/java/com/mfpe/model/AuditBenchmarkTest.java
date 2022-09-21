package com.mfpe.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AuditBenchmarkTest {

	@Test
	void GetterAndSetterTest() {
		AuditBenchmark obj = new AuditBenchmark();
		obj.setAuditType("sox");
		obj.setBenchmarkId(18);
		obj.setBenchmarkNoAnswers(1);
		
		String auditType = obj.getAuditType();
		int benchmarkId = obj.getBenchmarkId();
		int benchmarkNoAnswers = obj.getBenchmarkNoAnswers();
		
		assertEquals(auditType,"sox");
		assertEquals(benchmarkId,18);
		assertEquals(benchmarkNoAnswers,1);

		
	}

}
