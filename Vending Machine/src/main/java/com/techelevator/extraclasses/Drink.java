package com.techelevator.extraclasses;

import java.math.BigDecimal;


public class Drink extends Snackable {


    public Drink(String itemSlot, String itemName, BigDecimal itemPrice, String itemType) {
        super(itemSlot, itemName, itemPrice, itemType);
    }

    @Override
    public String getMessage() {
        return "Drinky, Drinky, Slurp Slurp!";
    }


}

