package item;

import java.util.Comparator;

/**
 * a comparison enum which compares objects that extend the Item class, by
 * speicific keys. Each enumeral is a valid sort key.
 * 
 */
public enum ItemComparator implements Comparator<AbstractItem> {
    Title {
        @Override
        public int compare(AbstractItem o1, AbstractItem o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    },
    Creator {
        @Override
        public int compare(AbstractItem o1, AbstractItem o2) {
            return o1.getCreator().compareTo(o2.getCreator());
        }
    },
    Acquisition {
        @Override
        public int compare(AbstractItem o1, AbstractItem o2) {
            return o1.getAcquireDate().compareTo(o2.getAcquireDate());
        }
    },
    Owner {
        @Override
        public int compare(AbstractItem o1, AbstractItem o2) {
            return o1.getOwner().compareTo(o2.getOwner());
        }
    },
    Format {
        @Override
        public int compare(AbstractItem o1, AbstractItem o2) {
            return o1.getClass().getName().compareTo(o2.getClass().getName());
        }
    };

    /**
     * 
     * @param itemComparators any amount of enumerals from ItemComparator that
     *                        indicate the order of two children objects of the Item
     *                        class.
     * @return Α Comparator for the collections class that allows the sort method to
     *         work.
     */
    public static Comparator<AbstractItem> orderSort(ItemComparator... itemComparators) {
        return new Comparator<AbstractItem>() {
            @Override
            public int compare(AbstractItem o1, AbstractItem o2) {
                /*
                 * Loops through each item comparator, until there is an inequality (in which
                 * case the inequality is returned). If no inequality is found, then the zero is
                 * returned indicating that the items are even in all given keys
                 */
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