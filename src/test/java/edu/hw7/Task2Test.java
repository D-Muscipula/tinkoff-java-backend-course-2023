package edu.hw7;

import edu.hw7.Task2.Task2;
import java.math.BigInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task2Test {

    @Test
    void countFactorialTest() {
        Assertions.assertEquals(BigInteger.valueOf(3628800), Task2.countFactorial(10));
        Assertions.assertEquals(
            new BigInteger("30414093201713378043612608166064768844377641568960512000000000000"),
            Task2.countFactorial(50)
        );
        Assertions.assertEquals(new BigInteger("11978571669969891796072783721689098736458938142546425857555362864628" +
            "009582789845319680000000000000000"), Task2.countFactorial(70));

    }
}
