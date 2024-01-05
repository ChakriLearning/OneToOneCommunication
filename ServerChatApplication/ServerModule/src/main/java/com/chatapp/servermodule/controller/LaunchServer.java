package com.chatapp.servermodule.controller;
import com.chatapp.servermodule.server.SampleServer;
import com.chatapp.servermodule.handler.ClientHandler;
import java.net.*;

public class LaunchServer {
    public static void main(String[] args) {
        int port = 5000;
        SampleServer server = new SampleServer(port);
        ServerSocket serverSocket = server.startServer();  //starting the server
        while(!serverSocket.isClosed()) {
            new Thread( ()-> {
                Socket socket = server.acceptClientConnection();
                ClientHandler clientHandler = new ClientHandler(socket);
                String clientName = clientHandler.getClientName(); //getting client name;
                clientHandler.printClientName(clientName);  //printing the client name;
                String msgFromClient = "";
                do {
                    msgFromClient = clientHandler.readAndWriteFromClient();
                } while (!msgFromClient.equals("bye"));
                System.out.println("[" + clientName + "] : left the chat");
                //closing the resources
                clientHandler.closeClientConnection();
            }).start();
        }
        server.stopServer();
    }
}
