package factories;

import features.UserInput;
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

    public static Product createProduct() {
        /*Product temp  = new Product(UserInput.getProductName(),
                UserInput.getPrice(),
                UserInput.getProductCategory(),
                UserInput.existsSpecialWrapping());*/

        String productName = UserInput.getProductName();
        // that's the problem.
        int productPrice = UserInput.getPrice();
        ProductCategory productCategory = UserInput.getProductCategory();
        boolean existsSpecialWrapping = UserInput.existsSpecialWrapping();
        productPrice = (existsSpecialWrapping) ? productPrice += UserInput.getAdditionalPrice() : productPrice;

        return new Product(productName, productPrice, productCategory, existsSpecialWrapping);
    }
}
