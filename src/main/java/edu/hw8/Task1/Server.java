package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.lang.Thread.sleep;

public class Server {
    private final static HashMap<String, String> AFFRONTS = new HashMap<>() {{
        put("личности", "Не переходи на личности там, где их нет\n");
        put(
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами\n"
        );
        put(
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.\n"
        );
        put("интеллект", "Чем ниже интеллект, тем громче оскорбления\n");
    }};
    private final static String DEFAULT_MESSAGE = "My honest reaction to that information: 0_0";
    private final static int BUFFER_SIZE = 1024;
    private final static int PORT = 9999;
    private final static int SLEEP_TIME = 3000;

    private ServerSocketChannel socketChannel;

    public void run() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        socketChannel = serverSocketChannel;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        while (serverSocketChannel.isOpen()) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {

                SelectionKey key = iter.next();

                if (key.isAcceptable()) {
                    register(selector, serverSocketChannel);
                }

                if (key.isReadable()) {
                    executorService.submit(() -> {
                        try {

                            readAndAnswer(key);

                        } catch (IOException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
                iter.remove();
            }
        }
        executorService.close();
    }

    public void close() {
        try {
            socketChannel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void register(Selector selector, ServerSocketChannel serverSocket)
        throws IOException {

        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    private static void readAndAnswer(SelectionKey key) throws IOException, InterruptedException {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        SocketChannel client = (SocketChannel) key.channel();
        buffer.clear();
        int r = client.read(buffer);
        if (r <= 0) {
            sleep(SLEEP_TIME);
            r = client.read(buffer);
            if (r <= 0) {
                client.close();
            }
        }
        if (r > 0) {
            String response = new String(buffer.array()).trim();
            String[] words = response.split(" ");
            buffer.flip();
            for (var word : words) {
                if (AFFRONTS.containsKey(word)) {
                    client.write(ByteBuffer.wrap(AFFRONTS.get(word).getBytes()));
                    return;
                }
            }
            client.write(ByteBuffer.wrap(AFFRONTS.get(DEFAULT_MESSAGE).getBytes()));
        }
        buffer.clear();
    }
}
