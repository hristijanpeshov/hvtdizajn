package hvt.proekt.web;

import hvt.proekt.model.util.Location;
import hvt.proekt.service.MoneyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;

@Controller
@RequestMapping({"/navigate", "/"})
public class NavigationController {


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
