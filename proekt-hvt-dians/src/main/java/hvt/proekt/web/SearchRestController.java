package hvt.proekt.web;

import hvt.proekt.model.MoneyObject;
import hvt.proekt.model.WrapperMoneyObject;
import hvt.proekt.model.util.Location;
import hvt.proekt.service.MoneyService;
import hvt.proekt.service.SearchService;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    List<WrapperMoneyObject> getByDistance(@RequestParam String object, @RequestParam double lat, @RequestParam double lon, @RequestParam(required = false) String search){
        if(search == null)
            search = "";
        try {
            return searchService.findAllObjects(search, object, new Location(lat, lon));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @PostMapping("/topTen")
    List<WrapperMoneyObject> topTen(@RequestParam String object, @RequestParam double lat, @RequestParam double lon, @RequestParam(required = false) String search){
        try {
            return searchService.findNClosest(search, object, new Location(lat, lon), 10);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
