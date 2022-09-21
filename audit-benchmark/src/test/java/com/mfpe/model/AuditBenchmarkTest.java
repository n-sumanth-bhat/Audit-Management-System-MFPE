package com.mfpe.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AuditBenchmarkTest {

	@Test
	void GetterAndSetterTest() {
		AuditBenchmark ab = new AuditBenchmark();
		ab.setAuditType("sox");
		ab.setBenchmarkId(18);
		ab.setBenchmarkNoAnswers(1);
		
		String auditType = ab.getAuditType();
		int benchmarkId = ab.getBenchmarkId();
		int benchmarkNoAnswers = ab.getBenchmarkNoAnswers();
		
		assertEquals(auditType,"sox");
		assertEquals(benchmarkId,18);
		assertEquals(benchmarkNoAnswers,1);


	}

}
