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
        String id = getId(creator, title, formatStr);
        return findByIdString(id);
    }

    /**
     * returns a list of item objects in the collection sorted by the given key
     * 
     * @param orderKey the key to order by
     * @return a list of item objects ordered
     * @throws IllegalArgumentException if an invalid key is given
     */
    public List<AbstractItem> getItemsByOrder(String orderKey) throws IllegalArgumentException {
        List<AbstractItem> items = new ArrayList<AbstractItem>(_items.values());
        Collections.sort(items, ItemComparator.valueOf(orderKey));
        return items;
    }

    /**
     * returns a list of item objects acquired in a given year
     * 
     * @param year the year to filter by
     * @return a list of item objects aqcuired in the given year
     */
    public List<AbstractItem> getItemsAcquiredInYear(String year) {
        List<AbstractItem> items = new ArrayList<AbstractItem>();
        for (AbstractItem i : new ArrayList<AbstractItem>(_items.values())) {
            if (i.checkIfAcquiredInYear(year)) {
                items.add(i);
            }
        }
        return items;
    }

    /**
     * returns all creators in the collection as a set
     * 
     * @return a set of all creators
     */
    public Set<String> getCreatorSet() {
        Set<String> creators = new HashSet<String>();
        for (AbstractItem a : new ArrayList<AbstractItem>(_items.values()))
            creators.add(a.getCreator());
        return creators;
    }

    /**
     * gets a report of all items in the collection
     * 
     * @param flatName the name of the flat (for report heading)
     * @return list of strings containing the report
     */
    public List<AbstractItem> inventoryReport() {
        List<AbstractItem> items = new ArrayList<AbstractItem>(_items.values());
        Collections.sort(items, ItemComparator.orderSort(ItemComparator.Owner, ItemComparator.Format,
                ItemComparator.Creator, ItemComparator.Title));
        return items;
    }

    /**
     * Static method that gets the id of the given parameters. The system does not
     * allow duplicates (defined as same creator, title and format) so this id must
     * be unique.
     * 
     * @param creator item creator
     * @param title   item title
     * @param format  item format
     * @return the item of this id.
     */
    private static String getId(String creator, String title, String format) {
        return creator + title + format;
    }
}