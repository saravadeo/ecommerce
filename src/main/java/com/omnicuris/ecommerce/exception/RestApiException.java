package com.omnicuris.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global REST end point exception handler
 * 
 * @author Onkar Saravade
 *
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RestApiException extends RuntimeException {

  private static final long serialVersionUID = 4671329588454194630L;
  private final HttpStatus statusCode;

  public RestApiException(final String message, final HttpStatus statusCode) {
    super(message);
    this.statusCode = statusCode;
  }

  public HttpStatus getStatusCode() {
    return statusCode;
  }

}
