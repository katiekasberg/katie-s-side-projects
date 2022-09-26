package com.techelevator.extraclasses;

import com.techelevator.application.VendingMachine;

import java.math.BigDecimal;

public abstract class Money {

   private BigDecimal dollar = new BigDecimal(1.00);
   private BigDecimal quarter = new BigDecimal(0.25);
   private BigDecimal dime = new BigDecimal(0.10);
   private BigDecimal nickel = new BigDecimal(0.05);
   private BigDecimal penny = new BigDecimal(0.01);

    public BigDecimal getDollar() {
        return dollar;
    }

    public BigDecimal getQuarter() {
        return quarter;
    }

    public BigDecimal getDime() {
        return dime;
    }

    public BigDecimal getNickel() {
        return nickel;
    }

    public BigDecimal getPenny() {
        return penny;
    }
}
