package edu.hw2;

import edu.hw2.Task4.CallingInfo;
import edu.hw2.Task4.Task4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    void callingInfoTest() {
        Assertions.assertInstanceOf(CallingInfo.class, Task4.callingInfo());
        CallingInfo callingInfo = Task4.callingInfo();
        Assertions.assertEquals("edu.hw2.Task4Test", callingInfo.className());
        Assertions.assertEquals("callingInfoTest", callingInfo.methodName());
    }

    @Test
    void anotherOneCallingInfoTest() {
        CallingInfo callingInfo = Wrap.getCallingInfo();
        Assertions.assertEquals("edu.hw2.Task4Test$Wrap", callingInfo.className());
        Assertions.assertEquals("getCallingInfo", callingInfo.methodName());
    }

    public static class Wrap {
        static CallingInfo getCallingInfo() {
            return Task4.callingInfo();
        }
    }
}
