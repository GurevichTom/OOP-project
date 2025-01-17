package features;

import main.Manager;

public class MainActionsFacade {

    private final Manager manager;
    private final UserNameFeatures uf;

    public MainActionsFacade(String pluralEntityTypeToAutomated) {
        this.manager = new Manager();
        this.uf = new UserNameFeatures(manager.getSellers(), pluralEntityTypeToAutomated);
    }



    public void run() {
        int choice;

        do {
            choice = SystemOutput.displayMainMenu(this.getAutomatedEntityTypePluralForm(), manager);
            runFunction(choice);
        } while (choice != 0);

        UserInput.end();
    }


    /**
     * Executes the function corresponding to the user's choice.
     *
     * @param choice the user's choice from the main menu
     */
    public void runFunction(int choice) {


        switch (choice) {
            case 0 -> System.out.println("Exiting the menu.");
            case 1 -> manager.addSeller();
            case 2 -> manager.addBuyer();
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
