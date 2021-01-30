package com.hvt.dians.moneyobjectsservice.service;


import com.hvt.dians.moneyobjectsservice.model.MoneyObject;
import com.hvt.dians.moneyobjectsservice.model.enumeration.Type;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public interface MoneyService {

    List<MoneyObject> listAll() throws FileNotFoundException;
//    List<WrapperMoneyObject> listObjectByType(String type, Location current) throws FileNotFoundException;

    Optional<MoneyObject> findObjectById(long id) throws FileNotFoundException;
    void save(String name, Type type, double lat, double lon);
}
