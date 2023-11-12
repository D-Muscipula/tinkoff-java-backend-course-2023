package edu.hw5;

import edu.hw5.Task1.Task1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    void getAverageTimeTest() {
        String session =
            """
                2022-03-12, 20:20 - 2022-03-12, 23:50
                2022-04-01, 21:30 - 2022-04-02, 01:20
                """;
        Assertions.assertEquals("3ч 40м", Task1.getAverageTime(session));
        session =
            """
                2022-03-12, 20:20 - 2022-03-12, 23:50
                2022-04-01, 21:30 - 2022-04-02, 01:20
                2022-03-12, 20:20 - 2022-03-12, 23:50
                2022-04-01, 21:30 - 2022-04-02, 01:20
                2022-03-12, 20:20 - 2022-03-12, 23:50
                2022-04-01, 21:30 - 2022-04-02, 01:20
                """;
        Assertions.assertEquals("3ч 40м", Task1.getAverageTime(session));
        session =
            """
                2022-11-08, 17:39 - 2022-11-11, 09:15
                2022-11-11, 09:39 - 2022-11-11, 10:15
                2022-11-16, 15:30 - 2022-11-17, 10:15
                """;
        Assertions.assertEquals("27ч 39м", Task1.getAverageTime(session));
        session =
            """
                2022-11-08, 17:39 - 2022-11-08, 17:45
                """;
        Assertions.assertEquals("0ч 6м", Task1.getAverageTime(session));

        session =
            """
                2022-12-08, 17:39 - 2022-11-11, 09:15
                """;
        String finalSession = session;
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task1.getAverageTime(finalSession));

        session =
            """
                2022-11-08, 37:39 - 2022-11-11, 09:15
                """;
        String finalSession1 = session;
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task1.getAverageTime(finalSession1));
    }
}
