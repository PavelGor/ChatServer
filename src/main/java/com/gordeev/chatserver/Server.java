package com.gordeev.chatserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            ClientHandler clientHandler = new ClientHandler();
            clientHandler.setInputStream(inputStream);
            clientHandler.setOutputStream(outputStream);

            Thread thread = new Thread(clientHandler);
            thread.start();
        }
    }

    public void setPort(int port) {
        this.port = port;
    }
}
