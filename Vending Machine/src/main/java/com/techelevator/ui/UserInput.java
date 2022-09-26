package com.techelevator.ui;


import com.techelevator.application.VendingMachine;
import com.techelevator.extraclasses.Snackable;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 * <p>
 * Dependencies: None
 */
public class UserInput {

    private static Scanner scanner = new Scanner(System.in);


    public static String getHomeScreenOption() {
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("D) Display Items");
        System.out.println("P) Purchase");
        System.out.println("E) Exit");

        System.out.println();
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toLowerCase();
        System.out.println("option = " + option);
        if (option.equals("d")) {
            return "display";
        } else if (option.equals("p")) {
            return "purchase";
        } else if (option.equals("e")) {
            return "exit";
        } else {
            return "";
        }

    }

    public static String getPurchaseScreenOption() {
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("M) Feed Money");
        System.out.println("S) Select Item");
        System.out.println("F) Finish Transaction");

        System.out.println();
        System.out.println("Please select an option: ");
        System.out.println();



        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toLowerCase();
        System.out.println("option = " + option);
        System.out.println();
        if (option.equals("m")) {
            return "feed";
        } else if (option.equals("s")) {
            return "select";
        } else if (option.equals("f")){
            return "finish";
        }
        else {
            return "";
        }
    }


    public static String feedMoney() {


        System.out.println("Please insert cash: ");
        System.out.println("How much would you like to add?");
        System.out.println();
        System.out.println("1) $1.00");
        System.out.println("2) $5.00");
        System.out.println("3) $10.00");
        System.out.println("4) $20.00");
        System.out.println();
        System.out.println("Please enter 1, 2, 3 or 4: ");


        String selectedOption = scanner.nextLine();
        String option = selectedOption;
        System.out.println("option = " + option);
        System.out.println();
        if (option.equals("1")) {
            return "1";
        } else if (option.equals("2")) {
           return "2";
        } else if (option.equals("3")) {
            return "3";
        } else if (option.equals("4")) {
            return "4";
        }else{

        }
        return "";
    }


    public static String getOption(){
        System.out.println("Please enter letter and number of selection: ");
        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toUpperCase();

        return option;
    }



}

