package com.mfpe.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mfpe.exception.ProjectManagerNotFoundException;
import com.mfpe.model.ProjectManagerDetails;
import com.mfpe.service.JwtService;
import com.mfpe.service.ProjectManagerDetailsService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private ProjectManagerDetailsService projectManagerDetailsService;

	@Autowired
	private JwtService jwtService;

	/**
	 * 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, ProjectManagerNotFoundException {

		final String jwtRequestHeader = request.getHeader("Authorization");

		log.info("Inside JwtRequestFilter : {}", request.getRequestURI());

		String jwt = null, username = null;
		if (jwtRequestHeader != null && jwtRequestHeader.startsWith("Bearer ")) {
			jwt = jwtRequestHeader.substring(7);
			try {
				username = jwtService.extractUsername(jwt);
				log.info("Successfully obtained username : ({}) from JWT", username);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		} 
		else {
			log.error("Problem with JWT token obtained from Request-Header. JWT :: {}", jwt);
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			ProjectManagerDetails projectManagerDetails = projectManagerDetailsService.loadUserByUsername(username);
			if (jwtService.validateToken(jwt, projectManagerDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						projectManagerDetails, null, projectManagerDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				log.info("Successfully obtained and validated JWT :: {}", jwt);
			} 
			else {
				log.error("Validation failed for JWT :: {}", jwt);
			}
		} 
		else {
			log.error("Problem with JWT token obtained from Request-Header. JWT :: {}", jwt);
		}
		log.info("-------- Exiting JwtRequestFilter");
		filterChain.doFilter(request, response);
	}
}
