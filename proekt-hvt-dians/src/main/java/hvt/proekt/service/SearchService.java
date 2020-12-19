package hvt.proekt.service;


import hvt.proekt.model.MoneyObject;
import hvt.proekt.model.WrapperMoneyObject;
import hvt.proekt.model.util.Location;

import java.io.FileNotFoundException;
import java.util.List;

public interface SearchService {

    List<WrapperMoneyObject> findAllObjects(String name, String type, Location current) throws FileNotFoundException;

    List<WrapperMoneyObject> findNClosest(String name, String type, Location current, int n) throws FileNotFoundException;




}
