package features;

import factories.AddressFactory;
import main.*;

import java.util.ArrayList;
import java.util.Scanner;

/*
Class is responsible to manage the input of the user.
 */
public class UserInput {


    public static final Scanner scanner = new Scanner(System.in);


    /**
     * Prompts the user to enter a unique username.
     * Ensures that the username is not already taken by any buyer or seller.
     * New Update: handling with generation of name.
     *
     * @param entityType the type of entity (buyer/seller) for prompting the user.
     * @return a unique username
     */
    public static String getUsername(String entityType) {
        System.out.print("Enter " + entityType + " username: ");
        String username;

        do {
          username = scanner.nextLine();
        } while (username.isEmpty());

        return username;
    }

    public static String getStringInput() {
        return scanner.nextLine();
    }
//    /**
//     * Prompts the user to enter a unique username.
//     * Ensures that the username is not already taken by any buyer or seller.
//     * New Update: handling with generation of name.
//     *
//     * @param entityType the type of entity (buyer/seller) for prompting the user.
//     * @return a unique username
//     */
//    public static String getUniqueUsername(String entityType, Manager manager) {
//        System.out.print("Enter " + entityType + " username: ");
//
//        String username = scanner.nextLine();
//
//        while (manager.containsUsername(username)) {
//            System.out.print("Username already taken, please choose a new one: ");
//            username = scanner.nextLine();
//        }
//
//        return username;
//    }

    /**
     * Prompts the user to enter a valid non-numeric string and handles invalid inputs.
     *
     * @param prompt the message to display to the user
     * @return the valid string entered by the user
     */
    protected static String getValidNonNumericStringInput(String prompt) {
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
    // Helper function:
    private static boolean containsDigit(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prompts the user to enter a valid integer and handles invalid inputs.
     *
     * @param prompt the message to display to the user
     * @return the valid integer entered by the user
     */
    public static int getValidIntegerInput(String prompt) {
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
     * Prompts the user to select a product from the seller's list.
     *
     * @param seller the Seller object from which to select a product
     * @return the selected Product object, or null if no products are available
     */
    public static Product selectProduct(Seller seller) {
        Product product = null;
        while (product == null) {
            ArrayList<Product> products = seller.getProducts();
            int productCount = seller.getProducts().size();
            if (productCount == 0) {
                System.out.println("This seller has no products available.");
                return null;
            }

            System.out.println("Available products:");
            for (int i = 0; i < products.size(); i++) {

                System.out.println((i+1) + ". " + product.getName() + " - $" + products.get(i).getPrice());
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
     * gets the password (every space is available, including: spaces)
    * @return password
    */
    public static String getPassword() {
        String input;
        while (true) {
            System.out.print("Please enter a password: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                break;
            }
            System.out.println("Input cannot be empty. Please enter a valid value.");
        }
        return input;
    }
    /**
     * get the product name, and return it.
     * @return the name of the product
     * */
    public static String getProductName() {
        return getValidNonNumericStringInput("Please enter the name of the product: ");
    }
    /**
     * get the address, and return it.
     * @return the name of the product
     * */
    public static Address getAddress() {
        return AddressFactory.createAddress();
    }
    /**
     * get the street name, and return it.
     * @return the name of the product
     * */
    public static String getStreetName() {
        return getValidNonNumericStringInput("Please Enter the name of the product: ");
    }
    /**
     * get the building number, and return it.
     * @return the building name.
     * */
    public static int getBuildingNumber() {
        return getValidIntegerInput("Please enter the building number: ");
    }
    /**
     * get the city name, and return it.
     * @return the name of the city.
     * */
    public static String getCityName() {
        return getValidNonNumericStringInput("Please Enter the name of the city: ");
    }
    /**
     * get the country name, and return it.
     * @return the name of the product
     * */
    public static String getCountryName() {
        return getValidNonNumericStringInput("Please Enter the name of the country: ");
    }
    /**
     * get the price, and return it.
     * @return the price.
     * */
    public static int getPrice() {

        return getValidIntegerInput("Please enter the price: ");
    }

    public static int getAdditionalPrice() {

        return getValidIntegerInput("Please nter the additional cost for special wrapping: ");
    }
    /**
     * Prompts the user to select a product category.
     *
     * @return the selected ProductCategory
     */
    public static ProductCategory getProductCategory() {
        while (true) {
            System.out.println("Select a category:");
            for (int i = 0; i < ProductCategory.values().length; i++) {
                System.out.println(i + " - " + ProductCategory.values()[i].getDisplayName());
            }

            int categoryIndex = getValidIntegerInput("Enter your choice: ");
            StringBuilder errorMessage = new StringBuilder();
            boolean isValid = selectProductCategory(categoryIndex, errorMessage);

            if (isValid) {
                return ProductCategory.fromOrdinal(categoryIndex);
            } else {
                System.out.println(errorMessage);
            }
        }
    }
    /**
     * Handles the category selection and returns whether the selection was valid.
     *
     * @param categoryIndex the index of the selected category
     * @param errorMessage  a reference to store the error message if the selection is invalid
     * @return true if the selection is valid, false otherwise
     */
    public static boolean selectProductCategory(int categoryIndex, StringBuilder errorMessage) {
        try {
            ProductCategory.fromOrdinal(categoryIndex);
            return true;
        } catch (IllegalArgumentException e) {
            errorMessage.append(e.getMessage());
            return false;
        }
    }
    /**
     * return True : if there is a special wrapping.
     */
    public static boolean existsSpecialWrapping() {
        System.out.print("Does the product have special wrapping? (yes/no): ");
        return getAnswerBinary();
    }

    public static boolean getAnswerBinary() {
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes");
    }



    public static void end() {
        scanner.close();
    }

}
