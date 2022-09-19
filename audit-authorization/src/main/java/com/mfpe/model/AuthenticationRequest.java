package com.mfpe.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class AuthenticationRequest {
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
}
