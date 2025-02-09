package main;

import features.UserInput;

import java.util.ArrayList;
import java.util.List;

public class ManagerSystemOutput {


    /**
     * Displays the main menu and returns the user's choice.
     *
     * @return the user's choice as an integer
     */
    public static int displayMainMenu(String pluralEntityType, boolean hasBuyers, boolean hasSellers) {
        System.out.println("\nChoose one of the following options:");
        System.out.println("-1 - Generate test values of " + pluralEntityType);
        System.out.println("0 - Exit");
        System.out.println("1 - Add seller");
        System.out.println("2 - Add buyer");

        if (hasSellers) {
            System.out.println("3 - Add item to seller");
        } else {
            System.out.println("3 - Add item to seller (Not available - No sellers)");
        }

        if (hasBuyers && hasSellers) {
            System.out.println("4 - Add item to buyer's shopping cart");
        } else if (!hasBuyers && !hasSellers) {
            System.out.println("4 - Add item to buyer's shopping cart (Not available - No buyers and no sellers)");
        } else if (hasBuyers) {
            System.out.println("4 - Add item to buyer's shopping cart (Not available - No buyers)");
        } else {
            System.out.println("4 - Add item to buyer's shopping cart (Not available - No sellers)");
        }

        if (hasBuyers) {
            System.out.println("5 - Pay for customer order");
        } else {
            System.out.println("5 - Pay for customer order (Not available - No buyers)");
        }

        if (hasBuyers) {
            System.out.println("6 - Display all buyer details");
        } else {
            System.out.println("6 - Display all buyer details (Not available - No buyers)");
        }

        if (hasSellers) {
            System.out.println("7 - Display all seller details");
        } else {
            System.out.println("7 - Display all seller details (Not available - No sellers)");
        }

        System.out.println("8 - Display products by category");

        if (hasBuyers) {
            System.out.println("9 - Generate new shopping cart from purchase history");
        } else {
            System.out.println("9 - Generate new shopping cart from purchase history (Not available - No buyers)");
        }

        // Course 10119:
        System.out.println("99 - View all " + pluralEntityType + " usernames");
        System.out.println("100 - Display " + pluralEntityType + " usernames with their counts");
        System.out.println("101 - Check how many " + pluralEntityType + " have a specific username");
        System.out.println("102 - Duplicate and reverse the names of all " + pluralEntityType + ", then display them.");
        System.out.println("103 - Sort names of all " + pluralEntityType + " without duplicates by string length and display them.");
        System.out.println("104 - Save ArrayList");
        System.out.println("105 - Restore ArrayList");

        return UserInput.getValidIntegerInput("Enter your choice: ");
    }

    private static void printListNumbered(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + ". " + list.get(i));
        }
    }

    public static void printAllUsers(ArrayList<String> userNames, String userType) {
        if (userNames.isEmpty()) {
            System.out.println("No " + userType + " found.");
        } else {
            printListNumbered(userNames);
        }
    }



    public static void displayAllUserDetails(ArrayList<?> users, String userType) {
        if (users.isEmpty()) {
            System.out.println("No " + userType + " found.");
            return;
        }

        users.forEach(System.out::println);
    }


    public static void displayHistoryPurchase(Buyer buyer) {
        ArrayList<Order> purchaseHistory = buyer.getPurchaseHistory();

        if (purchaseHistory.isEmpty()) {
            System.out.println("You have no purchase history.");
            return;
        }

        System.out.println("Select a purchase history to load into your shopping cart:");
        printListNumbered(purchaseHistory);
    }


}
