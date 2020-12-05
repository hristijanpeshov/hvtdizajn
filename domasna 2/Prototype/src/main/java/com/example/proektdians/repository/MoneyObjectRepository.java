package com.example.proektdians.repository;

import com.example.proektdians.model.MoneyObject;
import com.example.proektdians.model.bootstrap.DataHolder;
import org.springframework.stereotype.Repository;
import com.example.proektdians.model.enumeration.Type;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MoneyObjectRepository {

    public List<MoneyObject> findAll(){
        return DataHolder.services;
    }

    public List<MoneyObject> findObjectsByType(Type type){
        return DataHolder.services.stream().filter(s-> s.getType().equals(type)).collect(Collectors.toList());
    }

    public Optional<MoneyObject> findObjectById(Long id){
        return DataHolder.services.stream().filter(o -> o.getId().equals(id)).findFirst();
    }

    public List<MoneyObject> findObjectByName(String name){
        return DataHolder.services.stream().filter(s-> s.getName().contains(name)).collect(Collectors.toList());
    }

    public List<MoneyObject> findObjectByCloseness(int n){
        return DataHolder.services;
    }

}
