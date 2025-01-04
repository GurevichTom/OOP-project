package factories;

import main.Address;
import main.Buyer;
import main.Order;
import main.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuyerFactory {
    public static ArrayList<Buyer> createListBuyer() {
        ArrayList<Address> addresses = AddressFactory.createListAddress();
        ArrayList<Product> products = ProductFactory.createListProduct();
        Map<String, String> testValuesMap = UserTestData.getTestValuesMap();
        ArrayList<Buyer> buyers = new ArrayList<>();
        int index = 0; // To cycle through the lists

        for (Map.Entry<String, String> entry : testValuesMap.entrySet()) {
            String username = entry.getKey();
            String password = entry.getValue();

            // Get an address from the list (cycled if out of bounds)
            Address address = addresses.get(index % addresses.size());
            index++;

            Buyer buyer = new Buyer(username, password, address);

            Product product = products.get(index % products.size());
            buyer.addToCart(product);

            List<Product> purchasedProducts = products.subList(0, (index % products.size()) + 1);
            Order historyPurchase = new Order(new ArrayList<>(purchasedProducts));
            buyer.getPurchaseHistory().add(historyPurchase);

            buyers.add(buyer);
        }

        return buyers;
    }
}
