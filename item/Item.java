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

    public Integer getId() {
        Integer id = getId(_creator, _title, _format);
        return id;
    }

    public void validInput() throws InvalidFormatException {
        if (!_acquireDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new InvalidFormatException("Date is not a valid format. Please use YYYY-MM-DD");
        } else if (!_cost.matches("^[$]\\d+.\\d{2}$")) {
            throw new InvalidFormatException("Cost is not a valid format. Please use $Dollars.Cents");
        }
    }

    

    public static Integer getId(String creator, String title, String format) {
        return (creator + title + format).hashCode();
    }

    public String formatReport() {
        String template = "%s: %s, '%s'. (%s)";
        return String.format(template, _owner, _creator, _title, _format);
    }

    public abstract String getDetails();

}