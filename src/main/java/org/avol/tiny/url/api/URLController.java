package org.avol.tiny.url.api;

import org.avol.tiny.url.api.model.URLApiResponse;
import org.avol.tiny.url.api.model.URLRequestModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Controller, rest request entry point.
 *
 * Created by lovababu on 10/06/18.
 */
@RestController
@RequestMapping(path = "/urls", produces = MediaType.APPLICATION_JSON_VALUE)
public class URLController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<URLApiResponse> shorten(@RequestBody URLRequestModel request) {
        URLApiResponse URLApiResponse = new URLApiResponse();
        URLApiResponse.setShortUrl("abc");
        URLApiResponse.setExpiryTime(LocalDateTime.now().plusDays(2));
        return new ResponseEntity<>(URLApiResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/{uniqueKey}")
    public void redirect(@PathVariable(value = "uniqueKey") String uniqueKey,
                                           HttpServletResponse response) throws IOException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.LOCATION,  "https://google.co.in");
        response.addHeader(HttpHeaders.LOCATION, "https://google.co.in");
        response.sendRedirect("https://google.co.in");
    }
}
