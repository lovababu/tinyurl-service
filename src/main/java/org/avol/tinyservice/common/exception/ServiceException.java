package org.avol.tinyservice.common.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.avol.tinyservice.common.error.ErrorCode;

/**
 * @author Durga, Padala on 10/06/18.
 */
@Slf4j
public class ServiceException extends RuntimeException {

    @Getter
    private ErrorCode errorCode;

    public ServiceException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public static ServiceException wrap(Throwable throwable) {
        log.error("Exception: ", throwable);
        return throwable instanceof ServiceException ? (ServiceException)throwable : new ServiceException(ErrorCode.SERVER_ERROR);
    }
}
