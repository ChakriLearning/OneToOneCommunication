package com.onetoonecommunication.servermodule.activeclient;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NewlyAddedClient {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    public NewlyAddedClient(Socket socket) {
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
}
