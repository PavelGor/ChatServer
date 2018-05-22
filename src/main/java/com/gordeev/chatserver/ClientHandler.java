package com.gordeev.chatserver;

import com.gordeev.chatserver.Entity.Client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {

    private static List<Client> clients = new ArrayList<>();
    private Client client = new Client();
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            String nick = reader.readLine();
            if (nick != null) {
                client.setNick(nick);
                client.setWriter(writer);

                clients.add(client);
            }


            for (Client client : clients) {

                client.getWriter().write("пополнение прибыло: " + nick);
                client.getWriter().newLine();
                client.getWriter().flush();
            }

            String message = reader.readLine();
            while (message != null) {
                for (Client client : clients) {
                    client.getWriter().write(nick + " : " + message);
                    client.getWriter().newLine();
                    client.getWriter().flush();
                }
                message = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
