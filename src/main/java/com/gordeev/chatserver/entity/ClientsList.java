package com.gordeev.chatserver.entity;

import java.util.ArrayList;
import java.util.List;

public class ClientsList {
    private final static ClientsList INSTANCE = new ClientsList();
    private static List<Client> clients = new ArrayList<>();

    private ClientsList() {
    }

    public static ClientsList getInstance(){
        return INSTANCE;
    }

    public static List<Client> getClients() {
        return clients;
    }
}
