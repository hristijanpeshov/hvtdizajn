package com.hvt.dians.mainservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hvt.dians.mainservice.model.MoneyObject;
import com.hvt.dians.mainservice.model.MoneyObjectDecorator;
import com.hvt.dians.mainservice.model.util.Location;
import com.hvt.dians.mainservice.web.RequestHelper.RequestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/search")
public class SearchRestController {

    //TODO: handle na exceptions

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<MoneyObjectDecorator[]> getByDistance(@RequestParam String object, @RequestParam double lat, @RequestParam double lon, @RequestParam(required = false) String search){
        HttpEntity<MultiValueMap<String, Object>> request = RequestHelper.createRequestMap("object", object, "search", search, "lat", lat, "lon", lon);
        return restTemplate.postForEntity(RequestHelper.relativeUrl+"/api/search",request,MoneyObjectDecorator[].class);
    }

    @PostMapping("/topTen")
    public ResponseEntity<MoneyObjectDecorator[]> topTen(@RequestParam String object, @RequestParam double lat, @RequestParam double lon, @RequestParam(required = false) String search){
        HttpEntity<MultiValueMap<String, Object>> request = RequestHelper.createRequestMap("object", object, "search", search, "lat", lat, "lon", lon);
        return restTemplate.postForEntity(RequestHelper.relativeUrl+"/api/search/topTen",request,MoneyObjectDecorator[].class);
    }

    @PostMapping("/locationDenied")
    public ResponseEntity<MoneyObjectDecorator[]> fail(@RequestParam String object, @RequestParam(required = false) String search){
        HttpEntity<MultiValueMap<String, Object>> request = RequestHelper.createRequestMap("object", object, "search", search, "lat", 22.12, "lon", 44.23);
        return restTemplate.postForEntity(RequestHelper.relativeUrl+"/api/search/locationDenied",request,MoneyObjectDecorator[].class);
    }
}
