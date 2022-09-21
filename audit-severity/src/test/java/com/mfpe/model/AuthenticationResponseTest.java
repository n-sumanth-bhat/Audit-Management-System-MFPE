package com.mfpe.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AuthenticationResponseTest {

	@Test
	void GetterAndSetterTest() {
		AuthenticationResponse resp = new AuthenticationResponse();
		resp.setName("SN");
		resp.setProjectName("project1");
		resp.setValid(false);
		
		String name = resp.getName();
		String projectName = resp.getProjectName();
		boolean valid = resp.isValid();
		
		assertEquals(name,"SN");
		assertEquals(projectName,"project1");
		assertEquals(valid,false);


	}

}
