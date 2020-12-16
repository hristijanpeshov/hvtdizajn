package hvt.proekt.web;

import hvt.proekt.model.MoneyObject;
import hvt.proekt.service.MoneyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/markers")
public class NavigateRestController {

    private final MoneyService moneyService;

    public NavigateRestController(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    @GetMapping
    public List<MoneyObject> findAll(){
        return this.moneyService.listAll();
    }
}
