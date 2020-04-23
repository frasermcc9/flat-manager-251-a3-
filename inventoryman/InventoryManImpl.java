package inventoryman;

import java.util.Collections;
import java.util.List;

import item.Book;
import item.Music;

public class InventoryManImpl implements InventoryMan {

    private String _flatName;
    private InventoryCollection _flatCollection = new InventoryCollection();

    public InventoryManImpl(String flatName) {
        _flatName = flatName;
    }

    @Override
    public String addBook(String author, String title, String pubYear, String publisher, String acquisitionDateStr,
            String owner, String costStr, String formatStr) {
        try {
            Book book = new Book(author, title, pubYear, publisher, acquisitionDateStr, owner, costStr, formatStr);
            return _flatCollection.itemHandler(book);
        } catch (Exception e) {
            return "ERROR " + e.getMessage();
        }
    }

    @Override
    public String addMusic(String artist, String title, String releaseDateStr, String acquisitionDateStr, String owner,
            String costStr, String formatStr) {
        try {
            Music music = new Music(artist, title, releaseDateStr, acquisitionDateStr, owner, costStr, formatStr);
            return _flatCollection.itemHandler(music);
        } catch (Exception e) {
            return "ERROR " + e.getMessage();
        }
    }

    @Override
    public String getItemToDisplay(String creator, String title, String formatStr) {
        try {
            return _flatCollection.findByProperties(creator, title, formatStr).getDetails();
        } catch (Exception e) {
            return e.getMessage(); //if no item is found
        }
    }

    @Override
    public List<String> getAll(String order) {
        try {
            return _flatCollection.getItemDetailsByOrder(order);
        } catch (Exception e) {
            return _flatCollection.getItemDetailsByOrder("Title"); // if given an invalid key, then just order by title.
        }
    }

    @Override
    public List<String> getItemsAcquiredInYear(String year) {
        return _flatCollection.getItemDetailsAcquiredInYear(year);
    }

    @Override
    public List<String> getCreators() {
        List<String> output = _flatCollection.getCreators();
        Collections.sort(output);
        return output;
    }

    @Override
    public List<String> getFlatReport() {
        return _flatCollection.inventoryReport(_flatName);
    }
}