package factories;

import java.util.Map;

public class UserTestData {
    public static Map<String, String> getTestValuesMap() {
        return Map.of(
                "Mo", "5",
                "hooi", "3",
                "bro", "8",
                "taMi", "4",
                "BuG", "2000",
                "BUG", "2009"
        );
    }
}
