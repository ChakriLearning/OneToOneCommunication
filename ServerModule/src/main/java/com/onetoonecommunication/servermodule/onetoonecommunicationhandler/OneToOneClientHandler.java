package com.onetoonecommunication.servermodule.onetoonecommunicationhandler;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class OneToOneClientHandler {
    private static Map<String,Socket> clientSockets = new HashMap<>();
    private Socket newClientSocket;
    private BufferedReader newClientReader;
    private BufferedWriter newClientWriter;
    private String newClientName;
    private Socket existingClientSocket;
    private String existingClientName;
    private BufferedWriter existingClientWriter;
    private BufferedReader existingClientReader;

    private BufferedReader presentClientReader;
    public OneToOneClientHandler(String newClientName, Socket newClientSocket) {
        this.newClientName = newClientName;                 ///constructor
        this.newClientSocket = newClientSocket;
        clientSockets.put(newClientName,newClientSocket);
        try {                                               ///setting newlyAdded client readers,writers,socket
            this.newClientReader = new BufferedReader(new InputStreamReader(this.newClientSocket.getInputStream()));
            this.newClientWriter = new BufferedWriter(new OutputStreamWriter(this.newClientSocket.getOutputStream()));
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public void displayAvailableClients() {
        List<String> availableClients = new ArrayList<>();
        for(Map.Entry<String,Socket> entry : clientSockets.entrySet()) {  //displaying usernames to newlyAddedClient;
            availableClients.add(entry.getKey());
        }
        try {
            newClientWriter.write(availableClients + "\n");
            newClientWriter.flush();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public void getClientResponce_WhomHeWantsToConnect() {
        try {
            this.existingClientName = this.newClientReader.readLine();  //this is newlyAddedClient
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public Socket setSocketFromClientResponce() {
        for(Map.Entry<String,Socket> entry : clientSockets.entrySet()) {
            if(existingClientName.equals(entry.getKey())) {           //setting existing client readers,writers,socket;
                try {
                    this.existingClientSocket = entry.getValue();
                    this.existingClientReader = new BufferedReader(new InputStreamReader(this.existingClientSocket.getInputStream()));
                    this.existingClientWriter = new BufferedWriter(new OutputStreamWriter(this.existingClientSocket.getOutputStream()));
                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
        }
        return this.existingClientSocket;
    }
    public void readFromExistingClientAndWriteToNewClient() {
        String msg = "";
        do {
            try {
                msg = existingClientReader.readLine();
                newClientWriter.write("[" + existingClientName + "]" + msg + "\n");
                newClientWriter.flush();
            } catch (IOException i) {
                i.printStackTrace();
            }
        } while (!msg.equals("bye"));
    }
    public void readFromNewClientAndWriteToExistingClient() {
        String msg = "";
        do {
            try {
                msg = newClientReader.readLine();
                existingClientWriter.write("[" + newClientName + "] : "+ msg + "\n");
                existingClientWriter.flush();
            } catch (IOException i) {
                i.printStackTrace();
            }
        } while (!msg.equals("bye"));
    }
}
