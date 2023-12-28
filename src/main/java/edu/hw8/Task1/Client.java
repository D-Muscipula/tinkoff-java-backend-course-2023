package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    private final SocketChannel client;
    private ByteBuffer buffer;
    private final static int BUFFER_SIZE = 1024;

    public void stop() throws IOException {
        client.close();
        buffer = null;
    }

    public Client(int port) {
        try {
            client = SocketChannel.open(new InetSocketAddress("localhost", port));
            buffer = ByteBuffer.allocate(BUFFER_SIZE);
        } catch (IOException ignored) {
            throw new RuntimeException();
        }
    }

    public String sendMessage(String msg) {
        buffer = ByteBuffer.wrap(msg.getBytes());
        String response = null;
        try {
            client.write(buffer);
            buffer.clear();
            buffer = ByteBuffer.allocate(BUFFER_SIZE);
            int r = client.read(buffer);
            while (r != -1) {
                response = new String(buffer.array()).trim();
                r = client.read(buffer);
                if (r != 0) {
                    return response;
                }
            }
            buffer.clear();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return response;

    }
}

