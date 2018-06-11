package org.avol.tinyservice.stepdefs;

import com.couchbase.client.java.Bucket;
import org.avol.tinyservice.TinyUrlApp;
import org.avol.tinyservice.url.api.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private Bucket bucket;

    private final TestRestTemplate testRestTemplate;

    BaseStepDef() {
        testRestTemplate = new TestRestTemplate();
    }

    ResponseEntity<String> health(String url) {
        return testRestTemplate.getForEntity(url, String.class);
    }

    ResponseEntity<String> post(String url, Object body, HttpHeaders httpHeaders) {
        HttpEntity<?> payload = new HttpEntity<>(body, httpHeaders);
        return testRestTemplate.postForEntity(url, payload, String.class);
    }

    ResponseEntity<String> get(String url, HttpHeaders httpHeaders) {
        HttpEntity<?> httpEntity = new HttpEntity<>(null, httpHeaders);
        return testRestTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
    }

    int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void flushBucket() {
        bucket.bucketManager().flush();
    }
}
