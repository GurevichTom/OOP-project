package factories;

import main.User;

import java.util.ArrayList;

/**
 * Class that responsible to create the list for the Sellers/Buyers.
 */
public class EntitiesFactory {

    public static ArrayList<? extends User> createEntitiesList(String entitiesName){
        if(entitiesName.equalsIgnoreCase("Sellers")) {
            return new SampleSellers().createList();
        }
        if(entitiesName.equalsIgnoreCase("Buyers")) {
            return new SampleBuyers().createList();
        }

        throw new IllegalArgumentException("Invalid Type");
    }
}
