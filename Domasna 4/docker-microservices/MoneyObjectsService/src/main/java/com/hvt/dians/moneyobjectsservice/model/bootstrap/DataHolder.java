package com.hvt.dians.moneyobjectsservice.model.bootstrap;

import com.hvt.dians.moneyobjectsservice.model.MoneyObject;
import com.hvt.dians.moneyobjectsservice.model.enumeration.Type;
import lombok.Getter;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Getter
public class DataHolder {
    //samata klasa e singleton preku samiot spring framework

    private List<IFileDatabase> files;

    private List<MoneyObject> moneyObjects;

    public List<MoneyObject> getMoneyObjects(){
        return moneyObjects;
    }

    //ja popolnuva listata moneyObjects so objektite od bazata
    @PostConstruct
    public void init(){
        files = new ArrayList<>();
        moneyObjects = new ArrayList<>();

        File file = new File("/var/lib/docker/volumes/myapp/_data");

        System.out.println(Arrays.toString(file.listFiles()));

        files.add(new CSVFileImpl(Type.ATM, "/var/lib/docker/volumes/myapp/_data/atm.csv"));
        files.add(new CSVFileImpl(Type.BANK, "/var/lib/docker/volumes/myapp/_data/bank.csv"));
        files.add(new CSVFileImpl(Type.EXCHANGE, "/var/lib/docker/volumes/myapp/_data/exchange.csv"));

        listAllFromDatabase();
    }

    private void listAllFromDatabase() {
        files.forEach(file-> {
            try {
                moneyObjects.addAll(file.read());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    //zacuvuvanje na podatocite vo baza
    @PreDestroy
    public void preDestroy(){
        files.forEach(file -> {
            try {
                file.save(moneyObjects);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

}
