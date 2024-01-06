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
        clientHandler.registerUserName();  //regitering the user name;  //write
        clientHandler.listActiveUsers(); // printing the avilable user's; //read
        clientHandler.connectWithPeer(); //connecting with the friend;  //write
        String msg = "";
        while(!socket.isClosed()) {
            new Thread(clientHandler::readFromFriend).start();  //read
            new Thread(clientHandler::writeToFriend).start(); //write
        }
        clientHandler.closeResources();
    }
}
