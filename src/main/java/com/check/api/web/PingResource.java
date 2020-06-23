package com.check.api.web;

import java.io.IOException;

import com.check.api.request.CheckApiRequest;
import com.check.api.response.GenericResponse;
import com.check.api.service.ApiService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PingResource
 */
@RestController
@RequestMapping("/api")
public class PingResource {

    private final ApiService apiService;

    PingResource(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/checkApi")
    public ResponseEntity<GenericResponse> ping(@RequestBody CheckApiRequest apiRequest) {
        GenericResponse response = apiService.checkApi(apiRequest);
        return ResponseEntity.status(response.getCode()).body(response);

    }
    
}