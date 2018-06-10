package org.avol.tiny.url.api.model;

import lombok.Getter;
import org.avol.tiny.common.error.ErrorCode;

/**
 * @author Durga, Padala on 10/06/18.
 */
@Getter
public class ErrorResponse {
    private String code;
    private String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.code = String.valueOf(errorCode.getStatusCode());
        this.message = errorCode.getMessage();
    }
}
