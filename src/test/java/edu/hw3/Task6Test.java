package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.Stocks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task6Test {
    @Test
    void stockTest() {
        Stock stock = new Stock("a", 12.0);
        Stock stock1 = new Stock("c", 12.1);
        Stock stock2 = new Stock("b", 12.3);
        Stocks stocks = new Stocks();
        stocks.add(stock1);
        stocks.add(stock2);
        stocks.add(stock);
        Assertions.assertEquals(12.3, stocks.mostValuableStock().getPrice());
        Assertions.assertEquals("b", stocks.mostValuableStock().getName());
        stocks.remove(stocks.mostValuableStock());
        Assertions.assertEquals(12.1, stocks.mostValuableStock().getPrice());
        Assertions.assertEquals("c", stocks.mostValuableStock().getName());
        stocks.remove(stocks.mostValuableStock());
        Assertions.assertEquals(12.0, stocks.mostValuableStock().getPrice());
        Assertions.assertEquals("a", stocks.mostValuableStock().getName());
        stocks.remove(stocks.mostValuableStock());
        Assertions.assertNull(stocks.mostValuableStock());
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new Stock(null, 1.0));
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new Stock("abc", -1.0));
    }
}
