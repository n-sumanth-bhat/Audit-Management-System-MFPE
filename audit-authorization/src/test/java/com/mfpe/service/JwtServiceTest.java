package com.mfpe.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.mfpe.model.ProjectManagerDetails;

import io.jsonwebtoken.Jwts;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {
	
//	@Mock
//	private Jwts jwt;
	
	private String SECRETKEY = "secret";

//	@Value("${tokenduration}")
//	private int TOKENDURATION;
	
	@InjectMocks
	private JwtService service;

//	@Test
//	void extractUserNameTest() {
//		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
//		when(service.extractUsername(token)).thenReturn("John Doe");
//		String username = service.extractUsername(token);
//	}
	
	@Test
	public void generateTokenTest() {
		ProjectManagerDetails projectManagerDetails = null;
		projectManagerDetails = new ProjectManagerDetails();
		projectManagerDetails.setId(1);
		projectManagerDetails.setName("user1"); // same username
		projectManagerDetails.setPassword("abcd1234");
		projectManagerDetails.setProjectName("Project1");
		
		
//		String generateToken = service.generateToken(projectManagerDetails);
		
	}

}
