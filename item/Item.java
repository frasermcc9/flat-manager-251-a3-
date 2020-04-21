package item;

public abstract class Item {

    private String _creator;
    private String _title;
    private String _acquireDate;
    private String _owner;
    private String _cost;
    private String _format;

    public Item(String creator, String title, String acquireDate, String owner, String cost, String type)
            throws InvalidFormatException {
        _creator = creator;
        _title = title;
        _acquireDate = acquireDate;
        _owner = owner;
        _cost = cost;
        _format = type;

    }

    public boolean checkIfAcquiredInYear(String year) {
        return _acquireDate.substring(0, 4).equals(year);
    }

    public String findCreator() {
        return this._creator;
    }

    public String getId() {
        String id = getId(_creator, _title, _format);
        return id;
    }

    public void validInput() throws InvalidFormatException {
        if (!_acquireDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new InvalidFormatException("Date is not a valid format. Please use YYYY-MM-DD");
        } else if (!_cost.matches("^[$]\\d+.\\d{2}$")) {
            throw new InvalidFormatException("Cost is not a valid format. Please use $Dollars.Cents");
        }
    }

    public static String getId(String creator, String title, String format) {
        return (creator + title + format);
    }

    public abstract String formatReport();

    public abstract String getDetails();

    protected String getCreator() {
        return this._creator;
    }

    protected String getTitle() {
        return this._title;
    }

    protected String getAcquireDate() {
        return this._acquireDate;
    }

    protected String getOwner() {
        return this._owner;
    }

    protected String getCost() {
        return this._cost;
    }

    protected String getFormat() {
        return this._format;
    }

}