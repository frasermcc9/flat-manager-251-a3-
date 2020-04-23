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

    public AbstractItem findByIdString(String id) throws NoSuchElementException {
        if (_items.get(id) == null) {
            throw new NoSuchElementException();
        }
        return _items.get(id);
    }

    public AbstractItem findByProperties(String creator, String title, String formatStr) throws NoSuchElementException {
        String id = AbstractItem.getId(creator, title, formatStr);
        return findByIdString(id);
    }

    public List<String> getItemDetailsByOrder(String orderKey) throws IllegalArgumentException {
        return getDetailsList(getItemsByOrder(orderKey));
    }

    public List<String> getItemDetailsAcquiredIn(String year) {
        return getDetailsList(getItemsAcquiredInYear(year));
    }

    public List<String> getCreators() {
        return new ArrayList<String>(getCreatorSet());
    }

    public List<String> inventoryReport(String flatName) {
        List<AbstractItem> items = new ArrayList<AbstractItem>(_items.values());
        Collections.sort(items, ItemComparator.orderSort(ItemComparator.Owner, ItemComparator.Format,
                ItemComparator.Creator, ItemComparator.Title));
        return getDetailsList(items, flatName);
    }

    private Set<String> getCreatorSet() {
        Set<String> creators = new HashSet<String>();
        for (AbstractItem a : new ArrayList<AbstractItem>(_items.values()))
            creators.add(a.getCreator());
        return creators;
    }

    private List<AbstractItem> getItemsAcquiredInYear(String year) {
        List<AbstractItem> items = new ArrayList<AbstractItem>();
        for (AbstractItem i : new ArrayList<AbstractItem>(_items.values())) {
            if (i.checkIfAcquiredInYear(year)) {
                items.add(i);
            }
        }
        return items;
    }

    private List<AbstractItem> getItemsByOrder(String orderKey) throws IllegalArgumentException {
        List<AbstractItem> items = new ArrayList<AbstractItem>(_items.values());
        Collections.sort(items, ItemComparator.valueOf(orderKey));
        return items;
    }

    private List<String> getDetailsList(List<AbstractItem> originaList) {
        List<String> output = new ArrayList<String>();
        for (AbstractItem i : originaList) {
            output.add(i.getDetails());
        }
        return output;
    }

    private List<String> getDetailsList(List<AbstractItem> originaList, String flatName) {
        List<String> output = new ArrayList<String>();
        output.add(flatName);
        for (AbstractItem i : originaList) {
            output.add(i.formatReport());
        }
        return output;
    }
}