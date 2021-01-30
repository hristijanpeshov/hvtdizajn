package com.hvt.dians.mainservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hvt.dians.mainservice.model.MoneyObject;
import com.hvt.dians.mainservice.model.MoneyObjectDecorator;
import com.hvt.dians.mainservice.model.enumeration.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class MoneyObjectCRUDController {

    @Autowired
    private RestTemplate restTemplate;


//    public List<MoneyObject> get(){
//        ResponseEntity<MoneyObject[]> responseEntity = restTemplate.getForEntity("http://MONEY-OBJECT-SERVICE/api/markers", MoneyObject[].class);
//        Object[] objects = responseEntity.getBody();
//
//        ObjectMapper mapper = new ObjectMapper();
//        return Arrays.stream(objects).map(o->mapper.convertValue(o,MoneyObject.class))
//                .collect(Collectors.toList());
//    }

    @GetMapping
    public String listAll(Model model)
    {

        ResponseEntity<MoneyObject[]> responseEntity = restTemplate.getForEntity("http://money-objects-service:9091/admin", MoneyObject[].class);
        Object[] objects = responseEntity.getBody();

        ObjectMapper mapper = new ObjectMapper();
        List<MoneyObject> objectList = Arrays.stream(objects).map(o->mapper.convertValue(o,MoneyObject.class))
                .collect(Collectors.toList());
        model.addAttribute("objects",objectList);
        return "adminObjects";
    }


    @GetMapping("/create")
    public String newObject()
    {
        return "createObject";
    }


    @PostMapping("/create")
    public String save(@RequestParam String name, @RequestParam Type type, @RequestParam double lon, @RequestParam double lat)
    {
        HttpEntity<MultiValueMap<String, String>> request = createRequestMap(name,type,lat,lon);
        //restTemplate.postForEntity("http://MONEY-OBJECT-SERVICE/admin/create",request,String.class);
        sendRequest("http://money-objects-service:9091/admin/create",request);
        return "redirect:/admin";
    }

    private HttpEntity<MultiValueMap<String, String>> createRequestMap(String name, Type type, double lat, double lon)
    {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("name", name);
        map.add("type",type.toString());
        map.add("lon",String.valueOf(lon));
        map.add("lat",String.valueOf(lat));
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        return request;
    }

    private void sendRequest(String url,HttpEntity<MultiValueMap<String, String>> request){

        restTemplate.postForEntity(url,request,String.class);

//        Object[] objects = responseEntity.getBody();
//
//        ObjectMapper mapper = new ObjectMapper();
//        return Arrays.stream(objects).map(o->mapper.convertValue(o,String.class))
//                .collect(Collectors.toList());
    }

//    private void sendRequest(String url, HttpEntity<MultiValueMap<String, Object>> request){
//
//        restTemplate.postForEntity("http://MONEY-OBJECT-SERVICE/admin/create",request,MoneyObjectDecorator[].class);
//    }
}