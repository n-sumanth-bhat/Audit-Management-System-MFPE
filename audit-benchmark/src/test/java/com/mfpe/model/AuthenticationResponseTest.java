package com.mfpe.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AuthenticationResponseTest {

	@Test
	void TestingGettersAndSetters() {
		AuthenticationResponse obj = new AuthenticationResponse();
		obj.setName("abc");
		obj.setProjectName("project1");
		obj.setValid(false);
		
		String name = obj.getName();
		String projectName = obj.getProjectName();
		boolean valid = obj.isValid();
		
		assertEquals(name,"abc");
		assertEquals(projectName,"project1");
		assertEquals(valid,false);

	}
}
