package main;

import factories.BuyerFactory;
import factories.ProductFactory;
import factories.SellerFactory;
import features.SystemOutput;
import features.UserInput;

import java.util.ArrayList;

// TODO : Check if there is no such a user.

/**
 * The Manager class is responsible for managing the lists of buyers and sellers,
 * including adding new buyers or sellers, checking for existing usernames,
 * retrieving buyers or sellers by index, and printing all buyers or sellers.
 *
 * This class contains methods for adding entities, checking if usernames exist,
 * retrieving entities by index, and printing entities. It ensures that usernames
 * are unique across both buyers and sellers.
 *
 * This class is implemented as a singleton to ensure that only one instance
 * of the Manager class exists throughout the application.
 */
public class Manager {
    private static ArrayList<Buyer> buyers = new ArrayList<>();
    private static ArrayList<Seller> sellers = new ArrayList<>(); // for Section 15.


    /**
     * Processes payment for the buyer's cart and returns the cart details and total price.
     *
     * @param buyer the buyer whose cart is to be paid for
     * @return a string containing the cart details and total price, or an error message if the cart is empty
     */
    private String processPayment(Buyer buyer) {
        try {
            if (buyer.getCartCount() == 0) {
                throw new EmptyCartException("The cart is empty. Cannot proceed with payment.");
            }

            ArrayList<Product> cart = buyer.getCart();
            int totalPrice = 0;
            StringBuilder receipt = new StringBuilder("Cart items:\n");

            int n = 1;
            for (Product product : cart) {
                receipt.append((n)).append(". ")
                        .append(product.getName()).append(" - $")
                        .append(product.getPrice()).append("\n");
                totalPrice += product.getPrice();
                ++n;
            }
            receipt.append("Total price: $").append(totalPrice).append("\n");

            buyer.completePurchase();
            receipt.append("Purchase completed successfully.");
            return receipt.toString();
        } catch (EmptyCartException e) {
            return e.getMessage();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Replaces the buyer's current shopping cart with items from the selected order in their purchase history.
     *
     * @param buyer                the buyer whose cart is to be replaced
     * @param purchaseHistoryIndex the index of the selected order in the buyer's purchase history
     * @return a message indicating the result of the operation
     */
    public String generateNewShoppingCartFromHistory(Buyer buyer, int purchaseHistoryIndex) {
        if (purchaseHistoryIndex < 0 || purchaseHistoryIndex > buyer.getPurchaseHistory().size() - 1)
        {
            return "Invalid selection. Operation cancelled.";
        }

        Order selectedOrder = buyer.getPurchaseHistory().get(purchaseHistoryIndex);
        ArrayList<Product> selectedProducts = selectedOrder.getProducts();
        buyer.clearCart();

        for (Product product : selectedProducts) {
            buyer.addToCart(new Product(product));
        }

        return "Your shopping cart has been replaced with items from the selected purchase history.";
    }


    /**
     * Adds a new buyer to the list.
     *
     * @return true if the buyer was added successfully, false if the username already exists
     */
    public void addBuyer() {
        addEntity(BuyerFactory.createBuyer(), buyers);
    }

    /**
     * Adds a new seller to the list.
     */
    public void addSeller() {
        addEntity(SellerFactory.createSeller(), sellers);
    }

    /**
     * Checks if a username already exists in either the buyers or sellers list.
     *
     * @param username the username to check
     * @return true if the username exists, false otherwise
     */
    public boolean containsUsername(String username) {
        return containsUsernameInArray(username, buyers) || containsUsernameInArray(username, sellers);
    }

    /**
     * Sorts the sellers by the number of products they sell in descending order.
     */
    public void sortSellersByProductCount() {
        sellers.sort(Seller::compareTo);
    }

    /**
     * Sorts the buyers by their username in ascending order.
     */
    public void sortBuyersByUsername() {
        buyers.sort(Buyer::compareTo);
    }

    /**
     * Gets a buyer by their index in the buyers list.
     *
     * @param index the index of the buyer
     * @return the Buyer object at the specified index, or null if the index is out of bounds
     */
    public Buyer getBuyerByIndex(int index) {
        if (index < 0 || index > buyers.size() - 1)
            return null;
        return buyers.get(index);
    }

    /**
     * Gets a seller by their index in the sellers list.
     *
     * @param index the index of the seller
     * @return the Seller object at the specified index, or null if the index is out of bounds
     */
    public Seller getSellerByIndex(int index) {
        if (index < 0 || index >= sellers.size()) {
            return null;
        }
        return sellers.get(index);
    }

    /**
     * @return a list of strings with all buyers' name.
     */
    public ArrayList<String> getAllBuyers() {
        sortBuyersByUsername();
        return getAllUsernames(buyers);
    }

    /**
     * Prints all sellers' usernames.
     */
    public ArrayList<String> getAllSellers() {
        sortSellersByProductCount();
        return getAllUsernames(sellers);
    }

    /**
     * Gets the list of buyers.
     *
     * @return a list of Buyer objects
     */
    public ArrayList<Buyer> getBuyers() {
        return buyers;
    }

    /**
     * Gets the list of sellers.
     *
     * @return a list of Seller objects
     */
    public ArrayList<Seller> getSellers() {
        return sellers;
    }


    /**
     * Adds a new entity (buyer or seller) to the specified list.
     *
     * @param entity        the entity to add
     * @param userArrayList the list to which the entity is added
     * @return true if the entity was added successfully, false if the username already exists
     */
    private <T extends User> boolean addEntity(T entity, ArrayList<T> userArrayList) {
        if (containsUsernameInArray(entity.getUsername(), userArrayList)) {
            return false;
        }

        userArrayList.add(entity);
        return true;
    }

    /**
     * Checks if a username exists in the specified array.
     *
     * @param username      the username to check
     * @param userArrayList the list to search
     * @return true if the username exists in the array, false otherwise
     */
    private static boolean containsUsernameInArray(String username, ArrayList<? extends User> userArrayList) {
        for (User user : userArrayList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Collects a list of all usernames from the specified list.
     *
     * @param userArrayList the array of User objects
     * @return a list of usernames
     */
    public ArrayList<String> getAllUsernames(ArrayList<? extends User> userArrayList) {
        ArrayList<String> usernames = new ArrayList<>();

        for (User user : userArrayList) {
            usernames.add(user.getUsername());
        }
        return usernames;
    }

    /**
     * Collects all products of the specified category from all sellers.
     *
     * @param category the category of the products to display
     * @return a string containing the product details
     */
    public String getProductsByCategory(ProductCategory category) {
        StringBuilder productDetails = new StringBuilder();
        boolean found = false;

        for (Seller seller : sellers) {
            ArrayList<Product> products = seller.getProducts();
            for (Product product : products) {
                if (product.getCategory() == category) {
                    productDetails.append(product.toString()).append("\n");
                    found = true;
                }
            }
        }

        if (!found) {
            productDetails.append("No products found in the ").append(category.getDisplayName()).append(" category.");
        }

        return productDetails.toString();
    }



    /**
     * @return how many Sellers there are.
     */
    public int getSellersCount() {
        return sellers.size();
    }

    /**
     * @return how many Buyers there are.
     */
    public int getBuyersCount() {
        return buyers.size();
    }



    /**
     * create a test system for functionality for course 10119
     */
    public void createTestSystem() {
        ArrayList<Buyer> newBuyers = BuyerFactory.createListBuyer();
        getBuyers().addAll(newBuyers);
        messageDoneToGenerate(newBuyers, "Buyers");
        ArrayList<Seller> newSellers = SellerFactory.createListSeller();
        this.getSellers().addAll(newSellers);
        messageDoneToGenerate(newSellers, "Sellers");
    }

    /**
     * prints the message of successful generation
     */
    private static void messageDoneToGenerate(ArrayList<? extends User> newUsers, String pluralEntityType){
        System.out.println("Automatic generation of " + pluralEntityType + " successful");
        newUsers.forEach(user -> System.out.println("Added user: " + user.getUsername()));
    }

    /**
     * Allows a seller to add a new product to their list.
     */
    public void addItemToSeller() {
        Seller seller = UserInput.selectSeller(this);
        if (seller == null) {
            return;
        }

        Product product = ProductFactory.createProduct();
        seller.addProduct(product);
        System.out.println("Product added successfully.");
    }

    /**
     * Allows a buyer to add a product to their shopping cart from a seller's list.
     */
    public void addItemToBuyerCart() {
        Buyer buyer = UserInput.selectBuyer(this);
        if (buyer == null) {
            return;
        }

        Seller seller = UserInput.selectSeller(this);
        if (seller == null) {
            return;
        }

        Product product = UserInput.selectProduct(seller);
        if (product == null) {
            return;
        }

        buyer.addToCart(product);
        System.out.println("Product added to cart successfully.");
    }

    /**
     * Finalizes the payment for a buyer's order and clears their cart.
     */
    public void payForOrder() {
        Buyer buyer = UserInput.selectBuyer(this);
        if (buyer == null) {
            return;
        }

        String result = this.processPayment(buyer);
        System.out.println(result);
    }

    /**
     *  Displays all buyers' details
     * */
    public void displayAllBuyerDetails()
    {
        SystemOutput.displayAllBuyerDetails(this);
    }

    /**
     *  Displays all buyers' details
     * */
    public void displayAllSellerDetails()
    {
        SystemOutput.displayAllSellerDetails(this);
    }
    /**
     *  Displays all products by categories.
     * */
    public void displayProductsByCategory() {
        SystemOutput.displayProductsByCategory(this);
    }

    /**
     * Generates a new shopping cart from history for a certain buyer.
     * */
    public void generateNewShoppingCartFromHistory() {
        Buyer buyer = UserInput.selectBuyer(this);
        if (!UserInput.decideRestoreHistoryCart(this, buyer)) {
            return;
        }

        SystemOutput.displayHistoryPurchase(this, buyer);

        int choice = UserInput.getValidIntegerInput("Enter your choice: ") - 1;

        String result = this.generateNewShoppingCartFromHistory(buyer, choice);
        System.out.println(result);
    }
}
