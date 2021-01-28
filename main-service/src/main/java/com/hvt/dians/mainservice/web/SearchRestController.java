package com.hvt.dians.mainservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hvt.dians.mainservice.model.MoneyObject;
import com.hvt.dians.mainservice.model.MoneyObjectDecorator;
import com.hvt.dians.mainservice.model.util.Location;
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
    public List<MoneyObjectDecorator> getByDistance(@RequestParam String object, @RequestParam double lat, @RequestParam double lon, @RequestParam(required = false) String search){

        HttpEntity<MultiValueMap<String, Object>> request = createRequestMap(object, search, lat, lon);
        return sendRequest("http://MONEY-OBJECT-SERVICE/api/search",request);
    }

    @PostMapping("/topTen")
    public List<MoneyObjectDecorator> topTen(@RequestParam String object, @RequestParam double lat, @RequestParam double lon, @RequestParam(required = false) String search){
        HttpEntity<MultiValueMap<String, Object>> request = createRequestMap(object, search, lat, lon);
        return sendRequest("http://MONEY-OBJECT-SERVICE/api/search/topTen",request);
    }

    @PostMapping("/locationDenied")
    public List<MoneyObjectDecorator> fail(@RequestParam String object, @RequestParam(required = false) String search){
        HttpEntity<MultiValueMap<String, Object>> request = createRequestMap(object, search, 22.12, 44.23);
        return sendRequest("http://MONEY-OBJECT-SERVICE/api/search/locationDenied",request);
    }

    private HttpEntity<MultiValueMap<String, Object>> createRequestMap(String object,String search,double lat,double lon)
    {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("object", object);
        map.add("lat",lat);
        map.add("lon",lon);
        map.add("search",search);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
        return request;
    }

    private List<MoneyObjectDecorator> sendRequest(String url,HttpEntity<MultiValueMap<String, Object>> request){

        ResponseEntity<MoneyObjectDecorator[]> responseEntity = restTemplate.postForEntity(url,request,MoneyObjectDecorator[].class);

        Object[] objects = responseEntity.getBody();

        ObjectMapper mapper = new ObjectMapper();
        return Arrays.stream(objects).map(o->mapper.convertValue(o,MoneyObjectDecorator.class))
                .collect(Collectors.toList());
    }
}
