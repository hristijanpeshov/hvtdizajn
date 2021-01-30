package com.hvt.dians.mainservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hvt.dians.mainservice.model.MoneyObject;
import com.hvt.dians.mainservice.model.MoneyObjectList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/markers")
public class NavigateRestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<MoneyObject> findAll(HttpServletResponse response){
        ResponseEntity<MoneyObject[]> responseEntity = restTemplate.getForEntity("http://money-objects-service:9091/api/markers", MoneyObject[].class);
        Object[] objects = responseEntity.getBody();

        ObjectMapper mapper = new ObjectMapper();
        return Arrays.stream(objects).map(o->mapper.convertValue(o,MoneyObject.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MoneyObject findById(@PathVariable Long id){
        return restTemplate.getForObject("http://money-objects-service:9091/api/markers/"+id,MoneyObject.class);
    }
}