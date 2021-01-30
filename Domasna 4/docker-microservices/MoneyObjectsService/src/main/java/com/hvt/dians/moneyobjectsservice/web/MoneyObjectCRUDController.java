package com.hvt.dians.moneyobjectsservice.web;

import com.hvt.dians.moneyobjectsservice.model.MoneyObject;
import com.hvt.dians.moneyobjectsservice.model.enumeration.Type;
import com.hvt.dians.moneyobjectsservice.service.MoneyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class MoneyObjectCRUDController {
    private final MoneyService moneyService;

    public MoneyObjectCRUDController(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    @GetMapping
    public List<MoneyObject> listAll(){
        try {
            return moneyService.listAll();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @PostMapping("/create")
    public void save(@RequestParam String name, @RequestParam Type type, @RequestParam Double lon, @RequestParam Double lat)
    {
        moneyService.save(name, type, lat, lon);
    }
}
