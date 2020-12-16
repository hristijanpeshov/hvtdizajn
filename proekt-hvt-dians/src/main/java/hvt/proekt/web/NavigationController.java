package hvt.proekt.web;

import hvt.proekt.service.MoneyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/navigate")
public class NavigationController {

    private final MoneyService moneyService;

    public NavigationController(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    @GetMapping
    public String getMapPage(){
        return "map";
    }

    @GetMapping("/objects")
    public String getAllObjects(@RequestParam(required = false) String object , Model model){
        if(object != null && !object.isEmpty()){
            model.addAttribute("moneyObjects", moneyService.listObjectByType(object));
        }
        else {
            model.addAttribute("moneyObjects", moneyService.listAll());
        }
        return "objects";
    }

}
