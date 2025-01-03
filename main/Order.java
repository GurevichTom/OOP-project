package main;

import java.util.ArrayList;
import java.util.Date;

/**
 * The Order class represents a completed purchase order containing a list of products,
 * the number of products, and the purchase date.
 */
public class Order {

    private ArrayList<Product> products;
    private Date purchaseDate;

    public ArrayList<Product> getProducts() {
        return products;
    }



    /**
     * Constructs a new Order with the specified products.
     *
     * @param products the array of Product objects in the order
     */
    public Order(ArrayList<Product> products) {
        this.products = products;
        this.purchaseDate = new Date();
    }

    /**
     * Returns a string representation of the order, including the purchase date and items.
     *
     * @return a string representation of the order
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Purchase date: ").append(purchaseDate).append("\n")
                .append("Items:\n");

        for (Product product :  this.products) {
            sb.append("    * ").append(product.toString()).append("\n");
        }

        return sb.toString();
    }

}
