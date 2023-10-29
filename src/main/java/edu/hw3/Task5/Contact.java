package edu.hw3.Task5;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Contact implements Comparable<Contact> {
    String surname;
    String name;

    public Contact(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }

    @Override
    public int compareTo(@NotNull Contact o) {
        if (this.surname.compareTo(o.surname) == 0) {
            return this.name.compareTo(o.name);
        } else {
            return this.surname.compareTo(o.surname);
        }
    }

    @Override
    public String toString() {
        return "\"" + name + " " + surname + "\"";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Contact other)) {
            return false;
        }
        return this.surname.equals(other.surname) && this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name);
    }
}
