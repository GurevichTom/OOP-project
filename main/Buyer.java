package main;

import java.util.ArrayList;

/**
 * The Buyer class represents a buyer in the system. It contains the buyer's username,
 * password, address, a cart for current items, and a purchase history.
 */
public class Buyer extends User implements Comparable<Buyer>{
    private Address address;
    private ArrayList<Product> cart;
    private ArrayList<Order> purchaseHistory = new ArrayList<>();

    /**
     * Constructs a new Buyer with the specified username, password, and address.
     *
     * @param username the username of the buyer
     * @param password the password of the buyer
     * @param address the address of the buyer
     */
    public Buyer(String username, String password, Address address) {
        super(username, password);
        this.address = address;
        this.cart = new ArrayList<>();
    }

    /**
     * Adds a product to the buyer's cart.
     *
     * @param product the product to be added to the cart
     */
    public void addToCart(Product product) {
        cart.add(product);
    }

    /**
     * Clears the current shopping cart.
     */
    public void clearCart() {
        cart.clear(); // remove all the elements from cart.
    }

    /**
     * Finalizes the purchase for the buyer, moving the items from the cart to the purchase history.
     */
    public void completePurchase() throws CloneNotSupportedException {
        /*// Create a deep copy of the cart
        Product[] cartCopy = new Product[cartCount];
        for (int i = 0; i < cartCount; i++) {
            cartCopy[i] = new Product(cart[i]);
        }

        // Create a new Order with the copied cart
        Order order = new Order(cartCopy, cartCount);
        if (purchaseHistoryCount == purchaseHistory.length) {
            purchaseHistory = Arrays.copyOf(purchaseHistory, purchaseHistory.length * 2);
        }
        purchaseHistory[purchaseHistoryCount++] = order;
        clearCart();*/

        // create a deep copy of the cart.
        ArrayList<Product> cartCopy = new ArrayList<>();
        for (Product product : cart){
            cartCopy.add(product.clone());
        }

        // Create a new Order with the copied cart
        Order order = new Order(cartCopy);
        purchaseHistory.add(order);

        clearCart();

    }

    /**
     * Gets the current cart of the buyer.
     *
     * @return a list of Product objects in the cart
     */
    public ArrayList<Product> getCart() {
        return cart;
    }

    /**
     * Gets the count of items in the cart.
     *
     * @return the number of items in the cart
     */
    public int getCartCount() {
        return cart.size();
    }

    /**
     * Gets the purchase history of the buyer.
     *
     * @return a list of Order objects representing the purchase history
     */
    public ArrayList<Order> getPurchaseHistory() {
        return purchaseHistory;
    }

    /**
     * Returns a string representation of the buyer, including username, address,
     * current cart, and purchase history.
     *
     * @return a string representation of the buyer
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Username: ").append(getUsername()).append("\n")
                .append("Address: ").append(address).append("\n")
                .append("Current Cart:\n");

        for (Product product: cart) {
            sb.append("  - ").append(product.toString()).append("\n");
        }

        sb.append("Purchase History:\n");
        for (Order order: purchaseHistory) {
            sb.append("  - ").append(order).append("\n");
        }

        return sb.toString();
    }

    /**
     * Compares this buyer with another buyer based on the username.
     *
     * @param other the other buyer to compare to
     * @return a negative integer, zero, or a positive integer as this buyer's username
     *         is less than, equal to, or greater than the specified buyer's username
     */
    @Override
    public int compareTo(Buyer other) {
        return getUsername().compareTo(other.getUsername());
    }

}
