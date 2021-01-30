package com.hvt.dians.moneyobjectsservice.model.bootstrap;

import com.hvt.dians.moneyobjectsservice.model.MoneyObject;

import java.io.FileNotFoundException;
import java.util.List;

public interface IFileDatabase {

    List<MoneyObject> read() throws FileNotFoundException;

    void save(List<MoneyObject> objects) throws FileNotFoundException;

}
