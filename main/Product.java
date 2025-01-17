package main;

/**
 * The Product class represents a product with a name and a price.
 */
public class  Product implements Cloneable {
    private static int serialCounter = 1;
    private int serialNumber;
    private String name; // I'll use that. by section 13.
    private int price;
    private ProductCategory category;
    private boolean specialWrapping;

    /**
     * Constructs a new Product with the specified name, price, and category.
     */
    public Product(String name, int price, ProductCategory category, boolean specialWrapping) {
        this.serialNumber = serialCounter++;
        this.name = name;
        this.price = price;
        this.category = category;
        this.specialWrapping = specialWrapping;
    }

    /**
     * Copy constructor to create a new Product with the same properties as another Product.
     *
     * @param product the Product object to copy
     */
    public Product(Product product) {
        this.serialNumber = product.serialNumber;
        this.name = product.name;
        this.price = product.price;
        this.category = product.category;
        this.specialWrapping = product.specialWrapping;
    }

    /**
     * Gets the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the product.
     *
     * @return the price of the product
     */
    public int getPrice() {
        return price;
    }

    /**
     * Gets the category of the product.
     *
     * @return the category of the product
     */
    public ProductCategory getCategory() {
        return category;
    }

    /**
     * Returns a string representation of the product, including its name, price, serial number, category, and special wrapping.
     *
     * @return a string representation of the product
     */
    @Override
    public String toString() {
        return "Serial Number: " + serialNumber + ", Name: " + name + ", Price: $" + price +
                ", Category: " + category.getDisplayName() + ", Special Wrapping: " + (specialWrapping ? "Yes" : "No");
    }

    @Override
    public Product clone() {
        try {
            // Perform a shallow copy of the Product object
            Product clonedProduct = (Product) super.clone();

            // Enum values don't need cloning, they are inherently immutable
            clonedProduct.category = this.category;  // This is a reference copy, safe for enums

            return clonedProduct;
        } catch (CloneNotSupportedException e) {
            // This shouldn't happen because the class implements Cloneable
            throw new AssertionError("Cloning not supported", e);
        }
    }

    public void setPrice(int price) {
        this.price = price;
    }
}


