package edu.project3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArgumentsTest {
    @Test
    void getArgumentsTest() {
        String s1 = "--path logs/2023* --from 2023-08-31 --format markdown";
        LocalDate temp = LocalDate.parse("2023-08-31");
        LocalDateTime from  = temp.atTime(0,0,0);
        Arguments arguments = Arguments.getCommandFromString(s1);
        String[] paths = {"logs/2023*"};
        Arguments arguments1 = new Arguments(paths, from, null, "markdown");
        Assertions.assertEquals(arguments1, arguments);

        s1 = "--path logs/2023* aboba.txt --from 2023-08-31 --to 2023-11-17T07:20:36";
        temp = LocalDate.parse("2023-08-31");
        from  = temp.atTime(0,0,0);
        LocalDateTime to = LocalDateTime.parse("2023-11-17T07:20:36");
        arguments = Arguments.getCommandFromString(s1);
        paths = new String[]{"logs/2023*", "aboba.txt"};
        arguments1 = new Arguments(paths, from, to, null);
        Assertions.assertEquals(arguments1, arguments);
    }
}
