package item;

import java.util.Comparator;

/**
 * a comparison enum which compares objects that extend the Item class. 
 * 
 */
public enum ItemComparator implements Comparator<Item> {
    Title {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    },
    Creator {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getCreator().compareTo(o2.getCreator());
        }
    },
    Acquisition {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getAcquireDate().compareTo(o2.getAcquireDate());
        }
    },
    Owner {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getOwner().compareTo(o2.getOwner());
        }
    },
    Format {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getClass().getName().compareTo(o2.getClass().getName());
        }
    };

    public static Comparator<Item> orderSort(ItemComparator... itemComparators) {
        return new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                for (ItemComparator sortKey : itemComparators) {
                    int result = sortKey.compare(o1, o2);
                    if (result != 0) {
                        return result;
                    }
                }
                return 0;
            }
        };
    }
}