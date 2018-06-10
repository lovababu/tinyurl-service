package org.avol.tiny.datatables;

/**
 * @author Durga, Padala on 10/06/18.
 */
public class RequestData {
    private String url;
    private String customKey;
    private long expiryTimeInMs;

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

    public long getExpiryTimeInMs() {
        return expiryTimeInMs;
    }

    public void setExpiryTimeInMs(long expiryTimeInMs) {
        this.expiryTimeInMs = expiryTimeInMs;
    }
}
