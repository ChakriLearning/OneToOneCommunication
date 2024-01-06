package com.onetoonecommunication.servermodule.controller;

import com.onetoonecommunication.servermodule.activeclient.NewlyAddedClient;
import com.onetoonecommunication.servermodule.onetoonecommunicationhandler.OneToOneClientHandler;

import java.net.ServerSocket;
import java.net.Socket;

public class LaunchServer {
    public static void main(String[] args) {
        int serverPort = 5000;
        SampleServer sampleServer = new SampleServer(serverPort);
        ServerSocket serverSocket = sampleServer.runServer();
        System.out.println("Server is running on port : " + serverPort);
        while (!serverSocket.isClosed()) {
            new Thread( ()-> {
                Socket newClientSocket = sampleServer.acceptClientRequest(serverSocket); //accepting client request's;
                NewlyAddedClient newlyAddedClient = new NewlyAddedClient(newClientSocket);
                String clientName = newlyAddedClient.getClientName();  //getting clientName; //read

                //OneToOneClientHandler.clientObject.put(clientName,newClientSocket);
                OneToOneClientHandler oneToOneClientHandler = new OneToOneClientHandler(clientName, newClientSocket);  //setting newlyAddedClient - socket,reader,writer;
                oneToOneClientHandler.displayAvailableClients(); //displaying the available clients to newly added client  //write;
                oneToOneClientHandler.getClientResponce_WhomHeWantsToConnect();  //read
                Socket peerSocket = oneToOneClientHandler.setSocketFromClientResponce();
///
                while (!newClientSocket.isClosed() || !peerSocket.isClosed() ) {
                    new Thread(oneToOneClientHandler::readFromNewClientAndWriteToExistingClient).start();
                    new Thread(oneToOneClientHandler::readFromExistingClientAndWriteToNewClient).start();
                }
            }).start();
        }
    }
}