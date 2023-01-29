package com.villasenorfj.example.error;

import com.villasenorfj.example.ex.PriceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppErrorController extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(PriceNotFoundException.class)
	protected ResponseEntity<AppError> priceNotFoundException(PriceNotFoundException e) {
		AppError err = AppError.builder().message("Price not found").status(HttpStatus.NOT_FOUND.value()).build();
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<AppError> generalException(Exception e) {
		AppError err = AppError.builder().message(e.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
		return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
