package edu.hw3.Task6;

import java.util.Collections;
import java.util.PriorityQueue;

public class Stocks implements StockMarket {

    PriorityQueue<Stock> stocks = new PriorityQueue<>(Collections.reverseOrder());

    @Override
    public void add(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }
}
