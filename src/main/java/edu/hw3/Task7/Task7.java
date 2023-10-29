package edu.hw3.Task7;

import java.util.Comparator;

public final class Task7 {
    static Comparator<String> comparator = (o1, o2) -> {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return 1;
        } else if (o2 == null) {
            return -1;
        } else {
            return o1.compareTo(o2);
        }
    };

    public static Comparator<String> getComparator() {
        return comparator;
    }

    private Task7() {
    }
}

