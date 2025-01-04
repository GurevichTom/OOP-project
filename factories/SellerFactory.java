package factories;

import main.Product;
import main.Seller;
import java.util.*;



/**
 * Class that creates a digital store system with test values
 * <p>
 * follows section 12.
 * */
public class SellerFactory {
    public static ArrayList<Seller> createListSeller() {
        ArrayList<Product> products = ProductFactory.createListProduct();
        Map <String,String> testValuesMap = UserTestData.getTestValuesMap();
        ArrayList<Seller> list = new ArrayList<>();

        int index = 0;
        for (Map.Entry<String, String> entry : testValuesMap.entrySet()) {
            String username = entry.getKey();
            String password = entry.getValue();
            Seller seller = new Seller(username, password);

            Product product = products.get(index++ % products.size());
            seller.addProduct(product);

            list.add(seller);
        }
        return list;
    }
}