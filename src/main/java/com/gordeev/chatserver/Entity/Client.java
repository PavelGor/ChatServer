package com.gordeev.chatserver.Entity;

import java.io.BufferedWriter;

public class Client {
    private String nick;
    private BufferedWriter writer;

    public Client() {
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public BufferedWriter getWriter() {
        return writer;
    }

    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nick='" + nick + '\'' +
                '}';
    }
}
