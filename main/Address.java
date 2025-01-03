package main;

/**
 * The Address class represents a buyer's address with street name, building number, city, and country.
 */
public class Address {
    private String streetName;
    private int buildingNumber;
    private String city;
    private String country;

    /**
     * Constructs a new Address with the specified street name, building number, city, and country.
     *
     * @param streetName the name of the street
     * @param buildingNumber the number of the building
     * @param city the city of the address
     * @param country the country of the address
     */
    public Address(String streetName, int buildingNumber, String city, String country) {
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.country = country;
    }

    /**
     * Returns a string representation of the address.
     *
     * @return a string representation of the address
     */
    @Override
    public String toString() {
        return streetName + " " + buildingNumber + ", " + city + ", " + country;
    }
}
