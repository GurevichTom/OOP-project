package main;

import java.util.ArrayList;

/**
 * The Seller class represents a seller in the system. It contains the seller's username,
 * password, and a list of products they are selling.
 */
public class Seller extends User implements Comparable<Seller> {
    private final ArrayList<Product> products;

    /**
     * Constructs a new Seller with the specified username and password.
     *
     * @param username the username of the seller
     * @param password the password of the seller
     */
    public Seller(String username, String password) {
        super(username, password);
        this.products = new ArrayList<>();
    }

    /**
     * Adds a product to the seller's list of products.
     *
     * @param product the product to be added
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Gets the products sold by the seller.
     *
     * @return a list of Product objects sold by the seller
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * Returns a string representation of the seller, including username and products.
     *
     * @return a string representation of the seller
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Username: ").append(getUsername()).append("\n")
                .append("Password: ").append(getPassword()).append("\n")
                .append("Products:\n");

        for (Product product : products) {
            sb.append("  - ").append(product.toString()).append("\n");
        }

        return sb.toString();
    }

    /**
     * Compares this seller with another seller based on the number of products they sell.
     *
     * @param other the other seller to compare to
     * @return a negative integer, zero, or a positive integer as this seller sells
     *         fewer than, equal to, or more products than the specified seller
     */
    @Override
    public int compareTo(Seller other) {
        return Integer.compare(other.products.size(), this.products.size());
    }
}
