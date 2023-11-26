package edu.hw7;

import edu.hw7.Task4.Task4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    void getPITest() {
        double a = Task4.getPI(100000000);
        Assertions.assertTrue(a < 3.142 && a > 3.140);
    }

    @Test
    void multithreadingGetPITest() {
        double a = Task4.multithreadingGetPI(1000000000, 4);
        Assertions.assertTrue(a < 3.142 && a > 3.140);
    }
}
//количество  многопоток    погрешность   однопоток ускорение
//10млн         95млсек     0.00133       141мс     1.5
//100млн        416млсек    0.000077      1сек      2.5
//1млрд            4сек     0.00001       10сек     2.5
