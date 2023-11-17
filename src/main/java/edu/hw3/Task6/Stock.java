package edu.hw3.Task6;

import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {
    private final String name;
    private Double price;

    public Stock(String name, Double price) {
        this.name = name;
        this.price = price;
        if (name == null || price <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Stock setPrice(Double price) {
        this.price = price;
        return this;
    }

    @Override
    public int compareTo(@NotNull Stock o) {
        if ((this.price - o.price) < 0) {
            return -1;
        } else if ((this.price - o.price) > 0) {
            return 1;
        }
        return 0;
    }
}
