package com.example.proektdians.web;

import com.example.proektdians.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public String search(String search, Model model){
        if(search!= null && !search.isEmpty()){
            model.addAttribute("moneyObjects", searchService.findAllObjects(search));
        }
        return "objects";
    }

}
