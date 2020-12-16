package hvt.proekt.service;


import hvt.proekt.model.MoneyObject;

import java.util.List;

public interface SearchService {

    List<MoneyObject> findAllObjects(String name);

    List<MoneyObject> findNClosest(int n);


}
