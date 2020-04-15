package item;

public class Book extends Item {

    private String _pubYear;
    private String _publisher;

    public Book(String author, String title, String pubYear, String publisher, String acquireDate, String owner,
            String cost, String bookFormat) throws InvalidFormatException {
        super(author, title, acquireDate, owner, cost, bookFormat);

        _pubYear = pubYear;
        _publisher = publisher;

    }

    @Override
    public String getDetails() {
        String template = "%s, '%s'. (%s, %s). [%s, %s, %s, %s]";
        return String.format(template, _creator, _title, _pubYear, _publisher, _format, _owner, _acquireDate, _cost);

    }

    @Override
    public String formatReport() {
        String template = "%s: %s, '%s'. (%s)";
        return String.format(template, _owner, _creator, _title, _format);
    }

}
