package hvt.proekt.service.impl;


import hvt.proekt.model.MoneyObject;
import hvt.proekt.model.WrapperMoneyObject;
import hvt.proekt.model.enumeration.Type;
import hvt.proekt.model.util.Location;
import hvt.proekt.repository.MoneyObjectRepository;
import hvt.proekt.service.MoneyService;
import hvt.proekt.service.SearchService;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    private final MoneyObjectRepository repository;
    private final MoneyService moneyService;


    public SearchServiceImpl(MoneyObjectRepository repository, MoneyService moneyService) {
        this.repository = repository;
        this.moneyService = moneyService;
    }

    @Override
    public List<WrapperMoneyObject> findAllObjects(String name, String type, Location current) throws FileNotFoundException {
        //TODO: koristi go findObjectByName a ne type, i streamot za filtrer da bide vo repository
        List<MoneyObject> objects = repository.findObjectByNameAndType(name, type);
        List<WrapperMoneyObject> wrappers = new ArrayList<>();
        objects.forEach(s-> {
            WrapperMoneyObject tmp = new WrapperMoneyObject(s);
            tmp.setDistance(calculateDistance(s.getCoordinates().getY(), s.getCoordinates().getX(), current.getY(), current.getX()));
            wrappers.add(tmp);
        });
        Comparator<WrapperMoneyObject> comparator = Comparator.comparing(WrapperMoneyObject::getDistance);
        return wrappers.stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public List<WrapperMoneyObject> findNClosest(String name, String type, Location current, int n) throws FileNotFoundException {
        return findAllObjects(name, type, current).stream().limit(n).collect(Collectors.toList());
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2){
        double r = 6371*Math.pow(10,3); // metres
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
