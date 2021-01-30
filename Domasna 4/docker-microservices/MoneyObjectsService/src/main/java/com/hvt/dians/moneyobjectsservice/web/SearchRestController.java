package com.hvt.dians.moneyobjectsservice.web;

import com.hvt.dians.moneyobjectsservice.model.MoneyObjectDecorator;
import com.hvt.dians.moneyobjectsservice.model.util.Location;
import com.hvt.dians.moneyobjectsservice.service.MoneyService;
import com.hvt.dians.moneyobjectsservice.service.SearchService;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchRestController {

    private final MoneyService moneyService;
    private final SearchService searchService;

    public SearchRestController(MoneyService moneyService, SearchService searchService) {
        this.moneyService = moneyService;
        this.searchService = searchService;
    }

    //TODO: handle na exceptions

    @PostMapping
    List<MoneyObjectDecorator> getByDistance(@RequestParam String object, @RequestParam double lat, @RequestParam double lon, @RequestParam(required = false) String search){
        if(search == null)
            search = "";
        try {
            return searchService.findAllObjects(search, object, new Location(lat, lon));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    @PostMapping("/topTen")
    List<MoneyObjectDecorator> topTen(@RequestParam String object, @RequestParam double lat, @RequestParam double lon, @RequestParam(required = false) String search){
        try {
            return searchService.findNClosest(search, object, new Location(lat, lon), 10);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/locationDenied")
    List<MoneyObjectDecorator> fail(@RequestParam String object, @RequestParam(required = false) String search){
        try {
            return searchService.findAllObjects(search, object, new Location(22.12, 44.23));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
