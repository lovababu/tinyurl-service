package org.avol.tinyservice.stepdefs;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.math.NumberUtils;
import org.avol.tinyservice.TestContext;
import org.avol.tinyservice.TinyUrlApp;
import org.avol.tinyservice.common.EndpointUrl;
import org.avol.tinyservice.datatables.RequestData;
import org.avol.tinyservice.url.api.model.ApiRequest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

    @Before
    public void init() {
        TestContext.apiResponse = null;
        TestContext.responseEntity = null;
        TestContext.shortKeys.clear();
    }

    @Given("^the service is up and running$")
    public void the_service_is_up_and_running() {

        ResponseEntity<String> healthResponse = health(EndpointUrl.HEALTH.getResourceURL(getPort()));
        assertTrue(healthResponse.getStatusCode().is2xxSuccessful());
    }

    @Given("^the below long url and invoke the service$")
    public void the_below_long_url_and_invoke_the_service(List<RequestData> requestData)  {
        if (!CollectionUtils.isEmpty(requestData)) {
            TestContext.responseEntity = post(EndpointUrl.SHORTEN.getResourceURL(getPort()),
                    buildRequest(requestData.get(0).getUrl(), requestData.get(0).getExpiryTimeInMs(),
                            requestData.get(0).getCustomKey()),
                    buildHeaders());
            TestContext.shortKeys.add(TestContext.responseEntity.getBody().getShortUrl());
            TestContext.apiResponse = TestContext.responseEntity.getBody();
        }
    }

    @Then("^it succeeded with http status code (\\d+)$")
    public void it_succeeded_with_http_status_code(int arg1)  {
        assertEquals(arg1, TestContext.responseEntity.getStatusCode().value());
    }

    @Then("^the shortened url is accessible$")
    public void the_shortened_url_is_accessible()  {
        TestContext.responseEntity = get(TestContext.responseEntity.getBody().getShortUrl().replace("8080", String.valueOf(getPort())),
                buildHeaders());
        assertTrue(TestContext.responseEntity.getStatusCode().is3xxRedirection());

    }

    @Then("^it failed with http status code (\\d+)$")
    public void it_failed_with_http_status_code(int arg1)  {
        assertEquals(TestContext.responseEntity.getStatusCode().value(), arg1);
    }

    @Then("^the shortened should be different from earlier and keys size should be (\\d+)$")
    public void the_shortened_should_be_different_from_earlier(int size) {
        assertTrue(TestContext.shortKeys.size() == size);
    }

    private Object buildRequest(String url, String expiryTime, String customKey) {
        ApiRequest apiRequest = new ApiRequest();
        if (NumberUtils.isParsable(expiryTime) && NumberUtils.createLong(expiryTime) > 0) {
            apiRequest.setExpiryTime(NumberUtils.createLong(expiryTime));
        }
        apiRequest.setOriginalUrl(url);
        apiRequest.setCustomKey(customKey);
        return apiRequest;
    }

    private HttpHeaders buildHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
