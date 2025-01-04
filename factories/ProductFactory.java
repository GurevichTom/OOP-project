package factories;

import main.Product;
import main.ProductCategory;

import java.util.ArrayList;

public class ProductFactory {
    public static ArrayList<Product> createListProduct() {
        ArrayList <Product> list = new ArrayList<>();
        list.add(new Product("pen", 20, ProductCategory.OFFICE, false));
        list.add(new Product("phone", 200, ProductCategory.ELECTRONICS, true));
        list.add(new Product("book", 70, ProductCategory.KIDS, true));
        list.add(new Product("stapler", 20, ProductCategory.OFFICE, false));
        list.add(new Product("hole puncher", 40, ProductCategory.OFFICE, false));
        return list;
    }
}
