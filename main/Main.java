// * ********************************************************
// * *                                                      *
// * *                Project by:                           *
// * *                Tom Gurevich                          *
// * *                Omer Asayag                           *
// * *                                                      *
// * *                IDs:                                  *
// * *                Tom Gurevich - 326531167              *
// * *                Omer Asayag  - 215487174              *
// * *                                                      *
// * *                Lecturers:                            *
// * *                Tom Gurevich - Aranon Barkat          *
// * *                Omer Asayag  - Aranon Barkat          *
// * *                                                      *
// * ********************************************************
package main;


import java.util.ArrayList;
import java.util.Scanner;

import assignments.UserNameFeatures;
import factories.EntitiesFactory;
import factories.GeneratedActions;

public class Main {
    /**
     * Main class to handle a simple buyer-seller system.
     * Created by:
     * Tom Gurevich
     * Omer Asayag
     */

    private static final Scanner scanner = new Scanner(System.in);
    private static final Manager manager = new Manager();
    private static UserNameFeatures uf;


    public static void main(String[] args) {
        String pluralEntityType = "sellers";
        uf = new UserNameFeatures(manager.getSellers(), pluralEntityType);
        int choice;

        do {
            choice = displayMainMenu(pluralEntityType);
            runFunction(choice);
        } while (choice != 0);

        scanner.close();
    }


    /**
     * Displays the main menu and returns the user's choice.
     *
     * @return the user's choice as an integer
     */
    private static int displayMainMenu(String pluralEntityType) {
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

        return getValidIntegerInput("Enter your choice: ");
    }


