package main;

/**
 * Custom exception thrown when an operation is attempted on an empty cart.
 */
public class EmptyCartException extends Exception {
    public EmptyCartException(String message) {
        super(message);
    }
}
