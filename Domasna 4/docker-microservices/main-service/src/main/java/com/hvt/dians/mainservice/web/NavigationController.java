package com.hvt.dians.mainservice.web;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;

@Controller
@RequestMapping({"/navigate", "/"})
public class NavigationController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String getMapPage(){
        return "map";
    }

    @GetMapping("/objects")
    public String getAllObjects(@RequestParam(required = false) String object , Model model){
        if(object != null && !object.isEmpty()){
            model.addAttribute("type", object);
        }
        return "objects";
    }
}
