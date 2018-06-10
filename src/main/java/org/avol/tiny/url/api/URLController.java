package org.avol.tiny.url.api;

import org.avol.tiny.url.api.model.ResponseBody;
import org.avol.tiny.url.api.model.URLModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;
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
    public ResponseEntity<ResponseBody> shorten(@RequestBody URLModel request) {
        ResponseBody responseBody = ResponseBody.builder()
                .shortUrl("abc")
                .expiryTime(LocalDateTime.now().plusDays(2))
                .build();
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
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
