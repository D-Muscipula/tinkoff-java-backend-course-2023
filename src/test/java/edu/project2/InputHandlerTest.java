package edu.project2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;

public class InputHandlerTest {
    @Test
    void variantHandlerTest() {
        System.setIn(new ByteArrayInputStream(("1").getBytes()));
        Assertions.assertEquals(1, InputHandler.variantHandler());
        System.setIn(new ByteArrayInputStream(("2").getBytes()));
        Assertions.assertEquals(2, InputHandler.variantHandler());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            System.setIn(new ByteArrayInputStream(("3").getBytes()));
            InputHandler.variantHandler();
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            System.setIn(new ByteArrayInputStream(("a").getBytes()));
            InputHandler.variantHandler();
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            System.setIn(new ByteArrayInputStream(("0").getBytes()));
            InputHandler.variantHandler();
        });
    }

    @Test
    void xAndYHandlerTest() {
        System.setIn(new ByteArrayInputStream(("0").getBytes()));
        Assertions.assertEquals(0, InputHandler.xAndYHandler());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            System.setIn(new ByteArrayInputStream(("a").getBytes()));
            InputHandler.xAndYHandler();
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            System.setIn(new ByteArrayInputStream(("-1").getBytes()));
            InputHandler.xAndYHandler();
        });
    }

    @Test
    void sideHandlerTest() {
        System.setIn(new ByteArrayInputStream(("1").getBytes()));
        Assertions.assertEquals(1, InputHandler.sideHandler());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            System.setIn(new ByteArrayInputStream(("a").getBytes()));
            InputHandler.sideHandler();
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            System.setIn(new ByteArrayInputStream(("0").getBytes()));
            InputHandler.sideHandler();
        });
    }
}
