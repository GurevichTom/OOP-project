package assignments;

import main.User;

/* All exact needed imports. */
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.TreeSet;

public class UserNameFeatures {
    private final String entityTypePluralForm;
    private final List<? extends User> list;

    public UserNameFeatures(List<? extends User> list, String entityTypePluralForm) {
        this.entityTypePluralForm = entityTypePluralForm;
        this.list = list;
    }


    // Utility method to check if the list is empty and print a message
    private boolean checkListEmpty() {
        if (list == null || list.isEmpty()) {
            System.out.println("There are no " + entityTypePluralForm + " yet.");
            return true; // Indicates that the list is empty
        }
        return false; // Indicates the list is not empty
    }


    /**
     * Menu option #99
     * print all names
     */
    public void printNames() {
        if (checkListEmpty()) {
            return;
        }

        list.forEach(user -> System.out.println(user.getUsername()));
    }


    /**
     * Menu option #100
     * prints out all names without duplication showing the number of repeated occurrences
     */
    public void printNamesCountedDuplicates() {
        if (checkListEmpty()) {
            return;
        }

        Map<String, Integer> counts = countDuplicatesUsernames();
        counts.forEach((sellerName, sellerNameRepetitions) -> {
            System.out.println(sellerName + " .........." +sellerNameRepetitions);
        });
    }

    /**
     * Menu option #101
     * prints the number of occurrences of a given name in a User List
     * @param name the name to count the occurrences of
     */
    public void printNumOfNameOccurrences(String name) {
        if (checkListEmpty()) {
            return;
        }

        Map<String, Integer> counts = countDuplicatesUsernamesHashMap();
        int occurrences = counts.getOrDefault(name.toLowerCase(), 0);

        System.out.println("The number of times '" + name + "' appears in the original array is " + occurrences);

    }

    /**
     * Menu option #102
     * prints all usernames twice in a backwards order
     */
    public void printUniqueUsernamesTwiceBackwards() {
        if (checkListEmpty())
            return;

        Map<String, Integer> namesMap = countDuplicatesUsernames();
        ArrayList<String> namesList = new ArrayList<>();

        ListIterator<String> it = namesList.listIterator();
        namesMap.forEach((name, value) -> {
            it.add(name);
            it.add(name);
        });

        while (it.hasPrevious()) {
            System.out.println(it.previous());
        }
    }

    /**
     * Menu option #103
     * sorts all users without duplicating usernames and displays them
     */
    public void sortByUsernameAndDisplay() {
        if (checkListEmpty())
            return;

        TreeSet <User> treeSet = new TreeSet<>((u1, u2) -> {
            if (u1.getUsername().equalsIgnoreCase(u2.getUsername()))
                return 0;

            if (u1.getUsername().length() == u2.getUsername().length())
                return 1;

            return u1.getUsername().length() - u2.getUsername().length();

        });

        treeSet.addAll(list);

        treeSet.forEach((user -> System.out.println(user.getUsername().toUpperCase())));
    }

    /**
     * Count duplicated usernames in a User List case-insensitive
     * @return LinkedHashMap of usernames with the number of duplicates counted case-insensitive. Order of insertion preserved
     */
    private LinkedHashMap<String, Integer> countDuplicatesUsernames() {
        LinkedHashMap<String, Integer> counts = new LinkedHashMap<>();
        fillDuplicatesMap(counts);
        return counts;
    }

    /**
     * Made for option #101 because order of insertion doesn't matter. Saving memory
     * Count duplicated usernames in a User List case-insensitive
     * @return HashMap of usernames with the number of duplicates counted case-insensitive.
     */
    private HashMap<String, Integer> countDuplicatesUsernamesHashMap() {
        HashMap<String, Integer> counts = new HashMap<>();
        fillDuplicatesMap(counts);
        return counts;
    }

    /**
     * Fills out a Map with strings as keys and values as their repetition count in list case-insensitive
     * @param map the map to fill
     */
    private void fillDuplicatesMap(Map<String, Integer> map) {
        for (User user : list) {
            String lowerUsername = user.getUsername().toLowerCase();
            map.put(lowerUsername, map.getOrDefault(lowerUsername, 0) + 1);
        }
    }

}
