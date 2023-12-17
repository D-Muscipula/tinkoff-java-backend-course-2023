package edu.hw10.Task1.Classes;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import edu.hw10.Task1.annotations.NotNull;

public record MyRecord(
    @NotNull String data,
    @Min(0) @Max(100) int number,
    boolean flag
) {


    public static MyRecord create() {
        return new MyRecord("DefaultData", 0, false);
    }

    public void printInfo() {
        System.out.println("Data: " + data + ", Number: " + number + ", Flag: " + flag);
    }
}

