package com.techelevator.extraclasses;

import java.math.BigDecimal;

public class Gum extends Snackable {


    public Gum(String itemSlot, String itemName, BigDecimal itemPrice, String itemType) {
        super(itemSlot, itemName, itemPrice, itemType);
    }

    @Override
    public String getMessage() {
        return "Chewy, Chewy, Lots O Bubbles!";
    }


}
