package com.onetoonecommunication.clientmodule.controller;

import com.onetoonecommunication.clientmodule.handler.ClientHandler;
import com.onetoonecommunication.clientmodule.handler.SampleClient;

import java.net.Socket;

public class LaunchClient {

    public static void main(String[] args) {
        String clientIpAddress = "localhost";
        int clientPort = 5000;
        SampleClient sampleClient = new SampleClient(clientIpAddress, clientPort);
        Socket socket = sampleClient.connectToServer();  //connecting to the server;
        ClientHandler clientHandler = new ClientHandler(socket);
        clientHandler.registerUserName();  //giving userName to other client's;
        clientHandler.takeAndGiveWhomToChat();  //take other client's usernames and tell whom to chat;
        String msg = "";
        while(!msg.equals("bye")) {
            new Thread(clientHandler::readFromFriend).start();
            msg = clientHandler.writeToFriend();
        }
        clientHandler.closeResources();

    }
}
