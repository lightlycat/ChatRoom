package clu.agile.core;

import java.net.Socket;

public interface CLUChatApplication {
    public void addUser(Socket client);
    public void broadcastMessage(String message);
}

