package com.hvt.dians.moneyobjectsservice.service;


import com.hvt.dians.moneyobjectsservice.model.MoneyObject;
import com.hvt.dians.moneyobjectsservice.model.enumeration.Type;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public interface MoneyService {

    List<MoneyObject> listAll() throws FileNotFoundException;
    Optional<MoneyObject> findObjectById(long id) throws FileNotFoundException;
    void save(String name, Type type, double lat, double lon);
    void edit(long id,String name,Type type,double lat,double lon);
    MoneyObject delete(long id);
}
