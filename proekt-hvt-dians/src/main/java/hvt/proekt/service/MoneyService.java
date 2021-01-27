package hvt.proekt.service;


import hvt.proekt.model.MoneyObject;
import hvt.proekt.model.enumeration.Type;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public interface MoneyService {

    List<MoneyObject> listAll() throws FileNotFoundException;
//    List<WrapperMoneyObject> listObjectByType(String type, Location current) throws FileNotFoundException;

    Optional<MoneyObject> findObjectById(long id) throws FileNotFoundException;
    void save(String name, Type type, double lat, double lon);
}
