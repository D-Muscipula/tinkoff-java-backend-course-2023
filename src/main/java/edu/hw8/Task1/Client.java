package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    private SocketChannel client;
    private ByteBuffer buffer;

    public void stop() throws IOException {
        client.close();
        buffer = null;
    }

    public Client(int port) {
        try {
            client = SocketChannel.open(new InetSocketAddress("localhost", port));
            buffer = ByteBuffer.allocate(1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendMessage(String msg) {
        buffer = ByteBuffer.wrap(msg.getBytes());
        String response = null;
        try {
            System.out.println("Клиент: " + new String(buffer.array()));
            client.write(buffer);
            buffer.clear();
            buffer = ByteBuffer.allocate(1024);
            int r = client.read(buffer);
            while (r != -1) {
                response = new String(buffer.array()).trim();
                r = client.read(buffer);
                if (r != 0) {
                    return response;
                }
                System.out.println("response=" + response);
            }
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;

    }
}

