package com.chatapp.servermodule.handler;

import java.io.*;
import java.net.*;

public class ClientHandler {
    private final Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ClientHandler(Socket socket)
    {
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
        } catch (IOException i) {
            i.printStackTrace();
        }
        return clientName;
    }
    public void printClientName (String clientName) {
       System.out.println("[" + clientName + "] : has joined the chat");
    }
    public String readAndWriteFromClient() {
         String msgFromClient = "";
         try {
             msgFromClient = reader.readLine();
             writer.write(msgFromClient + "\n");
             writer.flush();
             System.out.println("message from client : " + msgFromClient);
         } catch (IOException i) {
             i.printStackTrace();
         }
         return msgFromClient;
    }
    public void closeClientConnection(){
        try {
            socket.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


//    public void writeToClient(String msgFromClient) {
//        try {
//            writer.write("Server Received: " + msgFromClient + "\n");
//            writer.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}

