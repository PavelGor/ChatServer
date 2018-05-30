package com.gordeev.chatserver;

import com.gordeev.chatserver.entity.Client;
import com.gordeev.chatserver.entity.ClientsList;

import java.io.*;
import java.util.List;

public class ClientHandler implements Runnable {
    private List<Client> clients = ClientsList.getClients();
    private Client client = new Client();
    private InputStream inputStream;
    private OutputStream outputStream;

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"))) {

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
            while ((message != null) && (!Thread.interrupted())) {
                for (Client client : clients) {
                    client.getWriter().write(nick + " : " + message);
                    client.getWriter().newLine();
                    client.getWriter().flush();
                }
                message = reader.readLine();
            }

            clients.remove(client);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
