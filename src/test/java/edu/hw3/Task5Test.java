package edu.hw3;

import edu.hw3.Task5.Contact;
import edu.hw3.Task5.Task5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task5Test {
    @Test void simpleTest() {
        Assertions.assertArrayEquals(
            new Contact[] {new Contact("Aquinas", "Thomas"), new Contact("Descartes", "Rene"),
                new Contact("Hume", "David"), new Contact("Locke", "John")},
            Task5.parseContacts(new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"}, "ASC")
        );
        Assertions.assertArrayEquals(
            new Contact[] {new Contact("Gauss", "Carl"), new Contact("Euler", "Leonhard"),
                new Contact("Erdos", "Paul")},
            Task5.parseContacts(new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"}, "DESC")
        );
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        Assertions.assertThrowsExactly(
            IllegalArgumentException.class,
            () -> Task5.parseContacts(new String[] {"Paul Erdos", "Leonhard Euler", null}, "DESC")
        );
    }

    @Test
    void emptyOrNullArray() {
        Assertions.assertArrayEquals(new Contact[] {}, Task5.parseContacts(null, "DESC"));
        Assertions.assertArrayEquals(new Contact[] {}, Task5.parseContacts(new String[] {null}, "DESC"));
    }

    @Test
    @DisplayName("При равенстве фамилий сортировка идет по именам") void ifSurnamesAreEqual() {
        Assertions.assertArrayEquals(
            new Contact[] {new Contact("Gauss", "D"), new Contact("Gauss", "Carl"), new Contact("Erdos", "Paul")},
            Task5.parseContacts(new String[] {"Paul Erdos", "Carl Gauss", "D Gauss"}, "DESC")
        );
    }

    @Test void ifThereIsNoSurname() {
        Assertions.assertArrayEquals(
            new Contact[] {new Contact("", "Paul"),
                new Contact("", "D"), new Contact("", "Carl"),},
            Task5.parseContacts(new String[] {"Paul", "Carl", "D"}, "DESC")
        );
    }
}
