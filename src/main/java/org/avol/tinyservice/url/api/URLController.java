package org.avol.tinyservice.url.api;

import org.avol.tinyservice.common.exception.ServiceException;
import org.avol.tinyservice.url.api.model.ApiResponse;
import org.avol.tinyservice.url.api.model.ApiRequest;
import org.avol.tinyservice.url.repository.entity.UrlModel;
import org.avol.tinyservice.url.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Controller, rest request entry point.
 *
 * Created by lovababu on 10/06/18.
 */
@RestController
@RequestMapping(path = "/urls", produces = MediaType.APPLICATION_JSON_VALUE)
public class URLController {

    @Autowired
    private URLService urlService;

    @Value("${urlapi.redirectBaseURL}")
    private String redirectBaseURL;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> shorten(@RequestBody ApiRequest request) throws ServiceException {
        UrlModel urlModel = urlService.shorten(request);
        return new ResponseEntity<>(buildResponse(urlModel), HttpStatus.OK);
    }

    @GetMapping(path = "/{customKey}")
    public void redirect(@PathVariable(value = "customKey") String uniqueKey,
                                           HttpServletResponse response) throws ServiceException, IOException {
        String redirectURL = urlService.get(uniqueKey);
        response.sendRedirect(redirectURL);
    }


    private ApiResponse buildResponse(UrlModel urlModel) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setExpiryTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(urlModel.getExpiryTime()), ZoneId.systemDefault()));
        apiResponse.setShortUrl(String.format(redirectBaseURL, urlModel.getId()));
        return apiResponse;
    }
}
