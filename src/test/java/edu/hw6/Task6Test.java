package edu.hw6;

import edu.hw6.Task6.Task6;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task6Test {

    @Test
    void checkPortsTest() {
        Map<Integer, String> map = Task6.checkPorts();
        for (var entry : map.entrySet()) {
            if (entry.getValue().equals("TCP")) {
                Assertions.assertFalse(Task6.isTCPFree(entry.getKey()));
            }
            if (entry.getValue().equals("UDP")) {
                Assertions.assertFalse(Task6.isUDPFree(entry.getKey()));
            }
        }
    }
}
