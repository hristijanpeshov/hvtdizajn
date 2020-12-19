package hvt.proekt.model;

import hvt.proekt.model.enumeration.Type;
import lombok.Data;

@Data
public class WrapperMoneyObject {

    private MoneyObject moneyObject;
    private double distance;

    public WrapperMoneyObject(MoneyObject moneyObject) {
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

