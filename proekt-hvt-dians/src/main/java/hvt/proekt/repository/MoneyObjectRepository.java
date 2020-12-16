package hvt.proekt.repository;

import hvt.proekt.model.MoneyObject;
import hvt.proekt.model.bootstrap.DataHolder;
import hvt.proekt.model.enumeration.Type;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Repository
public class MoneyObjectRepository {

    public List<MoneyObject> findAll() {
        return new ArrayList<>(DataHolder.services);
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
