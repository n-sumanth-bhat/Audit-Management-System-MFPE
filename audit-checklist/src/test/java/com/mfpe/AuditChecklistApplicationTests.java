package com.mfpe;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuditChecklistApplicationTests {

	@Autowired
	AuditChecklistApplication auditChecklistApplication;

	@Test
	void contextLoads() {
		assertNotNull(auditChecklistApplication);
	}

}
