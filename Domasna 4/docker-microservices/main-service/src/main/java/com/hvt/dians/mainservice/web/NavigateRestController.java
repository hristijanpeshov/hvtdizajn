package com.hvt.dians.mainservice.web;

import com.hvt.dians.mainservice.model.MoneyObject;
import com.hvt.dians.mainservice.web.RequestHelper.RequestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/markers")
public class NavigateRestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<MoneyObject[]> findAll(HttpServletResponse response){
        return restTemplate.getForEntity(RequestHelper.relativeUrl+"/api/markers", MoneyObject[].class);
    }

    @GetMapping("/{id}")
    public MoneyObject findById(@PathVariable Long id){
        return restTemplate.getForObject(RequestHelper.relativeUrl+"/api/markers/"+id,MoneyObject.class);
    }
}