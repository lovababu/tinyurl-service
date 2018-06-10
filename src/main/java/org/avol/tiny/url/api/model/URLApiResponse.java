package org.avol.tiny.url.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *
 * Response representation class.
 *
 * Created by lovababu on 10/06/18.
 */
@Setter
@Getter
public class URLApiResponse {
    private String shortUrl;
    private LocalDateTime expiryTime;
}
