package com.hvt.dians.mainservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hvt.dians.mainservice.model.MoneyObject;
import com.hvt.dians.mainservice.model.MoneyObjectDecorator;
import com.hvt.dians.mainservice.model.enumeration.Type;
import com.hvt.dians.mainservice.web.RequestHelper.RequestHelper;
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

    @GetMapping
    public String listAll(Model model)
    {
        ResponseEntity<MoneyObject[]> responseEntity = restTemplate.getForEntity(RequestHelper.relativeUrl+"/admin", MoneyObject[].class);
        MoneyObject[] objects = responseEntity.getBody();
        List<MoneyObject> list = Arrays.asList(objects);
        model.addAttribute("objects",list);
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
        HttpEntity<MultiValueMap<String, Object>> request = RequestHelper.createRequestMap("name",name,"type",type.toString(),"lat",lat,"lon",lon);
        restTemplate.postForEntity(RequestHelper.relativeUrl+"/admin/create",request,void.class);
        return "redirect:/admin";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model)
    {
        MoneyObject moneyObject = restTemplate.getForObject(RequestHelper.relativeUrl+"/api/markers/"+id,MoneyObject.class);
        model.addAttribute("moneyObject",moneyObject);
        return "createObject";
    }
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable long id,@RequestParam String name, @RequestParam Type type,double lon,double lat )
    {
        HttpEntity<MultiValueMap<String, Object>> request = RequestHelper.createRequestMap("id",id,"name",name,"type",type.toString(),"lat",lat,"lon",lon);
        restTemplate.postForEntity(RequestHelper.relativeUrl+"/admin/edit/"+id,request,void.class);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id)
    {
        restTemplate.delete(RequestHelper.relativeUrl+"/admin/delete/"+id);
        return "redirect:/admin";
    }
}