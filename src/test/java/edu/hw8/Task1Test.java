package edu.hw8;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import edu.hw8.Task1.Client;
import edu.hw8.Task1.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static java.lang.Thread.sleep;

public class Task1Test {
    @Test
    void task1Test() throws IOException {

        String hostname = "127.0.0.1";
        int port = 9999;
        SocketChannel client = SocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress(hostname, port);
        client.connect(hostAddress);
        for (int i = 0; i < 100; i++) {
            ByteBuffer message = ByteBuffer.wrap((i+"").getBytes());
            //System.out.println(new String(message.array(), StandardCharsets.UTF_8));
            client.write(message);
            ByteBuffer byteBufferGotMessage = ByteBuffer.allocate(25);
            int readBytes = client.read(byteBufferGotMessage);
            System.out.println(new String(Arrays.copyOfRange(byteBufferGotMessage.array(), 0, readBytes), StandardCharsets.UTF_8));
        }

        client.close();
    }
    @Test
    void test() throws IOException, InterruptedException {
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
       Client client2 = new Client(9999);
        Client client3 = new Client(9999);
        AtomicReference<String> answer = new AtomicReference<>();
        String answer1;

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            answer.set(client.sendMessage("глупый")
                );
            System.out.println(answer.get());
            try {
                client.stop();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {System.out.println(client1.sendMessage("оскорбления" + " 1"));
            try {
                client1.stop();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        /*executorService.submit(() -> {System.out.println(client2.sendMessage("dsfssda12 интеллект dsfssda12 df dsfssda12 df dsfssda12 df"));
            try {
                client2.stop();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {System.out.println(client3.sendMessage("xm личности")); System.out.println(client3.sendMessage("xmm"));
            try {
                client3.stop();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });*/
        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            Assertions.assertEquals("А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.", answer.get());
            server.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(client.sendMessage("aboba"));
       //client.stop();
       //client1.stop();
       //client2.stop();
    }
}
