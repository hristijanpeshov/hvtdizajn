package hvt.proekt.web;

import hvt.proekt.model.MoneyObject;
import hvt.proekt.service.MoneyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        try {
            return this.moneyService.listAll();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<MoneyObject>();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoneyObject> findById(@PathVariable Long id){
        try {
            return this.moneyService
                    .findObjectById(id)
                    .map(moneyObject -> ResponseEntity
                            .ok()
                            .body(moneyObject))
                    .orElseGet(() -> ResponseEntity
                            .notFound()
                            .build());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
