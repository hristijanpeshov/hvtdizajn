package com.hvt.dians.moneyobjectsservice.model.bootstrap;

import com.hvt.dians.moneyobjectsservice.model.MoneyObject;
import com.hvt.dians.moneyobjectsservice.model.enumeration.Type;
import lombok.Getter;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

        files.add(new CSVFileImpl(Type.ATM, "src/main/java/com/hvt/dians/moneyobjectsservice/model/bootstrap/db/atm.csv"));
        files.add(new CSVFileImpl(Type.BANK, "src/main/java/com/hvt/dians/moneyobjectsservice/model/bootstrap/db/bank.csv"));
        files.add(new CSVFileImpl(Type.EXCHANGE, "src/main/java/com/hvt/dians/moneyobjectsservice/model/bootstrap/db/exchange.csv"));

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
