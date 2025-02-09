package features;

import main.User;

/* All exact needed imports. */
import java.util.*;

public class UserNameFeatures {
    private final String entityTypePluralForm;
    private final List<? extends User> list;

    private ArrayList<String> namesList;

    public UserNameFeatures(List<? extends User> list, String entityTypePluralForm) {
        this.entityTypePluralForm = entityTypePluralForm;
        this.list = list;
        namesList = new ArrayList<>();
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
     */
    public void printNumOfNameOccurrences() {
        String name = UserInput.getValidNonNumericStringInput("Please enter the name of the seller:");

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

        namesList = new ArrayList<>();
        Map<String, Integer> namesMap = countDuplicatesUsernames();

//        ListIterator<String> it = namesList.listIterator();
        IteratorAdapter<String> it = new IteratorAdapter<>(namesList.listIterator());
        namesMap.forEach((name, _) -> {
            it.myAdd(name);
            it.myAdd(name);
        });

        while (it.myHasPrevious()) {
            System.out.println(it.myPrevious());
        }

        System.out.println("Do you want to see the output of my self-implemented iterators (Y/y or any other key to skip):");
        String ans = UserInput.getStringInput();
        if (ans.equalsIgnoreCase("y")) {
            demoCustomIterator();
        }
//        namesList.clear();
    }

    private void demoCustomIterator(){
        System.out.println("1) Custom iterator forward");
        MyIterator it = new MyIterator();
        it.attach(new Action1());
        it.attach(new Action2());

        while (it.hasNext())
            System.out.println(it.next());

        System.out.println("2) Custom list iterator forward");
        MyListIterator listIt = new MyListIterator();
        listIt.attach(new Action1());
        listIt.attach(new Action2());

        while (listIt.hasNext())
            System.out.println(listIt.next());

        System.out.println("3) Custom list iterator backwards");
        while (listIt.hasPrevious())
            System.out.println(listIt.previous());
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

    public String getEntityTypePluralForm() {
        return entityTypePluralForm;
    }


    private class MyIterator implements Iterator<String> {
        private final Set<IteratorObserver> observers = new HashSet<>();
        int cur = 0;

        @Override
        public boolean hasNext() {
            return cur < namesList.size();
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            String ret = namesList.get(cur++);

            if (!hasNext())
                update();

            return ret;
        }

        public void attach(IteratorObserver observer) {
            observers.add(observer);
        }

        public void detach(IteratorObserver observer) {
            observers.remove(observer);
        }

        public void update() {
            observers.forEach(o -> o.onIterationEnd("My " + getClass().getSimpleName() + " ended!"));
        }
    }

    private class MyListIterator extends MyIterator implements ListIterator<String> {

        @Override
        public boolean hasPrevious() {
            return cur > 0;
        }

        @Override
        public String previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            String ret = namesList.get(--cur);

            if (!hasPrevious())
                update();

            return ret;
        }

        @Override
        public int nextIndex() {
            return cur;
        }

        @Override
        public int previousIndex() {
            return cur - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(String s) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(String s) {
            throw new UnsupportedOperationException();
        }
    }


    /**
     * Option 104
     * Memento design pattern
     */
    public static class Memento {
        private final ArrayList<String> namesList;
        private Memento(ArrayList<String> namesList) {
            this.namesList = new ArrayList<>(namesList);
        }
    }

    public Memento createMemento() {
        return new Memento(namesList);
    }

    /**
     * Option 105
     * @param m the memento
     */
    public void setMemento(Memento m) {
        namesList = new ArrayList<>(m.namesList);
    }
}
