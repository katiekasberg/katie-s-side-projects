package com.techelevator.application;

import com.techelevator.extraclasses.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VendingMachine extends Money{

    public static List<Snackable> snackList = new ArrayList<>();
    private BigDecimal currentMoney = new BigDecimal(0);
    private Logger logger = new Logger("Audit.txt");


    public List<Snackable> getSnackList() {
        return snackList;
    }

    public void run() {
        UserOutput userOutput = new UserOutput();
        getItems();

        while(true) {
            UserOutput.displayHomeScreen();
            getCurrentMoney(currentMoney);
            String choice = UserInput.getHomeScreenOption();
            logger.write(logger.getCurrentTime() + " Home Screen " + choice);
            if(choice.equals("display")) {
                UserOutput.displayDisplayScreen();
                System.out.println();
                UserOutput.displayItems(snackList);
            }
            else if(choice.equals("purchase")) {
                while(true) {
                    UserOutput.displayPurchaseScreen();
                    getCurrentMoney(currentMoney);
                    String purchaseChoice = UserInput.getPurchaseScreenOption();
                       if (purchaseChoice.equals("feed")) {
                        UserOutput.displayFeedMoneyScreen();
                        addMoney();
                        getCurrentMoney(currentMoney);
                    } else if (purchaseChoice.equals("select")) {
                        UserOutput.getSelection();
                        UserOutput.displayItems(snackList);
                        getCurrentMoney(currentMoney);
                        getSelectionChoice();

                    }
                    else if (purchaseChoice.equals("finish")){
                       getChange();
                       currentMoney = BigDecimal.valueOf(0.00);
                        break;
                    }

                }
            }
            else if(choice.equals("exit")) {
                              break;
            }
        }
    }

    public void getItems() {
        try (Scanner scanner = new Scanner(new File("vending.csv"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] itemArray = line.split(",");
                String slot = itemArray[0];
                String name = itemArray[1];
                BigDecimal price = new BigDecimal(itemArray[2]);
                String type = itemArray[3];

                if(type.equals("Munchy")){
                    snackList.add(new Munchy(slot, name, price, type));
                }
                else if(type.equals("Drink")){
                    snackList.add(new Drink(slot, name, price, type));
                }
                else if(type.equals("Gum")){
                    snackList.add(new Gum(slot, name, price, type));
                }
                else{
                    snackList.add(new Candy(slot, name, price, type));
                }

                }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public void getSelectionChoice() {
        String option = UserInput.getOption();
        boolean isValidSelection = false;
        for (Snackable list : snackList) {
            if (option.equals(list.getItemSlot())) {
                isValidSelection = true;
                currentMoney = currentMoney.subtract(list.getItemPrice());
                list.setQuantity(list.getQuantity() - 1);
                if(list.getQuantity() >= 0){
                    UserOutput.dispensingItem();
                    getMessage();
                    System.out.println(list.getItemName() + " " + list.getItemPrice() + " " + currentMoney);
                    logger.write(logger.getCurrentTime() + " " + list.getItemName() + " " + list.getItemSlot() + " " +
                            currentMoney + " " + currentMoney.subtract(list.getItemPrice()));

                }
                else {
                    list.setQuantity(0);
                    UserOutput.notAvailable();
                }
            }
        }
            if (!isValidSelection){
                UserOutput.invalidChoice();
            }

    }
    public BigDecimal addMoney(){
        String input = UserInput.feedMoney();
        BigDecimal startingMoney = currentMoney;

        if (input.equals("1")) {
            BigDecimal amountOne = BigDecimal.valueOf(1.00);
        logger.write(logger.getCurrentTime() + " MONEY FED " + startingMoney + "  " + startingMoney.add(amountOne));
            currentMoney = currentMoney.add(BigDecimal.valueOf(1.00));
        } else if (input.equals("2")) {
            BigDecimal amountFive = BigDecimal.valueOf(5.00);
            logger.write(logger.getCurrentTime() + " MONEY FED " + startingMoney + "  " + startingMoney.add(amountFive));
            currentMoney = currentMoney.add(BigDecimal.valueOf(5.00));
        } else if (input.equals("3")) {
            BigDecimal amountTen = BigDecimal.valueOf(10.00);
            logger.write(logger.getCurrentTime() + " MONEY FED " + startingMoney + "  " + startingMoney.add(amountTen));
           currentMoney = currentMoney.add(BigDecimal.valueOf(10.00));
        } else if (input.equals("4")) {
            BigDecimal amountTwenty = BigDecimal.valueOf(20.00);
            logger.write(logger.getCurrentTime() + " MONEY FED " + startingMoney + "  " + startingMoney.add(amountTwenty));
            currentMoney = currentMoney.add(BigDecimal.valueOf(20.00));
        }
        return currentMoney;
    }
    public static String getCurrentMoney(BigDecimal currentMoney) {
        System.out.println("Current Money Provided: " + "$" + currentMoney);
        return "";
    }

    public static String getMessage (){
        for (Snackable list : snackList){
            if (list.getItemType().equals("Candy")){
                Candy candy = new Candy(list.getItemSlot(), list.getItemName(), list.getItemPrice(), list.getItemType());
                System.out.println(candy.getMessage());
                break;
            }
            else if (list.getItemType().equals("Gum")){
                Gum gum = new Gum(list.getItemSlot(), list.getItemName(), list.getItemPrice(), list.getItemType());
                System.out.println(gum.getMessage());
                break;
            }
            else if (list.getItemType().equals("Munchy")){
                Munchy munchy = new Munchy(list.getItemSlot(), list.getItemName(), list.getItemPrice(), list.getItemType());
                System.out.println(munchy.getMessage());
                break;
            }
            else {
                Drink drink = new Drink(list.getItemSlot(), list.getItemName(), list.getItemPrice(), list.getItemType());
                System.out.println(drink.getMessage());
                break;
            }
        }
        return "";
    }

    public void getChange(){
        double balance = currentMoney.doubleValue();
        balance = balance + 0.001;
        double dollar = getDollar().doubleValue();
        double quarter = getQuarter().doubleValue();
        double dime = getDime().doubleValue();
        double nickel = getNickel().doubleValue();
        double penny = getPenny().doubleValue();
        int dollarCount = 0;
        int quarterCount = 0;
        int dimeCount = 0;
        int nickelCount = 0;
        int pennyCount = 0;

        logger.write(logger.getCurrentTime() + " " + currentMoney + " " + "0.00");

            while (balance >= 1.00){
                balance = balance - dollar;
                dollarCount++;
            }
            while (balance < 1.00 && balance >= 0.25){
                balance = balance - quarter;
                quarterCount++;
            }
            while (balance < 0.25 && balance >= 0.10){
                balance = balance - dime;
                dimeCount++;
            }
            while (balance < 0.10 && balance >= 0.05){
                balance = balance - nickel;
                nickelCount++;
            }
            while (balance < 0.05 && balance >= 0.01){
                balance = balance - penny;
                pennyCount++;
            }

        System.out.println("Change given: " + dollarCount + " dollars " + quarterCount + " quarters "
                + dimeCount + " dimes " + nickelCount + " nickels " + pennyCount + " pennies");
    }


}
