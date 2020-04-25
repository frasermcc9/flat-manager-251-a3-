package inventoryman;

/**
 * Thrown when a duplicate item is added to the collection
 */
public class DuplicateItemException extends Exception {
    public DuplicateItemException(String errorMessage) {
        super(errorMessage);
    }
}