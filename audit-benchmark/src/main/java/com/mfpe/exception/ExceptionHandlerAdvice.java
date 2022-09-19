package com.mfpe.exception;

import java.util.ArrayList;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
		List<String> dummyList = new ArrayList<>();
		return new ResponseEntity<>(dummyList, HttpStatus.OK);
	}

	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<?> handleValidationException(ValidationException validataionException) {

		log.error(validataionException.getMessage());
		return new ResponseEntity<>(validataionException.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
