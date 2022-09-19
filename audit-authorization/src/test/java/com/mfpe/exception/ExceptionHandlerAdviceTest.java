package com.mfpe.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.MethodArgumentNotValidException;

class ExceptionHandlerAdviceTest {
	
	private ExceptionHandlerAdvice mainClass = new ExceptionHandlerAdvice();

	@Test
	void validationExceptionsTest() {
		mainClass.validationExceptions(new MethodArgumentNotValidException(null, null));
	}

}
