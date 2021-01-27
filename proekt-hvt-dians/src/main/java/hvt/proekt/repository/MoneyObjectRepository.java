package hvt.proekt.repository;

import hvt.proekt.model.MoneyObject;
import hvt.proekt.model.bootstrap.DataHolder;
import hvt.proekt.model.enumeration.Type;
import hvt.proekt.model.util.Location;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MoneyObjectRepository {

    private final DataHolder dataHolder;

    public MoneyObjectRepository(DataHolder dataHolder) {
        this.dataHolder = dataHolder;
    }

    public List<MoneyObject> findAll(){
        return dataHolder.getMoneyObjects();
    }

    //filtriranje po tip
    //vlezni argumenti: tip
    //izlezni argumenti: filtirirani moneyObjects
    public List<MoneyObject> findObjectsByType(Type type) {
        return findAll().stream().filter(s-> s.getType().equals(type)).collect(Collectors.toList());
    }

    //filtriranje po id
    //vlezni argumenti: id na moneyObject
    //izlezni argumenti: Optional od moneyObject(eden ili null)
    public Optional<MoneyObject> findObjectById(Long id) {
        return findAll().stream().filter(o -> o.getId().equals(id)).findFirst();
    }

    //filtriranje po ime i tip
    //vlezni argumenti: ime, tip
    //izlezni argumenti: filtirirani moneyObjects
    public List<MoneyObject> findObjectByNameAndType(String name, String type) {
        return findAll().stream().filter(s-> s.getName().toLowerCase()
                .contains(name.toLowerCase()) && s.getType().toString().equals(type.toUpperCase()))
                .collect(Collectors.toList());
    }

    //generiranje na id na nov moneyObject pri zapisuvanje vo file
    //go naogja maksimalniot id na dosegasnite objekti i go zgolemuva za 1
    private long generateNextId(){
        return dataHolder.getMoneyObjects().stream().mapToLong(MoneyObject::getId).max().orElse(0) + 1;
    }

    //zacuvuvanje vo file
    //vlezni argumenti: moneyObject
    public void saveNewObject(MoneyObject newObject){
        newObject.setId(generateNextId());
        dataHolder.getMoneyObjects().add(newObject);
    }
}
