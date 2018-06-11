package org.avol.tinyservice.url.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 *
 * Response representation class.
 *
 * Created by lovababu on 10/06/18.
 */
@Setter
@Getter
@Accessors
public class ApiResponse {
    private String shortUrl;
    private LocalDateTime expiryTime;
}
