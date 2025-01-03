package factories;

import main.Seller;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Class that creates the sellers of digital store system with test values
 * <p>
 * follows section 12.
 * */
public class SampleSellers implements ListCreator {


    public ArrayList<Seller> createList() {
        // So, it's so easy to compare.
        LinkedHashMap<String, String> testValuesMap = new LinkedHashMap<>();
        testValuesMap.put("Mo", "5");
        testValuesMap.put("hooi", "3");
        testValuesMap.put("bro", "8");
        testValuesMap.put("taMi", "4");
        testValuesMap.put("BuG", "2000");
        testValuesMap.put("BUG", "2009");

        ArrayList<Seller> list = new ArrayList<>();
        testValuesMap.forEach((username, password) -> {
            list.add(new Seller(username, password));
        });
        return list;
    }
}
