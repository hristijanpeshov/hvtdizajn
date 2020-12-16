package hvt.proekt.service;


import hvt.proekt.model.MoneyObject;

import java.util.List;
import java.util.Optional;

public interface MoneyService {

    List<MoneyObject> listAll();
    List<MoneyObject> listObjectByType(String type);

    Optional<MoneyObject> findObjectById(long id);
}
