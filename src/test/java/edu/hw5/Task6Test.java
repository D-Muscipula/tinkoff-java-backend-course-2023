package edu.hw5;

import edu.hw5.Task6.Task6;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task6Test {
    @Test
    void isSubsequenceTest() {
        Assertions.assertTrue(Task6.isSubsequence("abc", "abc"));
        Assertions.assertTrue(Task6.isSubsequence("abc", "atbtc"));
        Assertions.assertTrue(Task6.isSubsequence("abc", "tachfdbaabgabcaabg"));
        Assertions.assertTrue(Task6.isSubsequence("abc", "tattbdddcsd"));
        Assertions.assertTrue(Task6.isSubsequence("abc", "tttattbdddcsd"));
        Assertions.assertTrue(Task6.isSubsequence("", "attbdddcsd"));

        Assertions.assertFalse(Task6.isSubsequence("a", "hjt"));
        Assertions.assertFalse(Task6.isSubsequence("abc", "hjt"));
        Assertions.assertFalse(Task6.isSubsequence("abc", "ab"));
        Assertions.assertFalse(Task6.isSubsequence("abc", "cab"));
        Assertions.assertFalse(Task6.isSubsequence("abc", "ctbtat"));
    }
}
