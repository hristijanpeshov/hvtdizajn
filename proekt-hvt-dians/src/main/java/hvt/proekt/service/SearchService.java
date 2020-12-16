package hvt.proekt.service;


import hvt.proekt.model.MoneyObject;

import java.io.FileNotFoundException;
import java.util.List;

public interface SearchService {

    List<MoneyObject> findAllObjects(String name, String type) throws FileNotFoundException;

    List<MoneyObject> findNClosest(int n) throws FileNotFoundException;


}
