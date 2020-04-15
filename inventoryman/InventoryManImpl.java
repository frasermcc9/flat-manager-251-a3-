package inventoryman;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import item.Book;
import item.Item;
import item.ItemComparator;
import item.Music;

public class InventoryManImpl implements InventoryMan {

    private String _flatName;

    private HashMap<Integer, Item> _items = new HashMap<Integer, Item>();

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
        Integer id = Item.getId(creator, title, formatStr);
        Item retrievedItem = _items.get(id);
        return retrievedItem.getDetails();
    }

    @Override
    public List<String> getAll(String order) throws IllegalArgumentException {
        List<Item> items = new ArrayList<Item>(_items.values());
        ItemComparator key = ItemComparator.valueOf(order);
        Collections.sort(items, key);

        List<String> output = new ArrayList<String>();
        for (Item a : items) {
            output.add(a.getDetails());
        }
        return output;
    }

    @Override
    public List<String> getItemsAcquiredInYear(String year) {
        List<Item> items = new ArrayList<Item>(_items.values());
        ItemComparator key = ItemComparator.Acquisition;

        Collections.sort(items, key);

        List<String> output = new ArrayList<String>();
        for (Item a : items) {
            if (a.checkIfAcquiredInYear(year)) {
                output.add(a.getDetails());
            }
        }
        return output;
    }

    @Override
    public List<String> getCreators() {
        Set<String> creators = new HashSet<String>();
        for (Item a : new ArrayList<Item>(_items.values()))
            creators.add(a.findCreator());
        List<String> output = new ArrayList<String>(creators);
        Collections.sort(output);
        return output;
    }

    @Override
    public List<String> getFlatReport() {
        List<String> output = new ArrayList<String>();

        output.add(_flatName);

        List<Item> books = new ArrayList<Item>();
        List<Item> music = new ArrayList<Item>();
        List<Item> items = new ArrayList<Item>(_items.values());

        for (Item a : items) {
            if (a instanceof Book)
                books.add(a);
            else
                music.add(a);
        }
        items.clear();
        Collections.sort(books, ItemComparator.Title);
        Collections.sort(books, ItemComparator.Creator);
        Collections.sort(music, ItemComparator.Title);
        Collections.sort(music, ItemComparator.Creator);

        items.addAll(books);
        items.addAll(music);

        Collections.sort(items, ItemComparator.Owner);

        for (Item a : items) {
            output.add(a.formatReport());
        }

        return output;
    }
}