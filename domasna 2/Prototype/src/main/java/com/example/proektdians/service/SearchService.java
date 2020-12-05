package com.example.proektdians.service;

import com.example.proektdians.model.MoneyObject;

import java.util.List;

public interface SearchService {

    List<MoneyObject> findAllObjects(String name);

    List<MoneyObject> findNClosest(int n);


}
