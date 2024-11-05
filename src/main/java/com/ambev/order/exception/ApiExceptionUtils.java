package com.ambev.order.exception;


import org.springframework.http.HttpStatus;

public class ApiExceptionUtils {
	
	public static void assertTrue(boolean bool, HttpStatus httpStatus, String message) {
		if(!bool) {
			throw runApiException(httpStatus, message);
		}
	}

	public static void assertTrue(boolean bool, String message) {
		if(!bool) {
			throw runApiException(message);
		}
	}

	public static ApiException runApiException(String message) {
		return new ApiException(message);
	}
	
	public static ApiException runApiException(HttpStatus code, String message) {
		return new ApiException(code, message);
	}
	
}
