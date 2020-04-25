package item;

/**
 * A class representing any item that can be added to the flat collection.
 */
public abstract class AbstractItem {

    private String _creator;
    private String _title;
    private CustomDate _acquireDate;
    private String _owner;
    private String _cost;
    private String _format;

    /**
     * instantiates fields for objects in the item class.
     * 
     * @param creator     item creator
     * @param title       title of item
     * @param acquireDate date the item was acquired
     * @param owner       owner of the item
     * @param cost        cost of the item
     * @param type        format of the item
     * @throws InvalidFormatException
     */
    public AbstractItem(String creator, String title, String acquireDate, String owner, String cost, String type)
            throws InvalidFormatException {
        _creator = creator;
        _title = title;
        _acquireDate = new CustomDate(acquireDate);
        _owner = owner;
        _cost = cost;
        _format = type;

    }

    /**
     * returns if the object was acquired in the given year
     * 
     * @param year the query year
     * @return boolean of whether the item was acquired in the year.
     */
    public boolean checkIfAcquiredInYear(String year) {
        return _acquireDate.getYear().equals(year);
    }

    /**
     * The id of an item is its a concatenation of its creator, title and format.
     * 
     * @return the id of this item.
     */
    public String getId() {
        return _creator + _title + _format;
    }

    public abstract String formatReport();

    public abstract String getDetails();

    /**
     * Gets the creator of this item
     * 
     * @return the items creator
     */
    public String getCreator() {
        return this._creator;
    }

    // Protected methods for accessing private members of this class from its
    // subclasses. Intended for use within the item package, for displaying
    // information and sorting.

    protected String getTitle() {
        return this._title;
    }

    protected String getAcquireDate() {
        return this._acquireDate.stringifyDate();
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