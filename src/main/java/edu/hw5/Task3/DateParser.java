package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateParser {

    private final DateParser next;

    public DateParser(DateParser next) {
        this.next = next;
    }

    abstract public Optional<LocalDate> doParse(String request);

    public DateParser getNext() {
        return this.next;
    }
}
