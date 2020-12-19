package hvt.proekt.web;

import hvt.proekt.model.MoneyObject;
import hvt.proekt.service.MoneyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        //TODO: Da se dodade handlanje na exception so hasError
        try {
            return this.moneyService.listAll();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
