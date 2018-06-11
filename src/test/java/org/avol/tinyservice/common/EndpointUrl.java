package org.avol.tinyservice.common;

/**
 * @author Durga, Padala on 10/06/18.
 */
public enum EndpointUrl {

    HEALTH("/manage/health"),
    SHORTEN("/urls"),
    REDIRECT("/urls/%s");

    private final String BASE_URL = "http://localhost:%d/avol";

    private final String relativePath;

    EndpointUrl(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getResourceURL(int port, String ... pathVars) {
        return String.format(BASE_URL + this.relativePath, port, pathVars);
    }
}
