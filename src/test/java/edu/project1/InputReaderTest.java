package edu.project1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;

public class InputReaderTest {
    @Test
    @DisplayName("Пока не будет введено корректное значение ввод будет продолжаться")
    void readAndValidateMessageTest() {
        String correctLetter = "а";
        System.setIn(new ByteArrayInputStream(("abc\n" + correctLetter).getBytes()));
        Assertions.assertEquals(correctLetter, InputReader.readAndValidateMessage());
        correctLetter = "Б";
        System.setIn(new ByteArrayInputStream((correctLetter).getBytes()));
        Assertions.assertEquals(correctLetter.toLowerCase(), InputReader.readAndValidateMessage());
        correctLetter = "Б";
        System.setIn(new ByteArrayInputStream(("abc\n" + correctLetter).getBytes()));
        Assertions.assertEquals(correctLetter.toLowerCase(), InputReader.readAndValidateMessage());
        Assertions.assertThrows(Exception.class, () -> {
            System.setIn(new ByteArrayInputStream(("abc\n" + "asd").getBytes()));
            InputReader.readAndValidateMessage();
        });
    }

    @Test
    @DisplayName("Если введено \"сдаться\", то вернется \"сдаться\"")
    void giveUpTest() {
        String giveUp = "сдаться";
        System.setIn(new ByteArrayInputStream((giveUp).getBytes()));
        Assertions.assertEquals(giveUp, InputReader.readAndValidateMessage());
        System.setIn(new ByteArrayInputStream(("sfsdf\n"+giveUp).getBytes()));
        Assertions.assertEquals(giveUp, InputReader.readAndValidateMessage());
    }
}
