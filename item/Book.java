package item;

public class Book extends AbstractItem {

    private String _pubYear;
    private String _publisher;

    public Book(String author, String title, String pubYear, String publisher, String acquireDate, String owner,
            String cost, String bookFormat) throws InvalidFormatException {
        super(author, title, acquireDate, owner, cost, bookFormat);

        _pubYear = pubYear;
        _publisher = publisher;

        validInput(bookFormat);

    }

    @Override
    public String getDetails() {
        String template = "%s, '%s'. (%s, %s). [%s, %s, %s, %s]";
        return String.format(template, getCreator(), getTitle(), _pubYear, _publisher, getFormat(), getOwner(),
                getAcquireDate(), getCost());

    }

    @Override
    public String formatReport() {
        String template = "%s: %s, '%s'. (%s)";
        return String.format(template, getOwner(), getCreator(), getTitle(), getFormat());
    }
    
    public void validInput(String bookFormat) throws InvalidFormatException {
        validInput();
        if (!bookFormat.matches("^Hardcover|Paperback$")) {
            throw new InvalidFormatException(
                    "Bookformat is invalid. Please ensure format is 'Hardcover' or 'Paperback'");
        } else if (!_pubYear.matches("^\\d{4}$")) {
            throw new InvalidFormatException("Publish Year format is invalid. Please ensure the format is 4 digits.");
        }
    }

}
