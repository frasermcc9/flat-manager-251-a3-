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

        if (!validDate())
            throw new InvalidFormatException("Date is not a valid format. Please use YYYY-MM-DD");
        if (!validCost())
            throw new InvalidFormatException("Cost is not a valid format. Please use $Dollars.Cents");

    }

    public boolean checkIfAcquiredInYear(String year) {
        return _acquireDate.substring(0, 4).equals(year);
    }

    public String findCreator() {
        return this._creator;
    }

    public Integer getId() {
        Integer id = getId(_creator, _title, _format);
        return id;
    }

    public static Integer getId(String creator, String title, String format) {
        return (creator + title + format).hashCode();
    }

    private boolean validDate() {
        return _acquireDate.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    private boolean validCost() {
        return _cost.matches("^[$]\\d+.\\d{2}$");
    }

    public abstract String getDetails();

    public abstract String formatReport();

}