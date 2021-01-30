package com.hvt.dians.moneyobjectsservice.service.impl;


import com.hvt.dians.moneyobjectsservice.repository.MoneyObjectRepository;
import com.hvt.dians.moneyobjectsservice.service.SearchService;
import com.hvt.dians.moneyobjectsservice.model.MoneyObject;
import com.hvt.dians.moneyobjectsservice.model.MoneyObjectDecorator;
import com.hvt.dians.moneyobjectsservice.model.util.Location;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    private final MoneyObjectRepository repository;


    public SearchServiceImpl(MoneyObjectRepository repository) {
        this.repository = repository;
    }

    // gi naogja site objekti i ja presmetuva oddalecenosta na objektite do momentalnata lokacija na korisnikot
    //vlezni argumenti: ime na objekt, tip na objekt, lokacija na korisnikot
    //imeto i tipot na objektot moze da se prazni stringovi
    @Override
    public List<MoneyObjectDecorator> findAllObjects(String name, String type, Location current) throws FileNotFoundException {

        List<MoneyObject> objects = repository.findObjectByNameAndType(name, type);
        List<MoneyObjectDecorator> decorators = new ArrayList<>();

        objects.forEach(s-> {
            decorators.add(wrapObject(s, current));
        });

        Comparator<MoneyObjectDecorator> comparator = Comparator.comparing(MoneyObjectDecorator::getDistance);
        return decorators.stream().sorted(comparator).collect(Collectors.toList());
    }

    //go obvitkuva objektot so koristenje na klasata MoneyObjectDecorator
    //vlezni argumenti: MoneyObject, Location na korisnikot
    //izlezen argument: MoneyObjectDecorator vo koj e presmetano rastojanieto do korisnikot
    private MoneyObjectDecorator wrapObject(MoneyObject moneyObject, Location current){
        MoneyObjectDecorator wrapperMoneyObject = new MoneyObjectDecorator(moneyObject);
        wrapperMoneyObject.setDistance(calculateDistance(moneyObject.getCoordinates().getY(), moneyObject.getCoordinates().getX(), current.getY(), current.getX()));
        return wrapperMoneyObject;
    }

    //slicen na prethodniot samo sto ima ogranicuvanje na brojot na objekti koi ke gi vrati
    @Override
    public List<MoneyObjectDecorator> findNClosest(String name, String type, Location current, int n) throws FileNotFoundException {
        return findAllObjects(name, type, current).stream().limit(n).collect(Collectors.toList());
    }

    //algoritam za presmetuvanje na rastojanie pomegju dve koordinati
    //vlezni argumenti: koordinatite na korisnikot i objektot
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2){
        double r = 6371 * Math.pow(10,3); // metres
        double f1 = lat1 * Math.PI/180; // φ, λ in radians
        double f2 = lat2 * Math.PI/180;
        double distF = (lat2-lat1) * Math.PI/180;
        double distLambda = (lon2-lon1) * Math.PI/180;

        double a = Math.sin(distF/2) * Math.sin(distF/2) +
                        Math.cos(f1) * Math.cos(f2) *
                                Math.sin(distLambda/2) * Math.sin(distLambda/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return (int) Math.ceil(r * c);
    }
}
