package factories;

import features.UserInput;
import main.Address;

import java.util.ArrayList;

public class AddressFactory {
    public static ArrayList<Address> createListAddress() {
        ArrayList<Address> list = new ArrayList<>();
        list.add(new Address("Jabalia", 1, "Giza", "Egypt"));
        list.add(new Address("Country Road", 3, "Springfield", "USA"));
        list.add(new Address("Herzl", 2, "Petah Tikva", "Israel"));
        list.add(new Address("Ber", 18, "Munich", "Germany"));
        list.add(new Address("Blue Tooth", 88, "Oslo", "Norway"));
        return list;
    }

    public static Address createAddress() {

        return new Address(UserInput.getStreetName(),
                UserInput.getBuildingNumber(),
                UserInput.getCityName(),
                UserInput.getCountryName());
    }
}
