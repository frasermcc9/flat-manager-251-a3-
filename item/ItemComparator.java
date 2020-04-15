package item;

import java.util.Comparator;

//TODO change to not accessing protected variables?

public enum ItemComparator implements Comparator<Item> {
    Title {
        @Override
        public int compare(Item o1, Item o2) {
            return o1._title.compareTo(o2._title);
        }
    },
    Creator {
        @Override
        public int compare(Item o1, Item o2) {
            return o1._creator.compareTo(o2._creator);
        }
    },
    Acquisition {
        @Override
        public int compare(Item o1, Item o2) {
            return o1._acquireDate.compareTo(o2._acquireDate);
        }
    },
    Owner {
        @Override
        public int compare(Item o1, Item o2) {
            return o1._owner.compareTo(o2._owner);
        }
    }
}