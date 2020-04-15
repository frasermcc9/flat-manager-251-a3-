package inventoryman;

public class DuplicateItemException extends Exception {
    public DuplicateItemException(String errorMessage){
        super(errorMessage);
    }
}