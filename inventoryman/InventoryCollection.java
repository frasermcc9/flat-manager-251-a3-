package inventoryman;

import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;

import item.AbstractItem;
import item.Book;
import item.InvalidFormatException;
import item.Music;

public class InventoryCollection {
    private Map<String, Book> _books = new HashMap<String, Book>();
    private Map<String, Music> _music = new HashMap<String, Music>();

    private enum ItemTypes {
        Book {
            public String newItem() {

            }

        },
        Music {
            public String newItem() {

            }

        }
    }

    /**
     * Adss a book to the inventory
     * 
     * @param author
     * @param title
     * @param pubYear
     * @param publisher
     * @param acqDate
     * @param owner
     * @param costStr
     * @param formatStr
     * @return String of form "Success"
     * @throws InvalidFormatException If the item was of incorrect format
     * @throws DuplicateItemException If the item was duplicate
     */
    public String newBook(String author, String title, String pubYear, String publisher, String acqDate, String owner,
            String costStr, String formatStr) throws InvalidFormatException, DuplicateItemException {

        Book newBook = new Book(author, title, pubYear, publisher, acqDate, owner, costStr, formatStr);

        if (_books.containsKey(newBook.getId()))
            throw new DuplicateItemException("Duplicate item added to inventory:" + newBook.getId());

        _books.put(newBook.getId(), newBook);

        return "Success";
    }

    public String newMusic(String artist, String title, String releaseDateStr, String acquisitionDateStr, String owner,
            String costStr, String formatStr) throws InvalidFormatException, DuplicateItemException {

        Music newMusic = new Music(artist, title, releaseDateStr, acquisitionDateStr, owner, costStr, formatStr);

        if (_music.containsKey(newMusic.getId()))
            throw new DuplicateItemException("Duplicate item added to inventory:" + newMusic.getId());

        _music.put(newMusic.getId(), newMusic);

        return "Success";
    }

}