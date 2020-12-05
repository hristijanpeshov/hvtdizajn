package com.example.proektdians.service;

import com.example.proektdians.model.MoneyObject;

import java.util.List;
import java.util.Optional;

public interface MoneyService {

    List<MoneyObject> listAll();
    List<MoneyObject> listObjectByType(String type);

    Optional<MoneyObject> findObjectById(long id);
}
