package org.avol.tinyservice.stepdefs;

import org.avol.tinyservice.TinyUrlApp;
import org.avol.tinyservice.url.api.model.ApiResponse;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

/**
 * Spring bootstrap server runner during test phase.
 *
 * @author Durga, Padala on 10/06/18.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(
        classes = TinyUrlApp.class
)
public class BaseStepDef {

    @LocalServerPort
    private int port;

    private final TestRestTemplate testRestTemplate;

    BaseStepDef() {
        testRestTemplate = new TestRestTemplate();
    }

    ResponseEntity<String> health(String url) {
        return testRestTemplate.getForEntity(url, String.class);
    }

    ResponseEntity<ApiResponse> post(String url, Object body, HttpHeaders httpHeaders) {
        HttpEntity<?> payload = new HttpEntity<>(body, httpHeaders);
        return testRestTemplate.postForEntity(url, payload, ApiResponse.class);
    }

    ResponseEntity<ApiResponse> get(String url, HttpHeaders httpHeaders) {
        HttpEntity<?> httpEntity = new HttpEntity<>(null, httpHeaders);
        return testRestTemplate.exchange(url, HttpMethod.GET, httpEntity, ApiResponse.class);
    }

    int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
