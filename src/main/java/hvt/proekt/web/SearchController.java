package hvt.proekt.web;

import hvt.proekt.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

//    @PostMapping
//    public String search(@RequestParam(required = false) String object, String search, Model model){
//        if(search!= null && !search.isEmpty()){
//            model.addAttribute("type", object);
//        }
//        return "objects";
//    }

}
