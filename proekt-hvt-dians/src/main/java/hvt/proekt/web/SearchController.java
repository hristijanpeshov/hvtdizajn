package hvt.proekt.web;

import hvt.proekt.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
