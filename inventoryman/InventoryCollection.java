package inventoryman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import item.AbstractItem;
import item.ItemComparator;

public class InventoryCollection {

    private Map<String, AbstractItem> _items = new HashMap<String, AbstractItem>();

    /**
     * Adds a child of abstractitem to the collection.
     * 
     * @param item object that extends abstract item to add to the collection.
     * @return string containing "Success".
     * @throws DuplicateItemException if the item is already in the collection.
     */
    public String itemHandler(AbstractItem item) throws DuplicateItemException {
        if (_items.containsKey(item.getId())) {
            throw new DuplicateItemException("Duplicate item added to inventory:" + item.getId());
        }
        _items.put(item.getId(), item);
        return "Success";
    }

    /**
     * returns the item from the provided id
     * 
     * @param id the id of the object
     * @return the item that the id represented
     * @throws NoSuchElementException if there is no item stored with the given id
     */
    public AbstractItem findByIdString(String id) throws NoSuchElementException {
        if (_items.get(id) == null) {
            throw new NoSuchElementException();
        }
        return _items.get(id);
    }

    /**
     * returns the item from the provided item properties.
     * 
     * @param creator   creator of item
     * @param title     title of item
     * @param formatStr format of item
     * @return the item with these properties
     * @throws NoSuchElementException if no item is found
     */
    public AbstractItem findByProperties(String creator, String title, String formatStr) throws NoSuchElementException {
        String id = AbstractItem.getId(creator, title, formatStr);
        return findByIdString(id);
    }

    /**
     * Gets all items in the collection in the given order
     * 
     * @param orderKey the key to order by
     * @return a list of string representations of each item ordered by key.
     * @throws IllegalArgumentException if an invalid key is given
     */
    public List<String> getItemDetailsByOrder(String orderKey) throws IllegalArgumentException {
        return getDetailsList(getItemsByOrder(orderKey));
    }

    /**
     * gets all items in the collection acquired in a given year
     * 
     * @param year the year to search
     * @return a list of string representations of each item in given year
     */
    public List<String> getItemDetailsAcquiredInYear(String year) {
        return getDetailsList(getItemsAcquiredInYear(year));
    }

    /**
     * gets a list containing every creator in the collection
     * 
     * @return string list of each creator featured in the collection
     */
    public List<String> getCreators() {
        return new ArrayList<String>(getCreatorSet());
    }

    /**
     * gets a report of all items in the collection
     * 
     * @param flatName the name of the flat (for report heading)
     * @return list of strings containing the report
     */
    public List<String> inventoryReport(String flatName) {
        List<AbstractItem> items = new ArrayList<AbstractItem>(_items.values());
        Collections.sort(items, ItemComparator.orderSort(ItemComparator.Owner, ItemComparator.Format,
                ItemComparator.Creator, ItemComparator.Title));
        return getDetailsList(items, flatName);
    }

    /**
     * returns all creators in the collection as a set
     * 
     * @return a set of all creators
     */
    private Set<String> getCreatorSet() {
        Set<String> creators = new HashSet<String>();
        for (AbstractItem a : new ArrayList<AbstractItem>(_items.values()))
            creators.add(a.getCreator());
        return creators;
    }

    /**
     * returns a list of item objects acquired in a given year
     * 
     * @param year the year to filter by
     * @return a list of item objects aqcuired in the given year
     */
    private List<AbstractItem> getItemsAcquiredInYear(String year) {
        List<AbstractItem> items = new ArrayList<AbstractItem>();
        for (AbstractItem i : new ArrayList<AbstractItem>(_items.values())) {
            if (i.checkIfAcquiredInYear(year)) {
                items.add(i);
            }
        }
        return items;
    }

    /**
     * returns a list of item objects in the collection sorted by the given key
     * 
     * @param orderKey the key to order by
     * @return a list of item objects ordered
     * @throws IllegalArgumentException if an invalid key is given
     */
    private List<AbstractItem> getItemsByOrder(String orderKey) throws IllegalArgumentException {
        List<AbstractItem> items = new ArrayList<AbstractItem>(_items.values());
        Collections.sort(items, ItemComparator.valueOf(orderKey));
        return items;
    }

    /**
     * turns a list of item objects into a list of string representations of each
     * object, with format {@link item.AbstractItem#getDetails()} (see
     * implementations of method)
     * 
     * @param originaList the list of item objects to stringify
     * @return the list of string representations for the object list
     */
    private List<String> getDetailsList(List<AbstractItem> originaList) {
        List<String> output = new ArrayList<String>();
        for (AbstractItem i : originaList) {
            output.add(i.getDetails());
        }
        return output;
    }

    /**
     * Overload of getDetailsList which also accepts a flat name. If provided, this
     * will adjust the string representations to the report format, see
     * implementations of {@link item.AbstractItem#formatReport()}.
     * 
     * @param originaList the list of item objects to stringify
     * @param flatName    the name of the flat (for the header)
     * @return the list of string representations of the object list in report
     *         format, with the flatname heading.
     */
    private List<String> getDetailsList(List<AbstractItem> originaList, String flatName) {
        List<String> output = new ArrayList<String>();
        output.add(flatName);
        for (AbstractItem i : originaList) {
            output.add(i.formatReport());
        }
        return output;
    }
}