package edu.hw3;

import edu.hw3.Task8.BackwardIterator;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task8Test {
    @Test
    void iteratorArrayListTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(5);
        list.add(7);
        BackwardIterator<Integer> iterator = new BackwardIterator<>(list);
        ListIterator<Integer> iteratorResult = list.listIterator(list.size());
        while (iterator.hasNext()) {
            Assertions.assertEquals(iteratorResult.previous(), iterator.next());
        }
    }

    @Test
    void fromExampleTest() {
        var iterator = new BackwardIterator<>(List.of(1, 2, 3));
        int last = 3;
        while (iterator.hasNext()) {
            Assertions.assertEquals(last--, iterator.next());
        }
    }

    @Test
    void hashSetTest() {
        HashSet<String> hashSet = new HashSet<>() {
            {
                add("b");
                add("a");
                add("abc");
                add("c");
                add("d");
            }
        };
        ArrayList<String> strings = new ArrayList<>(hashSet);
        var backIterator = strings.listIterator(hashSet.size());
        var iterator = new BackwardIterator<>(hashSet);
        while (iterator.hasNext()) {
            Assertions.assertEquals(backIterator.previous(), iterator.next());
        }
    }

    @Test
    void priorityQueueTest() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(3);
        priorityQueue.add(9);
        priorityQueue.add(1);
        ArrayList<Integer> strings = new ArrayList<>(priorityQueue);
        var backIterator = strings.listIterator(priorityQueue.size());
        var iterator = new BackwardIterator<>(priorityQueue);
        while (iterator.hasNext()) {
            Assertions.assertEquals(backIterator.previous(), iterator.next());
        }
    }

    @Test
    void ArrayQueueTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.add(3);
        arrayDeque.add(9);
        arrayDeque.add(1);
        ArrayList<Integer> strings = new ArrayList<>(arrayDeque);
        var backIterator = strings.listIterator(arrayDeque.size());
        var iterator = new BackwardIterator<>(arrayDeque);
        while (iterator.hasNext()) {
            Assertions.assertEquals(backIterator.previous(), iterator.next());
        }
    }
}
