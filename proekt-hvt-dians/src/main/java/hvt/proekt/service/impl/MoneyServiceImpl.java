package hvt.proekt.service.impl;

import hvt.proekt.model.MoneyObject;
import hvt.proekt.model.enumeration.Type;
import hvt.proekt.model.util.Location;
import hvt.proekt.repository.MoneyObjectRepository;
import hvt.proekt.service.MoneyService;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class MoneyServiceImpl implements MoneyService {

    private final MoneyObjectRepository moneyObjectRepository;


    public MoneyServiceImpl(MoneyObjectRepository moneyObjectRepository) {
        this.moneyObjectRepository = moneyObjectRepository;
    }

    //gi vrakja site moneyObjects od repository(od file)
    @Override
    public List<MoneyObject> listAll() {
        return moneyObjectRepository.findAll();
    }

    //go povikuva soodvetniot metod od repository so vlezniot id
    @Override
    public Optional<MoneyObject> findObjectById(long id) throws FileNotFoundException {
        return moneyObjectRepository.findObjectById(id);
    }

    //go povikuva soodvetniot metod od repository so vleznite parametri za moneyObject
    @Override
    public void save(String name, Type type, double lat, double lon) {
        MoneyObject newMoneyObject = new MoneyObject(type, new Location(lat, lon), name);
        moneyObjectRepository.saveNewObject(newMoneyObject);
    }
}
