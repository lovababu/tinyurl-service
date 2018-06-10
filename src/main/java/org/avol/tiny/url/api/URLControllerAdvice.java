package org.avol.tiny.url.api;

import org.avol.tiny.common.error.ErrorCode;
import org.avol.tiny.url.api.model.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Advise class handles the exceptions in system.
 * Created by lovababu on 10/06/18.
 */
@RestControllerAdvice
public class URLControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponse(ErrorCode.UNSUPPORTED_MEDIA, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponse(ErrorCode.PAYLOAD_INVALID, status);
    }

    private ResponseEntity<Object> buildResponse(ErrorCode errorCode, HttpStatus status) {
        return new ResponseEntity<>(new ErrorResponse(errorCode), status);
    }
}
