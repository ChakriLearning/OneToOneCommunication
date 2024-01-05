package com.onetoonecommunication.servermodule;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InitialClient {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    public InitialClient(Socket socket) {
        this.socket = socket;
        try {
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public String getClientName() {
        String clientName = "";
        try {
            clientName = reader.readLine();
            System.out.println("[" + clientName + "] : has joined the chat");
        } catch (IOException i) {
            i.printStackTrace();
        }
        return clientName;
    }

    public void displayOnlineClients(Map<String, Socket> clientSockets) {
        List<String> clientNames = new ArrayList<>();
        for(Map.Entry<String,Socket> entry : clientSockets.entrySet()) {
            clientNames.add(entry.getKey());
        }
        try {
            writer.write(clientNames + "\n");
            writer.flush();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public String getClientFriend() {
        String clientFriend = "";
        try {
            clientFriend = reader.readLine();
        } catch (IOException i) {
            i.printStackTrace();
        }
        return clientFriend;
    }
}