    /**
     * Prompts the user to enter a valid integer and handles invalid inputs.
     *
     * @param prompt the message to display to the user
     * @return the valid integer entered by the user
     */
    private static int getValidIntegerInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                scanner.nextLine(); // consume newline left-over
                return number;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // consume the invalid input
            }
        }
    }


    /**
     * Prompts the user to enter a valid non-numeric string and handles invalid inputs.
     *
     * @param prompt the message to display to the user
     * @return the valid string entered by the user
     */
    private static String getValidNonNumericStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a valid value.");
            } else if (containsDigit(input)) {
                System.out.println("Input cannot contain numbers. Please enter a valid value.");
            } else {
                return input;
            }
        }
    }

    /**
     * Checks if a string contains any digits.
     *
     * @param input the string to check
     * @return true if the string contains digits, false otherwise
     */
    private static boolean containsDigit(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }



    /**
     * Executes the function corresponding to the user's choice.
     *
     * @param choice the user's choice from the main menu
     */
    private static void runFunction(int choice) {
        switch (choice) {
            case 0 -> System.out.println("Exiting the menu.");
            case 1 -> addSeller();
            case 2 -> addBuyer();
            case 3 -> addItemToSeller();
            case 4 -> addItemToBuyerCart();
            case 5 -> payForOrder();
            case 6 -> displayAllBuyerDetails();
            case 7 -> displayAllSellerDetails();
            case 8 -> displayProductsByCategory();
            case 9 -> generateNewShoppingCartFromHistory();
            // Course 10119:
            case 99 -> uf.printNames();
            case 100 -> uf.printNamesCountedDuplicates();
            case 101 -> printNoAppearSellerName();
            case 102 -> uf.printUniqueUsernamesTwiceBackwards();
            case 103 -> uf.sortByUsernameAndDisplay();
            case -1 -> createTestSystem();
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }


    /**
     * Prompts the user to enter a unique username.
     * Ensures that the username is not already taken by any buyer or seller.
     * New Update: handling with generation of name.
     *
     * @param entityType the type of entity (buyer/seller) for prompting the user.
     * @return a unique username
     */
    private static String getUniqueUsername(String entityType) {
        System.out.print("Enter " + entityType + " username: ");

        String username = scanner.nextLine();

        while (manager.containsUsername(username)) {
            System.out.print("Username already taken, please choose a new one: ");
            username = scanner.nextLine();
        }

        return username;
    }

    /**
     * Adds a new seller after prompting for username and password.
     */
    private static void addSeller() {
        String username = getUniqueUsername("Seller");
        System.out.print("Enter seller password: ");
        String password = scanner.nextLine();

        Seller seller = new Seller(username, password);
        manager.addSeller(seller);
        System.out.println("Seller added successfully.");
    }

    /**
     * Adds a new buyer after prompting for username, password, and address.
     */
    private static void addBuyer() {
        String username = getUniqueUsername("Buyer");
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter street name: ");
        String streetName = scanner.nextLine();
        int buildingNumber = getValidIntegerInput("Enter building number: ");
        String city = getValidNonNumericStringInput("Enter city: ");
        String country = getValidNonNumericStringInput("Enter country: ");

        Address address = new Address(streetName, buildingNumber, city, country);
        Buyer buyer = new Buyer(username, password, address);

        if (manager.addBuyer(buyer)) {
            System.out.println("Buyer added successfully.");
        } else {
            System.out.println("Error adding buyer. Please try again.");
        }
    }

    private static void printAllBuyers() {
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



    private static void printAllSellers() {
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



    /**
     * Prompts the user to select a buyer from the list.
     *
     * @return the selected Buyer object, or null if no buyers are available
     */
    private static Buyer selectBuyer() {
        if (manager.getBuyersCount() == 0) {
            System.out.println("No buyers available.");
            return null;
        }

        Buyer buyer = null;
        while (buyer == null) {
            printAllBuyers();
            int buyerIndex = getValidIntegerInput("Enter the number of the buyer: ") - 1; // Convert to zero-based index
            buyer = manager.getBuyerByIndex(buyerIndex);
            if (buyer == null) {
                System.out.println("Invalid selection, please try again.");
            }
        }
        return buyer;
    }

    /**
     * Prompts the user to select a seller from the list.
     *
     * @return the selected Seller object, or null if no sellers are available
     */
    private static Seller selectSeller() {
        if (manager.getSellersCount() == 0) {
            System.out.println("No sellers available.");
            return null;
        }

        Seller seller = null;
        while (seller == null) {
            printAllSellers();
            int sellerIndex = getValidIntegerInput("Enter the number of the seller: ") - 1; // Convert to zero-based index
            seller = manager.getSellerByIndex(sellerIndex);
            if (seller == null) {
                System.out.println("Invalid selection, please try again.");
            }
        }
        return seller;
    }

    /**
     * Prompts the user to select a product from the seller's list.
     *
     * @param seller the Seller object from which to select a product
     * @return the selected Product object, or null if no products are available
     */
    private static Product selectProduct(Seller seller) {
        Product product = null;
        while (product == null) {
            ArrayList<Product> products = seller.getProducts();
            int productCount = seller.getProducts().size();
            if (productCount == 0) {
                System.out.println("This seller has no products available.");
                return null;
            }

            System.out.println("Available products:");
            int n = 1;
            for (Product product1 : products) {
                System.out.println((n++) + ". " + product1.getName() + " - $" + product1.getPrice());
            }

            int productNumber = getValidIntegerInput("Enter product number to add to cart: ");

            if (productNumber < 1 || productNumber > productCount) {
                System.out.println("Invalid product number. Please try again.");
            } else {
                product = products.get(productNumber - 1);
            }
        }
        return product;
    }

    /**
     * Prompts the user to select a product category.
     *
     * @return the selected ProductCategory
     */
    private static ProductCategory selectCategory() {
        while (true) {
            System.out.println("Select a category:");
            for (int i = 0; i < ProductCategory.values().length; i++) {
                System.out.println(i + " - " + ProductCategory.values()[i].getDisplayName());
            }

            int categoryIndex = getValidIntegerInput("Enter your choice: ");
            StringBuilder errorMessage = new StringBuilder();
            boolean isValid = manager.selectProductCategory(categoryIndex, errorMessage);

            if (isValid) {
                return ProductCategory.fromOrdinal(categoryIndex);
            } else {
                System.out.println(errorMessage);
            }
        }
    }


    private static void displayProductsByCategory() {
        ProductCategory category = selectCategory();
        String products = manager.getProductsByCategory(category);
        System.out.println(products);
    }

    /**
     * Allows a seller to add a new product to their list.
     */
    private static void addItemToSeller() {
        Seller seller = selectSeller();
        if (seller == null) {
            return;
        }

        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        int price = getValidIntegerInput("Enter product price: ");

        ProductCategory category = selectCategory();

        System.out.print("Does the product have special wrapping? (yes/no): ");
        String wrappingResponse = scanner.nextLine().trim().toLowerCase();
        boolean specialWrapping = wrappingResponse.equals("yes");

        if (specialWrapping) {
            int additionalCost = getValidIntegerInput("Enter the additional cost for special wrapping: ");
            price += additionalCost;
        }

        Product product = new Product(productName, price, category, specialWrapping);
        seller.addProduct(product);
        System.out.println("Product added successfully.");
    }



    /**
     * Allows a buyer to add a product to their shopping cart from a seller's list.
     */
    private static void addItemToBuyerCart() {
        Buyer buyer = selectBuyer();
        if (buyer == null) {
            return;
        }

        Seller seller = selectSeller();
        if (seller == null) {
            return;
        }

        Product product = selectProduct(seller);
        if (product == null) {
            return;
        }

        buyer.addToCart(product);
        System.out.println("Product added to cart successfully.");
    }

    /**
     * Finalizes the payment for a buyer's order and clears their cart.
     */
    private static void payForOrder() {
        Buyer buyer = selectBuyer();
        if (buyer == null) {
            return;
        }

        String result = manager.processPayment(buyer);
        System.out.println(result);
    }


    /**
     * Displays details of all buyers including their current cart and purchase history.
     */
    private static void displayAllBuyerDetails() {
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
    private static void displayAllSellerDetails() {
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

    private static void generateNewShoppingCartFromHistory() {
        Buyer buyer = selectBuyer();
        if (buyer == null) {
            return;
        }

        if (buyer.getCartCount() > 0) {
            System.out.print("Your current shopping cart is not empty. Are you sure you want to replace it? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) {
                System.out.println("Operation cancelled. Your current shopping cart was not replaced.");
                return;
            }
        }

        ArrayList<Order> purchaseHistory = buyer.getPurchaseHistory();
        int purchaseHistoryCount = buyer.getPurchaseHistory().size();

        if (purchaseHistoryCount == 0) {
            System.out.println("You have no purchase history.");
            return;
        }

        System.out.println("Select a purchase history to load into your shopping cart:");
        int n = 1;
        for (Order order : purchaseHistory) {
            System.out.println((n++) + " - " + order);
        }

        int choice = getValidIntegerInput("Enter your choice: ") - 1;

        String result = manager.generateNewShoppingCartFromHistory(buyer, choice);
        System.out.println(result);
    }


    /**
     * print number of appearance of certain name in the list of Sellers.
     */
    private static void printNoAppearSellerName() {
        System.out.println("Please enter the name of the seller:");
        String name = scanner.nextLine();
        uf.printNumOfNameOccurrences(name);
    }



    /**
     * create a test system for functionality for course 10119
     */
    private static void createTestSystem() {
        //create Sellers' list
        createTestList("Sellers");
        GeneratedActions.addSampleItemsToSellers(manager);
        //Step 2: create Buyers' list
        createTestList("Buyers");
        GeneratedActions.addSampleItemsToBuyer(manager);
        GeneratedActions.payForOrder(manager);

    }
    /**
     * create the test list for Sellers' list
     * @param pluralEntityType is the field for Sellers/Buyers
     */
    private static void createTestList(String pluralEntityType){
        ArrayList<? extends User> newUsers = EntitiesFactory.createEntitiesList(pluralEntityType);

        if (newUsers.getFirst() instanceof Seller){
            newUsers.forEach(user -> manager.getSellers().add((Seller)user));
            messageDoneToGenerate(newUsers, "Sellers");
        }
        else if (newUsers.getFirst() instanceof Buyer){
            newUsers.forEach(user -> manager.getBuyers().add((Buyer)user));
            messageDoneToGenerate(newUsers, "Buyers");
        }

    }
    /**
     * prints the message of successful generation
     */
    private static void messageDoneToGenerate(ArrayList<? extends User> newUsers, String pluralEntityType){
        System.out.println("Automatic generation of " + pluralEntityType + " successful");
        newUsers.forEach(user -> System.out.println("Added seller: " + user.getUsername()));
    }

}
