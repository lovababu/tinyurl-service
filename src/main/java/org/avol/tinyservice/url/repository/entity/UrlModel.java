package org.avol.tinyservice.url.repository.entity;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lovababu on 10/06/18.
 */
@Getter
@Setter
public class UrlModel {
    @JsonIgnore
    private String id;
    private String longUrl;
    private long createdTime;
    private long expiryTime;
}
