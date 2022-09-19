package com.mfpe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpe.feign.AuthorizationFeign;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	@Autowired
	private AuthorizationFeign authClient;

	@Override
	public boolean validateJwt(String jwt) {
		return authClient.validate(jwt).getBody().isValid();

	}

}
