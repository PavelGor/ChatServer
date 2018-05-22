package com.gordeev.chatserver;

import java.io.IOException;

public class RunChatServer {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.setPort(3000);
        server.start();
    }
}
