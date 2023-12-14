package edu.hw5;

import edu.hw5.Task3.Task3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;

public class Task3Test {
    @Test
    void parseDateTest() {
        Assertions.assertEquals(Optional.of(LocalDate.of(2020, 10, 11)), Task3.parseDate("2020-10-11"));
        Assertions.assertEquals(Optional.of(LocalDate.of(2020, 12, 2)), Task3.parseDate("2020-12-2"));
        Assertions.assertEquals(Optional.of(LocalDate.of(2020, 10, 11)), Task3.parseDate("10/11/2020"));
        Assertions.assertEquals(Optional.of(LocalDate.of(2020, 1, 3)), Task3.parseDate("1/3/20"));
        Assertions.assertEquals(Optional.of(LocalDate.now().plusDays(1)), Task3.parseDate("tomorrow"));
        Assertions.assertEquals(Optional.of(LocalDate.now()), Task3.parseDate("today"));
        Assertions.assertEquals(Optional.of(LocalDate.now().minusDays(1)), Task3.parseDate("yesterday"));
        Assertions.assertEquals(Optional.of(LocalDate.now().minusDays(1)), Task3.parseDate("1 day ago"));
        Assertions.assertEquals(Optional.of(LocalDate.now().minusDays(2234)), Task3.parseDate("2234 days ago"));
        Assertions.assertEquals(Optional.empty(), Task3.parseDate("test"));
        Assertions.assertEquals(Optional.empty(), Task3.parseDate("2020-99-99"));
    }
}
