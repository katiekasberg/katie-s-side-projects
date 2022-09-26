package com.techelevator.extraclasses;

import java.math.BigDecimal;

public class Candy extends Snackable{


    public Candy(String itemSlot, String itemName, BigDecimal itemPrice, String itemType) {
        super(itemSlot, itemName, itemPrice, itemType);
    }

    @Override
    public String getMessage() {
        return "Sugar, Sugar, so Sweet!";
    }


}
