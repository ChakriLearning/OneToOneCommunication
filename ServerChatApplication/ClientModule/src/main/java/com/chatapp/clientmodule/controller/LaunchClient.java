package com.chatapp.clientmodule.controller;
import com.chatapp.clientmodule.client.SampleClient;
import com.chatapp.clientmodule.handler.ServerCommunicationHandler;

import java.net.*;
public class LaunchClient {
    public static void main(String[] args) {
        String serverIpAddress = "localhost";
        int serverPort = 5000;
        SampleClient sampleClient = new SampleClient(serverIpAddress, serverPort);
        Socket socket = sampleClient.connectToServer();
        System.out.println("Client is listing on ipAddress : " + serverIpAddress + " and port : " + serverPort);

        ServerCommunicationHandler serverCommunicationHandler = new ServerCommunicationHandler(socket);
        serverCommunicationHandler.writeClientName();
        String msg = "";
        do {
            msg = serverCommunicationHandler.writeToServer();
            serverCommunicationHandler.readAndPrintFromServer();
        } while(!msg.equals("bye"));
        serverCommunicationHandler.closeResources();
    }
}