package item;

/**
 * A class for book items.
 */
public class Book extends AbstractItem {

    private String _pubYear;
    private String _publisher;

    /**
     * Creates a new book item.
     * 
     * @param author      book author
     * @param title       book title
     * @param pubYear     book publication year (4 digit number)
     * @param publisher   book publisher
     * @param acquireDate date book was acquired
     * @param owner       owner of the book
     * @param cost        cost of the book
     * @param bookFormat  format of the book (either Hardcover or Paperback)
     * @throws InvalidFormatException
     */
    public Book(String author, String title, String pubYear, String publisher, String acquireDate, String owner,
            String cost, String bookFormat) throws InvalidFormatException {
        super(author, title, acquireDate, owner, cost, bookFormat);
        _pubYear = pubYear;
        _publisher = publisher;

        validInput(bookFormat, pubYear);
    }

    /**
     * returns a string containing the formatted details of this book
     */
    @Override
    public String getDetails() {
        String template = "%s, '%s'. (%s, %s). [%s, %s, %s, %s]";
        return String.format(template, getCreator(), getTitle(), _pubYear, _publisher, getFormat(), getOwner(),
                getAcquireDate(), getCost());

    }

    /**
     * returns a string containing the formatted deatils of this book for the report
     */
    @Override
    public String formatReport() {
        String template = "%s: %s, '%s'. (%s)";
        return String.format(template, getOwner(), getCreator(), getTitle(), getFormat());
    }

    /**
     * Throws an exception if the checked fields are invalid.
     * 
     * @param bookFormat
     * @throws InvalidFormatException
     */
    public void validInput(String bookFormat, String pubYear) throws InvalidFormatException {
        if (!bookFormat.matches("^(Hardcover)|(Paperback)$")) {
            throw new InvalidFormatException(
                    "Bookformat is invalid. Please ensure format is 'Hardcover' or 'Paperback'");
        } else if (!pubYear.matches("^\\d{4}$")) {
            throw new InvalidFormatException("Publish Year format is invalid. Please ensure the format is 4 digits.");
        }
    }

}
