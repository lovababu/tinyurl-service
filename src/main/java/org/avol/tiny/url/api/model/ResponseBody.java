package org.avol.tiny.url.api.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 *
 * Response representation class.
 *
 * Created by lovababu on 10/06/18.
 */
@Builder
@Getter
public class ResponseBody {
    private String shortUrl;
    private LocalDateTime expiryTime;
}
