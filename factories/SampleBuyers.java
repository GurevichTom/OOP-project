package factories;

import main.Address;
import main.Buyer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



/**
 * Class that creates the sellers of digital store system with test values
 * <p>
 * follows section 12.
 * */
public class SampleBuyers implements ListCreator {
    private static final ArrayList<Address> addresses = new ArrayList<>();


    public ArrayList<Buyer> createList() {
        LinkedHashMap<String,String> testValuesMap = new LinkedHashMap<>();
        testValuesMap.put("Ali", "45");
        testValuesMap.put("Hobby", "30");
        testValuesMap.put("Bruh", "88");
        testValuesMap.put("tAMi", "49");
        testValuesMap.put("BuGgy", "3000");

        initislizeList();

        ArrayList<Buyer> list = new ArrayList<>();
        int i = 0;
        for (Map.Entry<String, String> entry : testValuesMap.entrySet()) {
            String username = entry.getKey();
            String password = entry.getValue();
            list.add(new Buyer(username, password, addresses.get(i++)));
        }

        return list;
    }

    private static void initislizeList(){
        List<Address> tempAddresses = List.of(
                new Address("Jabalia", 1, "Giza", "Egypt"),
                new Address("Country Road", 3, "Springfield", "USA"),
                new Address("Herzl", 2, "Petah Tikva", "Israel"),
                new Address("Ber", 18, "Munich", "Germany"),
                new Address("Blue Tooth", 88, "Oslo", "Norway")
        );
        addresses.addAll(tempAddresses);
    }

}