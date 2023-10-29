package edu.hw3.Task5;

import java.util.Arrays;
import java.util.Collections;

public final class Task5 {
    public static Contact[] parseContacts(String[] persons, String sort) {
        if (persons == null || persons.length == 1 && persons[0] == null) {
            return new Contact[0];
        }
        Contact[] contacts = new Contact[persons.length];
        for (int i = 0; i < contacts.length; i++) {
            if (persons[i] == null) {
                throw new IllegalArgumentException();
            }
            String[] nameAndSurname = persons[i].split(" ");
            String surname = nameAndSurname.length == 1 ? "" : nameAndSurname[nameAndSurname.length - 1];
            String name = nameAndSurname[0];
            contacts[i] = new Contact(surname, name);
        }
        if (sort.equals("ASC")) {
            Arrays.sort(contacts);
        } else if (sort.equals("DESC")) {
            Arrays.sort(contacts, Collections.reverseOrder());
        }
        return contacts;
    }

    private Task5() {
    }
}
