package features;

import main.Manager;
import main.ManagerSystemOutput;


public class StoreFacade {
    /**
     * We decided to use Singleton because We're willing to ensure there is only one of that object.
     * <p> NO NEED IN ANOTHER OBJECT OF IT!</p>
     * */
    private static StoreFacade instance;
    private final Manager manager;
    private final UserNameFeatures uf;

    private StoreFacade() {
        manager = new Manager();
        uf = new UserNameFeatures(manager.getSellers(), "Sellers");
    }

    public static StoreFacade getInstance() {
        if (instance == null) {
            instance = new StoreFacade();
        }
        return instance;
    }


    public void run() {
        int choice;

        do {
            choice = ManagerSystemOutput.displayMainMenu(this.getAutomatedEntityTypePluralForm(), manager.hasBuyers(), manager.hasSellers());
            runFunction(choice);
        } while (choice != 0);

        UserInput.end();
    }

    private void addUser(String userType) {
        boolean added = false;

        while (!added) {
            added = switch (userType) {
                case "seller" -> manager.addSeller();
                case "buyer" -> manager.addBuyer();
                default -> throw new IllegalArgumentException("Unknown user type: " + userType);
            };

            if (!added) {
                System.out.println("Error adding a new " + userType + ". Username is already taken\nTry again");
            }
        }

        System.out.println(userType + " successfully added!");
    }


    /**
     * Executes the function corresponding to the user's choice.
     *
     * @param choice the user's choice from the main menu
     */
    private void runFunction(int choice) {


        switch (choice) {
            case 0 -> System.out.println("Exiting the menu.");
            case 1 -> addUser("seller");
            case 2 -> addUser("buyer");
            case 3 -> manager.addItemToSeller();
            case 4 -> manager.addItemToBuyerCart();
            case 5 -> manager.payForOrder();
            case 6 -> manager.displayAllBuyerDetails();
            case 7 -> manager.displayAllSellerDetails();
            case 8 -> manager.displayProductsByCategory();
            case 9 -> manager.generateNewShoppingCartFromHistory();
            // Course 10119:
            case 99 -> uf.printNames();
            case 100 -> uf.printNamesCountedDuplicates();
            case 101 -> uf.printNumOfNameOccurrences();
            case 102 -> uf.printUniqueUsernamesTwiceBackwards();
            case 103 -> uf.sortByUsernameAndDisplay();
            case -1 -> manager.createTestSystem();
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private String getAutomatedEntityTypePluralForm () {
        return this.uf.getEntityTypePluralForm();
    }
}
