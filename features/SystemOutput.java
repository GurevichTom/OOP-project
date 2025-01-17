package features;

import main.*;

import java.util.ArrayList;

public class SystemOutput {


    /**
     * Displays the main menu and returns the user's choice.
     *
     * @return the user's choice as an integer
     */
    public static int displayMainMenu(String pluralEntityType, Manager manager) {
        System.out.println("\nChoose one of the following options:");
        System.out.println("-1 - Generate test values of " + pluralEntityType);
        System.out.println("0 - Exit");
        System.out.println("1 - Add seller");
        System.out.println("2 - Add buyer");

        if (manager.getSellersCount() > 0) {
            System.out.println("3 - Add item to seller");
        } else {
            System.out.println("3 - Add item to seller (Not available - No sellers)");
        }

        if (manager.getBuyersCount() > 0 && manager.getSellersCount() > 0) {
            System.out.println("4 - Add item to buyer's shopping cart");
        } else if (manager.getBuyersCount() == 0 && manager.getSellersCount() == 0) {
            System.out.println("4 - Add item to buyer's shopping cart (Not available - No buyers and no sellers)");
        } else if (manager.getBuyersCount() == 0) {
            System.out.println("4 - Add item to buyer's shopping cart (Not available - No buyers)");
        } else {
            System.out.println("4 - Add item to buyer's shopping cart (Not available - No sellers)");
        }

        if (manager.getBuyersCount() > 0) {
            System.out.println("5 - Pay for customer order");
        } else {
            System.out.println("5 - Pay for customer order (Not available - No buyers)");
        }

        if (manager.getBuyersCount() > 0) {
            System.out.println("6 - Display all buyer details");
        } else {
            System.out.println("6 - Display all buyer details (Not available - No buyers)");
        }

        if (manager.getSellersCount() > 0) {
            System.out.println("7 - Display all seller details");
        } else {
            System.out.println("7 - Display all seller details (Not available - No sellers)");
        }

        System.out.println("8 - Display products by category");

        if (manager.getBuyersCount() > 0) {
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

        return UserInput.getValidIntegerInput("Enter your choice: ");
    }

    /**
     * print all the names of the same entity type arraylist
     * @param entityTypeNames names of the arraylists of that entity type.
     */
    // printAllBuyersNames, printAllSellersNames
    private static void printAllEntityTypeNames(ArrayList<String> entityTypeNames, Manager manager) {

        if (entityTypeNames.isEmpty()) {
            System.out.println("No buyers found.");
            return;
        }
        for (int i = 0; i < entityTypeNames.size(); i++) {
            System.out.println((i+1) + ". " + entityTypeNames.get(i));
        }
    }

    public static void displayProductsByCategory(Manager manager) {
        ProductCategory category = UserInput.selectCategory(manager);
        String products = manager.getProductsByCategory(category);
        System.out.println(products);
    }

    /**
     * prints the name of all the sellers
     * */
    public static void printAllSellers(Manager manager) {
        ArrayList<String> sellerNames = manager.getAllSellers();
        if (sellerNames.isEmpty()) {
            System.out.println("No sellers found.");
        } else {
            int n = 1;
            for (String name : sellerNames) {
                System.out.println((n++) + ". " + name);
            }
        }
    }

    public static void printAllBuyers(Manager manager) {
        ArrayList<String> buyerNames = manager.getAllBuyers();
        if (buyerNames.isEmpty()) {
            System.out.println("No buyers found.");
        } else {
            int n = 1;
            for (String name : buyerNames) {
                System.out.println((n++) + ". " + name);
            }
        }
    }
    // TODO : To do GENERIC?

    /**
     * Displays details of all buyers including their current cart and purchase history.
     */
    public static void displayAllBuyerDetails(Manager manager) {
        manager.sortBuyersByUsername();
        ArrayList<Buyer> buyers = manager.getBuyers();
        int buyersCount = manager.getBuyersCount();

        if (buyersCount == 0) {
            System.out.println("No buyers found.");
            return;
        }

        for (Buyer buyer : buyers) {
            System.out.println(buyer);
        }
    }

    /**
     * Displays details of all sellers including their product list.
     */
    public static void displayAllSellerDetails(Manager manager) {
        manager.sortSellersByProductCount();
        ArrayList<Seller> sellers = manager.getSellers();
        int sellersCount = manager.getSellersCount();

        if (sellersCount == 0) {
            System.out.println("No sellers found.");
            return;
        }

        for (Seller seller : sellers) {
            System.out.println(seller);
        }
    }

    public static void displayHistoryPurchase(Manager manager, Buyer buyer) {
        ArrayList<Order> purchaseHistory = buyer.getPurchaseHistory();
        int purchaseHistoryCount = buyer.getPurchaseHistory().size();

        if (purchaseHistoryCount == 0) {
            System.out.println("You have no purchase history.");
            return;
        }

        System.out.println("Select a purchase history to load into your shopping cart:");
        for (int i = 0; i < purchaseHistory.size(); i++) {
            System.out.println((i+1) + " - " + purchaseHistory.get(i));
        }
    }
    // TODO : Generics?


}
