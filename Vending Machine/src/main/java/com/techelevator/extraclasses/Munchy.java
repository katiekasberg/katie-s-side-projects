package com.techelevator.extraclasses;

import java.math.BigDecimal;

public class Munchy extends Snackable{


    public Munchy(String itemSlot, String itemName, BigDecimal itemPrice, String itemType) {
        super(itemSlot, itemName, itemPrice, itemType);
    }

    @Override
    public String getMessage() {
        return "Munchy, Munchy, so Good!";
    }


}
