package org.avol.tinyservice;

import org.avol.tinyservice.url.api.model.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Durga, Padala on 11/06/18.
 */
public class TestContext {

    public static ResponseEntity<ApiResponse> responseEntity;

    public static ApiResponse apiResponse;

    public static Set<String> shortKeys = new HashSet<>();
}
