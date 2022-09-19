package com.mfpe.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mfpe.model.ProjectManagerDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${secretkey}")
	private String SECRETKEY;

	@Value("${tokenduration}")
	private int TOKENDURATION;

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		String formated_token = token.trim().replaceAll("\0xfffd", "");
		return Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(formated_token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(ProjectManagerDetails projectManagerDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, projectManagerDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String username) {

		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKENDURATION * 60 * 1000))
				.signWith(SignatureAlgorithm.HS256, SECRETKEY).compact();
	}

	public Boolean validateToken(String token, ProjectManagerDetails projectManagerDetails) {
		final String username = extractUsername(token);
		return (username.equals(projectManagerDetails.getUsername()) && !isTokenExpired(token));
	}
}