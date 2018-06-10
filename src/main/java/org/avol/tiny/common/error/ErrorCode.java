package org.avol.tiny.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Possible Error codes system can throw.
 *
 * Created by lovababu on 10/06/18.
 */
@Getter
public enum  ErrorCode {
    KEY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), ErrorMessage.KEY_NOT_FOUND),
    DUPLICATE_KEY(HttpStatus.CONFLICT.value(), ErrorMessage.DUPLICATE_KEY),
    INVALID_URL(HttpStatus.BAD_REQUEST.value(), ErrorMessage.INVALID_URL),
    INVALID_EXPIRY(HttpStatus.BAD_REQUEST.value(), ErrorMessage.INVALID_EXPIRY),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorMessage.SERVER_ERROR);

    private int statusCode;
    private String message;

    ErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
