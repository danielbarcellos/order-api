package com.ambev.order.exception;

import java.io.IOException;
import java.text.ParseException;
import java.util.NoSuchElementException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.ambev.order.dto.ApiErrorDTO;
import com.fasterxml.jackson.core.JsonProcessingException;




@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler {

//  Logger log = LogManager.getLogger(RestExceptionHandler.class);

  private ResponseEntity<ApiErrorDTO> buildResponseEntityApiError(ApiErrorDTO apiError) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(apiError, headers, apiError.getStatus());
  }

  @ExceptionHandler({NoSuchElementException.class})
  protected ResponseEntity<ApiErrorDTO> handleEntityNotFound(Exception ex) {

	ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.NOT_FOUND);
    apiError.setMessage("Registro não localizado");
    return buildResponseEntityApiError(apiError);
  }

  @ExceptionHandler({IOException.class, JsonProcessingException.class, Exception.class})
  protected ResponseEntity<ApiErrorDTO> handleIOException(Exception ex) {
    ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR);
    apiError.setMessage(ex.getMessage());
    return buildResponseEntityApiError(apiError);
  }

  @ExceptionHandler(HttpClientErrorException.class)
  protected ResponseEntity<ApiErrorDTO> handleException(HttpClientErrorException ex) {

    ApiErrorDTO apiError = new ApiErrorDTO(ex.getStatusCode());
    apiError.setMessage(ex.getMessage());
    apiError.setDebugMessage(ex.getResponseBodyAsString());
    return buildResponseEntityApiError(apiError);
  }
  
  @ExceptionHandler(ApiException.class)
  protected ResponseEntity<ApiErrorDTO> handleException(ApiException ex) {
	  
	  ApiErrorDTO apiError = new ApiErrorDTO(ex.getCode());
	  apiError.setMessage(ex.getMessage());
	  apiError.setDebugMessage(ex.getResponseBody());
	  return buildResponseEntityApiError(apiError);
  }

  @ExceptionHandler(ParseException.class)
  protected ResponseEntity<ApiErrorDTO> handldParseException(ParseException ex) {
    org.springframework.util.MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add("content-type", "application/json");

    ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.BAD_REQUEST);
    apiError.setMessage(ex.getMessage());
    return buildResponseEntityApiError(apiError);
  }

  @ResponseBody
  @ExceptionHandler({HttpServerErrorException.class})
  protected ResponseEntity<ApiErrorDTO> handleException(HttpServerErrorException ex) {

    ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.SERVICE_UNAVAILABLE);
    apiError.setMessage(ex.getMessage());
    apiError.setDebugMessage(ex.getResponseBodyAsString());
    return buildResponseEntityApiError(apiError);
  }

  @ResponseBody
  @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
  protected ResponseEntity<ApiErrorDTO> handleException(HttpMediaTypeNotSupportedException ex) {

    ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    apiError.setMessage(ex.getMessage());
    return buildResponseEntityApiError(apiError);
  }
}
