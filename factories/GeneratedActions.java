package factories;

import main.Manager;
import main.Product;
import main.ProductCategory;
import main.Seller;

public class GeneratedActions {

    private static Product[] sampleProducts = {
            new Product("pen", 20, ProductCategory.OFFICE, false),
            new Product("phone", 200, ProductCategory.ELECTRONICS, true),
            new Product("book", 70, ProductCategory.KIDS, true),
            new Product("stapler", 20, ProductCategory.OFFICE, false),
            new Product("hole puncher", 40, ProductCategory.OFFICE, false)
    };

    public static void addSampleItemsToSellers(Manager manager) {


        // Silly insertion (so the list has a Seller with more than one product).
        int indexSellerWith2 = 3;
        manager.getSellers().get(indexSellerWith2).addProduct(sampleProducts[0]);

        int i = 1;
        for(Seller seller : manager.getSellers()){
            if (i < 5)
                seller.addProduct(sampleProducts[i++]);
            else
                break;
        }

        System.out.println("Added Some Items to Sellers.");
    }

    public static void payForOrder(Manager manager){

        String result1 = manager.processPayment(manager.getBuyerByIndex(1));
        System.out.println(result1);

        String result2 = manager.processPayment(manager.getBuyerByIndex(2));
        System.out.println(result2);
    }

    public static void addSampleItemsToBuyer(Manager manager) {
        purchaseItemsByBuyerAndProductIndexes(manager, 3, 0);
        purchaseItemsByBuyerAndProductIndexes(manager, 1, 1);
        purchaseItemsByBuyerAndProductIndexes(manager, 4, 2);
    }
    private static void purchaseItemsByBuyerAndProductIndexes(Manager manager, int buyerInd, int productInd){
        manager.getBuyerByIndex(buyerInd).addToCart(sampleProducts[productInd]);
    }
}
