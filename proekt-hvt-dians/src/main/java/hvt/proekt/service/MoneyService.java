package hvt.proekt.service;


import hvt.proekt.model.MoneyObject;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public interface MoneyService {

    List<MoneyObject> listAll() throws FileNotFoundException;
    List<MoneyObject> listObjectByType(String type) throws FileNotFoundException;

    Optional<MoneyObject> findObjectById(long id) throws FileNotFoundException;
}
