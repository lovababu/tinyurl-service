package org.avol.tinyservice.url.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Request body container class.
 *
 * Created by lovababu on 10/06/18.
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiRequest {
    private String originalUrl;
    private long expiryTime;
    private String customKey;
}
