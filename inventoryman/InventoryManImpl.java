package inventoryman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import item.AbstractItem;
import item.Book;
import item.ItemComparator;
import item.Music;

public class InventoryManImpl implements InventoryMan {

    private String _flatName;
    private Map<String, AbstractItem> _items = new HashMap<String, AbstractItem>();

    public InventoryManImpl(String flatName) {
        _flatName = flatName;
    }

    @Override
    public String addBook(String author, String title, String publicationYear, String publisher,
            String acquisitionDateStr, String owner, String costStr, String formatStr) {
        try {
            Book newBook = new Book(author, title, publicationYear, publisher, acquisitionDateStr, owner, costStr,
                    formatStr);

            if (_items.containsKey(newBook.getId()))
                throw new DuplicateItemException("Duplicate item added to inventory:" + newBook.getId());

            _items.put(newBook.getId(), newBook);

            return "Success";

        } catch (Exception e) {
            return "ERROR " + e.getMessage();
        }
    }

    @Override
    public String addMusic(String artist, String title, String releaseDateStr, String acquisitionDateStr, String owner,
            String costStr, String formatStr) {
        try {
            Music newMusic = new Music(artist, title, releaseDateStr, acquisitionDateStr, owner, costStr, formatStr);

            if (_items.containsKey(newMusic.getId()))
                throw new DuplicateItemException("Duplicate item added to inventory:" + newMusic.getId());

            _items.put(newMusic.getId(), newMusic);

            return "Success";
        } catch (Exception e) {
            return "ERROR " + e.getMessage();
        }
    }

    @Override
    public String getItemToDisplay(String creator, String title, String formatStr) {
        String id = AbstractItem.getId(creator, title, formatStr);
        AbstractItem retrievedItem = _items.get(id);
        return retrievedItem.getDetails();
    }

    @Override
    public List<String> getAll(String order) throws IllegalArgumentException {
        List<AbstractItem> items = new ArrayList<AbstractItem>(_items.values());
        ItemComparator key = ItemComparator.valueOf(order);

        Collections.sort(items, key);

        List<String> output = new ArrayList<String>();
        for (AbstractItem a : items) {
            output.add(a.getDetails());
        }
        return output;
    }

    @Override
    public List<String> getItemsAcquiredInYear(String year) {
        List<AbstractItem> items = new ArrayList<AbstractItem>(_items.values());
        ItemComparator key = ItemComparator.Acquisition;

        Collections.sort(items, key);

        List<String> output = new ArrayList<String>();
        for (AbstractItem a : items) {
            if (a.checkIfAcquiredInYear(year)) {
                output.add(a.getDetails());
            }
        }
        return output;
    }

    @Override
    public List<String> getCreators() {
        Set<String> creators = new HashSet<String>();
        for (AbstractItem a : new ArrayList<AbstractItem>(_items.values()))
            creators.add(a.getCreator());
        List<String> output = new ArrayList<String>(creators);
        Collections.sort(output);
        return output;
    }

    @Override
    public List<String> getFlatReport() {
        List<String> output = new ArrayList<String>();
        List<AbstractItem> items = new ArrayList<AbstractItem>(_items.values());

        output.add(_flatName);

        Collections.sort(items, ItemComparator.orderSort(ItemComparator.Owner, ItemComparator.Format,
                ItemComparator.Creator, ItemComparator.Title));

        for (AbstractItem a : items) {
            output.add(a.formatReport());
        }

        return output;
    }
}