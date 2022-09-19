package com.mfpe.service;

public interface AuthorizationService {

	public boolean validateJwt(String jwt);
}
