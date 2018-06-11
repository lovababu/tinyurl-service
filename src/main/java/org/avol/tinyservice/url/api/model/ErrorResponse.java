package org.avol.tinyservice.url.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.avol.tinyservice.common.error.ErrorCode;

/**
 * @author Durga, Padala on 10/06/18.
 */
@Getter
public class ErrorResponse {
    private String code;
    private String message;

    @JsonCreator
    public ErrorResponse(@JsonProperty("code") String code,
                         @JsonProperty("message") String message) {
        this.code = code;
        this.message = message;
    }
    public ErrorResponse(ErrorCode errorCode) {
        this.code = String.valueOf(errorCode.getStatusCode());
        this.message = errorCode.getMessage();
    }
}
