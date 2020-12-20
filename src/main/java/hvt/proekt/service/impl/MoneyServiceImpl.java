package hvt.proekt.service.impl;

import hvt.proekt.model.MoneyObject;
import hvt.proekt.model.WrapperMoneyObject;
import hvt.proekt.model.enumeration.Type;
import hvt.proekt.model.util.Location;
import hvt.proekt.repository.MoneyObjectRepository;
import hvt.proekt.service.MoneyService;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MoneyServiceImpl implements MoneyService {

    private final MoneyObjectRepository repository;


    public MoneyServiceImpl(MoneyObjectRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<MoneyObject> listAll() throws FileNotFoundException {
        return repository.findAll();
    }

    public void save(String name,Type type,double lat,double lon)
    {
        repository.save(new MoneyObject(-1L,type,new Location(lat,lon),name));
    }

//    @Override
//    public List<WrapperMoneyObject> listObjectByType(String type, Location current) throws FileNotFoundException {
//        List<MoneyObject> objects;
//        if(type.equals(Type.BANK.toString().toLowerCase()))
//            objects = repository.findObjectsByType(Type.BANK);
//        else if(type.equals(Type.ATM.toString().toLowerCase())){
//            objects = repository.findObjectsByType(Type.ATM);
//        }
//        else objects = repository.findObjectsByType(Type.EXCHANGE);
//        List<WrapperMoneyObject> wrappers = new ArrayList<>();
//        objects.forEach(s-> {
//            WrapperMoneyObject tmp = new WrapperMoneyObject(s);
//            tmp.setDistance(calculateDistance(s.getCoordinates().getY(), s.getCoordinates().getX(), current.getY(), current.getX()));
//           wrappers.add(tmp);
//        });
//        Comparator<WrapperMoneyObject> comparator = Comparator.comparing(WrapperMoneyObject::getDistance);
//        return wrappers.stream().sorted(comparator).collect(Collectors.toList());
//    }
//
//    private int calculateDistance(double lat1, double lon1, double lat2, double lon2){
//        double r = 6371*Math.pow(10,3); // metres
//        double f1 = lat1 * Math.PI/180; // φ, λ in radians
//        double f2 = lat2 * Math.PI/180;
//        double distF = (lat2-lat1) * Math.PI/180;
//        double distLambda = (lon2-lon1) * Math.PI/180;
//
//        double a = Math.sin(distF/2) * Math.sin(distF/2) +
//                Math.cos(f1) * Math.cos(f2) *
//                        Math.sin(distLambda/2) * Math.sin(distLambda/2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
//
//        double dist =  r * c;
//        return (int) Math.ceil(dist);
//    }

    @Override
    public Optional<MoneyObject> findObjectById(long id) throws FileNotFoundException {
        return repository.findObjectById(id);
    }
}
