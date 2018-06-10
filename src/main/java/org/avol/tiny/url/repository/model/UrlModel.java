package org.avol.tiny.url.repository.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by lovababu on 10/06/18.
 */
@Setter
@Getter
public class UrlModel {

    public String id;
    public String longUrl;
    public long createdTime;
    public long expiryTime;
}
