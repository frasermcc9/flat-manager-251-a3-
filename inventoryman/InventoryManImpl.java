package inventoryman;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import item.AbstractItem;
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
            return e.getMessage(); // if no item is found
        }
    }

    @Override
    public List<String> getAll(String order) {
        try {
            return getDetailsList(_flatCollection.getItemsByOrder(order));
        } catch (Exception e) {
            // if given an invalid key, then just order by title.
            return getDetailsList(_flatCollection.getItemsByOrder("Title"));
        }
    }

    @Override
    public List<String> getItemsAcquiredInYear(String year) {
        return getDetailsList(_flatCollection.getItemsAcquiredInYear(year));
    }

    @Override
    public List<String> getCreators() {
        List<String> output = new ArrayList<String>(_flatCollection.getCreatorSet());
        Collections.sort(output);
        return output;
    }

    @Override
    public List<String> getFlatReport() {
        return getDetailsList(_flatCollection.itemInventoryReport(), _flatName);
    }

    /**
     * The following methods turn lists of AbstractItem (which are commonly returned
     * from {@link inventoryman.InventoryCollection#InventoryCollection()} methods)
     * into a list of their string representations.
     */

    /**
     * turns a list of item objects into a list of string representations of each
     * object, with format {@link item.AbstractItem#getDetails()} (see
     * implementations of method).
     * 
     * @param originaList the list of item objects to stringify
     * @return the list of string representations for the object list
     */
    private static List<String> getDetailsList(List<AbstractItem> originaList) {
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
    private static List<String> getDetailsList(List<AbstractItem> originaList, String flatName) {
        List<String> output = new ArrayList<String>();
        output.add(flatName);
        for (AbstractItem i : originaList) {
            output.add(i.formatReport());
        }
        return output;
    }
}