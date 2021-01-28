package com.hvt.dians.mainservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MoneyObjectDecorator {

    private MoneyObject moneyObject;
    private double distance;

    public MoneyObjectDecorator(MoneyObject moneyObject) {
        this.moneyObject = moneyObject;
    }

    public MoneyObject getMoneyObject() {
        return moneyObject;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}