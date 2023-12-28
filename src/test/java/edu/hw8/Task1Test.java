package edu.hw8;

import edu.hw8.Task1.Client;
import edu.hw8.Task1.Server;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static java.lang.Thread.sleep;

public class Task1Test {
    @Test
    void test() throws InterruptedException {
        Server server = new Server();
        Thread thread = new Thread(() -> {
            try {
                server.run();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        sleep(1000);
        Client client = new Client(9999);
        Client client1 = new Client(9999);
        AtomicReference<String> answer = new AtomicReference<>();
        AtomicReference<String> answer1 = new AtomicReference<>();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            answer.set(client.sendMessage("глупый")
            );
            try {
                client.stop();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            answer1.set(client1.sendMessage("оскорбления" + " 1")
            );
            try {
                client1.stop();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            Assertions.assertEquals(
                "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
                answer.get()
            );
            Assertions.assertEquals(
                "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
                answer1.get()
            );
            server.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
