package org.avol.tinyservice.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Possible Error codes system can throw.
 *
 * Created by lovababu on 10/06/18.
 */
@Getter
public enum  ErrorCode {
    UNSUPPORTED_MEDIA(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), ErrorMessage.UNSUPPORTED_MEDIA_TYPE),
    PAYLOAD_INVALID(HttpStatus.BAD_REQUEST.value(), ErrorMessage.MALFORMED_PAYLOAD),
    KEY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), ErrorMessage.KEY_NOT_FOUND),
    DUPLICATE_KEY(HttpStatus.CONFLICT.value(), ErrorMessage.DUPLICATE_KEY),
    INVALID_CUSTOM_KEY(HttpStatus.BAD_REQUEST.value(), ErrorMessage.INVALID_CUSTOM_KEY),
    INVALID_URL(HttpStatus.BAD_REQUEST.value(), ErrorMessage.INVALID_URL),
    INVALID_EXPIRY(HttpStatus.BAD_REQUEST.value(), ErrorMessage.INVALID_EXPIRY),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorMessage.SERVER_ERROR);

    private int statusCode;
    private String message;

    ErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    class ErrorMessage {

        static final String DUPLICATE_KEY = "Key is already exist.";
        static final String KEY_NOT_FOUND = "Either Key is invalid or expired.";
        static final String INVALID_URL = "Supplied URL is not a valid.";
        static final String INVALID_EXPIRY = "Supplied Expiry time is not a valid.";
        static final String SERVER_ERROR = "Experiencing with server error, please try later.";
        static final String UNSUPPORTED_MEDIA_TYPE = "Unsupported media type requested.";
        static final String MALFORMED_PAYLOAD = "Payload is malformed.";
        static final String INVALID_CUSTOM_KEY = "Supplied Custom key is not a valid.";
    }
}
