package com.onetoonecommunication.servermodule.controller;

import com.onetoonecommunication.servermodule.InitialClient;
import com.onetoonecommunication.servermodule.onetoonecommunicationhandler.OneToOneClientHandler;
import com.onetoonecommunication.servermodule.SampleServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class LaunchServer {
    private static Map<String,Socket> clientSockets = new HashMap<>();
    public static void main(String[] args) {
        int serverPort = 5000;
        SampleServer sampleServer = new SampleServer(serverPort);
        ServerSocket serverSocket = sampleServer.runServer();
        System.out.println("Server is running on port : " + serverPort);
        while (!serverSocket.isClosed()) {
            new Thread( ()-> {
                Socket presentClientSocket = sampleServer.acceptClientRequest(serverSocket); //accepting client request's;
                InitialClient initialClient = new InitialClient(presentClientSocket);
                String clientName = initialClient.getClientName();  //getting clientName;
                clientSockets.put(clientName,presentClientSocket);  //putting newly added client's;
                initialClient.displayOnlineClients(clientSockets);  //displaying the client usernames;
                String clientFriend = initialClient.getClientFriend();
                OneToOneClientHandler oneToOneClientHandler = new OneToOneClientHandler(clientSockets,presentClientSocket);
                Socket clientFrndSocket = oneToOneClientHandler.connectToClientFriend(clientFriend);
                oneToOneClientHandler.makeClientFrndSocket();
                oneToOneClientHandler.makePresentClientSocket();
                String msg = "";
                while (!presentClientSocket.isClosed()) {
                   new Thread(oneToOneClientHandler::readPresendClientAndWriteToFrnd).start();
                   new Thread(oneToOneClientHandler::readFromFrndAndWriteToPresent).start();
                }
            }).start();
        }
    }
}