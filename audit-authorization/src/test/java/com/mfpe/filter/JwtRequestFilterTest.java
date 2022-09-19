package com.mfpe.filter;

import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mfpe.exception.ProjectManagerNotFoundException;
import com.mfpe.model.ProjectManagerDetails;
import com.mfpe.service.JwtService;
import com.mfpe.service.ProjectManagerDetailsService;

@ExtendWith(MockitoExtension.class)
public class JwtRequestFilterTest {
	
	@Mock
	private ProjectManagerDetailsService projectManagerDetailsService;
	
	@Mock
	private JwtService jwtService;
	
	@Mock
	HttpServletRequest request;
	
	@InjectMocks
	private JwtRequestFilter filter;
	
	@Mock
	HttpServletResponse response;
	
	@Mock
	FilterChain chain;
	
	@Test
	public void doFilterInternalTest() throws ServletException, IOException, ProjectManagerNotFoundException {
		filter.doFilterInternal(request, response, chain);
	}
	
	@Test
	public void doFilterInternalTest2() throws ServletException, IOException, ProjectManagerNotFoundException {
		ProjectManagerDetails projectManagerDetails = null;
		projectManagerDetails = new ProjectManagerDetails();
		projectManagerDetails.setId(1);
		projectManagerDetails.setName("user1"); // same username
		projectManagerDetails.setPassword("abcd1234");
		projectManagerDetails.setProjectName("Project1");
		
		
		when(request.getHeader("Authorization")).thenReturn("Bearer token");
		when(jwtService.extractUsername("token")).thenReturn("user1");
		when(projectManagerDetailsService.loadUserByUsername("user1")).thenReturn(projectManagerDetails);
		when(jwtService.validateToken("token", projectManagerDetails)).thenReturn(true);
		
		
		filter.doFilterInternal(request, response, chain);
	}
	
	@Test
	public void doFilterInternalTest3() throws ServletException, IOException, ProjectManagerNotFoundException {
		when(request.getHeader("Authorization")).thenReturn("huccha token");

		
		filter.doFilterInternal(request, response, chain);
		
	}
	
	@Test
	public void doFilterInternalTest4() throws ServletException, IOException, ProjectManagerNotFoundException {
		
		when(request.getHeader("Authorization")).thenReturn("Bearer token");
		when(jwtService.extractUsername("token")).thenThrow(ProjectManagerNotFoundException.class);
		
		filter.doFilterInternal(request, response, chain);
		
	}
	
	
//	@Test
//	public void doFilterInternalTest5() throws ServletException, IOException, ProjectManagerNotFoundException {
//		ProjectManagerDetails projectManagerDetails = null;
//		projectManagerDetails = new ProjectManagerDetails();
//		projectManagerDetails.setId(1);
//		projectManagerDetails.setName("user1"); // same username
//		projectManagerDetails.setPassword("abcd1234");
//		projectManagerDetails.setProjectName("Project1");
//		
//		
//		when(request.getHeader("Authorization")).thenReturn("Bearer token");
//		when(jwtService.extractUsername("token")).thenReturn("user1");
////		when(projectManagerDetailsService.loadUserByUsername("user1")).thenReturn(null);
////		when(jwtService.validateToken("token", projectManagerDetails)).thenReturn(false);
//		
//		
//		filter.doFilterInternal(request, response, chain);
//	}



}
