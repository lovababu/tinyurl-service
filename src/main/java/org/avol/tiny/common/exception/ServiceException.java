package org.avol.tiny.common.exception;

import org.avol.tiny.common.error.ErrorCode;

/**
 * @author Durga, Padala on 10/06/18.
 */
public class ServiceException extends RuntimeException {

    private String errorCode;
    private String message;

    public ServiceException(ErrorCode errorCode) {
        this.errorCode = String.valueOf(errorCode.getStatusCode());
        this.message = errorCode.getMessage();
    }

    public static ServiceException wrap(Throwable throwable) {
        return throwable instanceof ServiceException ? (ServiceException)throwable : new ServiceException(ErrorCode.SERVER_ERROR);
    }
}
