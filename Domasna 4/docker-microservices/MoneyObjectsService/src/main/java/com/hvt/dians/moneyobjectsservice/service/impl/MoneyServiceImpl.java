package com.hvt.dians.moneyobjectsservice.service.impl;

import com.hvt.dians.moneyobjectsservice.model.exceptions.InvalidMoneyObjectId;
import com.hvt.dians.moneyobjectsservice.repository.MoneyObjectRepository;
import com.hvt.dians.moneyobjectsservice.service.MoneyService;
import com.hvt.dians.moneyobjectsservice.model.MoneyObject;
import com.hvt.dians.moneyobjectsservice.model.enumeration.Type;
import com.hvt.dians.moneyobjectsservice.model.util.Location;
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

    @Override
    public void edit(long id, String name, Type type, double lat, double lon) {
        MoneyObject moneyObject = this.delete(id);
        moneyObject.setName(name);
        moneyObject.setType(type);
        moneyObject.setCoordinates(new Location(lat,lon));
        moneyObjectRepository.edit(moneyObject);
    }

    @Override
    public MoneyObject delete(long id) {
        MoneyObject moneyObject = moneyObjectRepository.findObjectById(id).orElseThrow(()->new InvalidMoneyObjectId(id));
        moneyObjectRepository.remove(moneyObject);
        return moneyObject;
    }
}
