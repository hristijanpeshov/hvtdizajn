package com.hvt.dians.moneyobjectsservice.model.exceptions;

public class InvalidMoneyObjectId extends RuntimeException {
    public InvalidMoneyObjectId(long id) {
        super(String.format("Money Object with id: %d doesn't exist!",id));
    }
}
