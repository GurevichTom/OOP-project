// * ********************************************************
// * *                                                      *
// * *                Project by:                           *
// * *                Tom Gurevich                          *
// * *                Omer Asayag                           *
// * *                                                      *
// * *                IDs:                                  *
// * *                Tom Gurevich - 326531167              *
// * *                Omer Asayag  - 215487174              *
// * *                                                      *
// * *                Lecturers:                            *
// * *                Tom Gurevich - Aranon Barkat          *
// * *                Omer Asayag  - Aranon Barkat          *
// * *                                                      *
// * ********************************************************
package main;


import features.StoreFacade;

public class Main {
    /**
     * Main class to handle a simple buyer-seller system.
     * <p>
     * Created by:
     * Tom Gurevich &
     * Omer Asayag.
     * </p>
     */

    public static void main(String[] args) {
        StoreFacade.getInstance().run();
    }

}
