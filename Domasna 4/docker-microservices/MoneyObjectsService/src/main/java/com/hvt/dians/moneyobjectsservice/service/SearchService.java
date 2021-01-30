package com.hvt.dians.moneyobjectsservice.service;


import com.hvt.dians.moneyobjectsservice.model.MoneyObjectDecorator;
import com.hvt.dians.moneyobjectsservice.model.util.Location;

import java.io.FileNotFoundException;
import java.util.List;

public interface SearchService {

    List<MoneyObjectDecorator> findAllObjects(String name, String type, Location current) throws FileNotFoundException;

    List<MoneyObjectDecorator> findNClosest(String name, String type, Location current, int n) throws FileNotFoundException;




}
