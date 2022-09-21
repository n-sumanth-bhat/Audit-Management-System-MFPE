package com.mfpe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.model.AuthenticationRequest;
import com.mfpe.model.AuthenticationResponse;
import com.mfpe.model.ProjectManagerDetails;
import com.mfpe.service.JwtService;
import com.mfpe.service.ProjectManagerDetailsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth") // Context Root
@CrossOrigin(origins = "*")
@Slf4j
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ProjectManagerDetailsService projectManagerDetailsService;
	
	
	@Autowired
	private JwtService jwtService;

	/**
	 * 
	 * @return ResponseEntity
	 */
	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() { // for Health check [PERMITTED FOR ALL]
		return new ResponseEntity<String>("Audit-Authorization MS Running Fine!!", HttpStatus.OK);
	}

	/**
	 * 
	 * @param request
	 * @return response
	 */
	@PostMapping("/authenticate")
	public ResponseEntity<String> generateJwt(@Valid @RequestBody AuthenticationRequest request) {
		ResponseEntity<String> response = null;

		// authenticating the User-Credentials
		try {
			//System.out.println("The entered password is"+request.getPassword());
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			
			// else when it authenticates successfully
			final ProjectManagerDetails projectManagerDetails = projectManagerDetailsService
					.loadUserByUsername(request.getUsername());

			final String jwt = jwtService.generateToken(projectManagerDetails); // returning the token as response

			// test
			log.info("Authenticated User :: {}", projectManagerDetails);

			response = new ResponseEntity<String>(jwt, HttpStatus.OK);

			log.info("Successfully Authenticated user!");

		} catch (Exception e) {
			log.error("{} !! info about request-body : {}", e.getMessage(), request);
			System.out.println(e);
			response = new ResponseEntity<String>("Not Authorized Project Manager", HttpStatus.FORBIDDEN);
		}
		log.info("-------- Exiting /authenticate");
		return response;
	}

	/**
	 * 
	 * @param jwt
	 * @return response
	 */
	@PostMapping("/validate")
	public ResponseEntity<AuthenticationResponse> validateJwt(@RequestHeader("Authorization") String jwt) {

		AuthenticationResponse authenticationResponse = new AuthenticationResponse("Invalid", "Invalid", false);
		ResponseEntity<AuthenticationResponse> response = null;

		// first remove Bearer from Header
		jwt = jwt.substring(7);

		// check token
		log.info("--------JWT :: {}", jwt);

		// check the jwt is proper or not
		final ProjectManagerDetails projectManagerDetails = projectManagerDetailsService
				.loadUserByUsername(jwtService.extractUsername(jwt));

		// now validating the jwt
		try {
			if (jwtService.validateToken(jwt, projectManagerDetails)) {
				authenticationResponse.setName(projectManagerDetails.getName());
				authenticationResponse.setProjectName(projectManagerDetails.getProjectName());
				authenticationResponse.setValid(true);
				response = new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);
				log.info("Successfully validated the jwt and sending response back!");
			} else {
				response = new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);
				log.error("JWT Token validation failed!");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			response = new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);
			log.error("Exception occured whil validating JWT : Exception info : {}", e.getMessage());
		}
		log.info("-------- Exiting /validate");
		return response;
	}

}
