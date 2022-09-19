package com.mfpe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mfpe.model.AuditResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

	/**
	 * 
	 * @param e
	 * @return ResponseEntity
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<?> exception(Exception e) {
		log.error(e.getMessage());
		AuditResponse auditResponse = new AuditResponse();
		return new ResponseEntity<>(auditResponse, HttpStatus.OK);
	}

	@ExceptionHandler(value = ValidationException.class)
	@ResponseBody
	public ResponseEntity<?> ValidationException(ValidationException validataionException) {
		log.error(validataionException.getMessage());
		return new ResponseEntity<>(validataionException.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
