package com.techelevator.ui;

import com.techelevator.extraclasses.Snackable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserOutput {

    private static List<String> itemList = new ArrayList<>();
    private static File file = new File("catering.csv");


    public static void displayHomeScreen() {
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                      Home");
        System.out.println("***************************************************");
        System.out.println();
    }

    public static void displayDisplayScreen() {
        System.out.println();
        System.out.println("********************************************************************************");
        System.out.printf("%-20s %-20s %-20s %-20s", "Slot", "Name", "Price", "Quantity        *");
        System.out.println();
        System.out.println("********************************************************************************");
        System.out.println();
    }

    public static void displayItems(List<Snackable> snackList) {
        for (Snackable item : snackList) {
            System.out.printf("%-20s %-20s %-20s %-20s", item.getItemSlot(), item.getItemName(), item.getItemPrice(), item.getQuantity());
            System.out.println();
        }
        System.out.println();
    }

    public static void displayPurchaseScreen() {
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                   Purchase Menu                   ");
        System.out.println("***************************************************");
        System.out.println();

    }

    public static void displayFeedMoneyScreen(){
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                     Feed Money                    ");
        System.out.println("***************************************************");
        System.out.println();
    }

    public static String getSelection(){

        System.out.println("What would you like to purchase?");
        System.out.println("******************************************************************");
        System.out.println();
        return "";
    }

    public static String invalidChoice(){
        System.out.println("Invalid Choice!");
        return "";
    }

    public static String dispensingItem(){
        System.out.println("Dispensing Item");
        return "";
    }

    public static String notAvailable(){
        System.out.println("NO LONGER AVAILABLE");
        return "";
    }

}
