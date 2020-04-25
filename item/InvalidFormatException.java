package item;

/**
 * Thrown when a provided argument is of an invalid format.
 */
public class InvalidFormatException extends Exception {
    public InvalidFormatException(String errorMessage) {
        super(errorMessage);
    }
}