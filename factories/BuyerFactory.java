package factories;

import features.UserInput;
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

    /**
     * Adds a new buyer after prompting for username, password, and address.
     */
    public static Buyer createBuyer() {
        /*
        String username = UserInputFeatures.getUniqueUsername("Buyer");
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter street name: ");
        String streetName = scanner.nextLine();
        int buildingNumber = getValidIntegerInput("Enter building number: ");
        String city = getValidNonNumericStringInput("Enter city: ");
        String country = getValidNonNumericStringInput("Enter country: ");
        //

        Address address = new Address(streetName, buildingNumber, city, country);
        Buyer buyer = new Buyer(username, password, address);

        if (manager.addBuyer(buyer)) {
            System.out.println("Buyer added successfully.");
        } else {
            System.out.println("Error adding buyer. Please try again.");
        }

        return buyer;*/

        return new Buyer(UserInput.getUsername("Buyer"),
                UserInput.getPassword(),
                UserInput.getAddress());
    }


}
