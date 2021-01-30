package com.hvt.dians.mainservice.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MoneyObjectList {
    private List<MoneyObject> moneyObjects;

    public MoneyObjectList() {
        this.moneyObjects = new ArrayList<>();
    }

    public List<MoneyObject> getMoneyObjects() {
        return moneyObjects;
    }

    public void setMoneyObjects(List<MoneyObject> moneyObjects) {
        this.moneyObjects = moneyObjects;
    }
}
