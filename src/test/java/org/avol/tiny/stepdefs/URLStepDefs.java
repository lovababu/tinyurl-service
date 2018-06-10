package org.avol.tiny.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.avol.tiny.TinyUrlApp;
import org.avol.tiny.common.EndpointUrl;
import org.avol.tiny.datatables.RequestData;
import org.avol.tiny.url.api.model.URLApiResponse;
import org.avol.tiny.url.api.model.URLRequestModel;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Cucumber stef definitions.
 *
 * Created by lovababu on 10/06/18.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(
        classes = TinyUrlApp.class
)
public class URLStepDefs extends BaseStepDef{


    private ResponseEntity<URLApiResponse> responseEntity = null;

    @Given("^the service is up and running$")
    public void the_service_is_up_and_running() {

        ResponseEntity<String> healthResponse = health(EndpointUrl.HEALTH.getResourceURL(getPort()));
        assertTrue(healthResponse.getStatusCode().is2xxSuccessful());
    }

    @Given("^the below long url and invoke the service$")
    public void the_below_long_url_and_invoke_the_service(RequestData requestData)  {
            responseEntity = post(EndpointUrl.SHORTEN.getResourceURL(getPort()),
                    buildRequest(requestData.getUrl(), requestData.getExpiryTimeInMs()),
                    buildHeaders());
    }

    @Then("^it succeeded with http status code (\\d+)$")
    public void it_succeeded_with_http_status_code(int arg1)  {
        assertEquals(responseEntity.getStatusCode().value(), arg1);
    }

    @Then("^the shortened url is accessible$")
    public void the_shortened_url_is_accessible()  {
        responseEntity = get(responseEntity.getBody().getShortUrl(), buildHeaders());
        assertTrue(responseEntity.getStatusCode().is3xxRedirection());

    }

    @Then("^it failed with http status code (\\d+)$")
    public void it_failed_with_http_status_code(int arg1)  {
        assertEquals(responseEntity.getStatusCode().value(), arg1);
    }

    private Object buildRequest(String url, long expiryTime) {
        URLRequestModel urlRequestModel = new URLRequestModel();
        if (expiryTime > 0) {
            urlRequestModel.setExpiryTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(expiryTime), ZoneId.systemDefault()));
        }
        urlRequestModel.setOriginalUrl(url);
        return urlRequestModel;
    }

    private HttpHeaders buildHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
