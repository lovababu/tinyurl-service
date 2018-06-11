package org.avol.tinyservice.datatables;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Durga, Padala on 10/06/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestData {
    private String url;
    private String customKey;
    private String expiryTimeInMs;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCustomKey() {
        return customKey;
    }

    public void setCustomKey(String customKey) {
        this.customKey = customKey;
    }

    public String getExpiryTimeInMs() {
        return expiryTimeInMs;
    }

    public void setExpiryTimeInMs(String expiryTimeInMs) {
        this.expiryTimeInMs = expiryTimeInMs;
    }
}
