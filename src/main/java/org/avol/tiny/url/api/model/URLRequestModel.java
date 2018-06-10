package org.avol.tiny.url.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Request body container class.
 *
 * Created by lovababu on 10/06/18.
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class URLRequestModel {
    private String originalUrl;
    private LocalDateTime expiryTime;
    private String uniqueKey;
}
