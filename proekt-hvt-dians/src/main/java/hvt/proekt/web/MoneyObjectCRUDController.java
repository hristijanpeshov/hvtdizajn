package hvt.proekt.web;

import hvt.proekt.service.MoneyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;

@Controller
@RequestMapping("/admin")
public class MoneyObjectCRUDController {
    private final MoneyService moneyService;

    public MoneyObjectCRUDController(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    @GetMapping
    public String listAll(Model model)
    {
        try {
            model.addAttribute("objects",moneyService.listAll());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "adminObjects";
    }


    @GetMapping("/create")
    public String newObject()
    {
        return "createObject";
    }


    @PostMapping("/create")
    public String save(@RequestParam String name, @RequestParam hvt.proekt.model.enumeration.Type type, @RequestParam Double lon, @RequestParam Double lat)
    {
        moneyService.save(name, type, lat, lon);
        return "redirect:/admin";
    }
}
