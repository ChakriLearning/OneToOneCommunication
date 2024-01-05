package com.onetoonecommunication.servermodule.onetoonecommunicationhandler;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
public class OneToOneClientHandler {
    private Map<String, Socket> clientSockets = new HashMap<>();
    private Socket clientFrndSocket;
    private Socket presentClientSocket;
    private BufferedWriter clientFrndWriter;
    private BufferedReader clientFrndReader;
    private BufferedWriter presentClientWriter;
    private BufferedReader presentClientReader;
    public OneToOneClientHandler(Map<String, Socket> clientSockets, Socket presentClientSocket) {
        this.clientSockets = clientSockets;
        this.presentClientSocket = presentClientSocket;
    }
    public Socket connectToClientFriend(String clientFriend) {
        for(Map.Entry<String,Socket> entry : clientSockets.entrySet()) {
            if(clientFriend.equals(entry.getKey())) {
                this.clientFrndSocket = entry.getValue();
            }
        }
        return this.clientFrndSocket;
    }
    public void makeClientFrndSocket() {
        try {
            this.clientFrndReader = new BufferedReader(new InputStreamReader(this.clientFrndSocket.getInputStream()));
            this.clientFrndWriter = new BufferedWriter(new OutputStreamWriter(this.clientFrndSocket.getOutputStream()));
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public void makePresentClientSocket() {
        try {
            this.presentClientReader = new BufferedReader(new InputStreamReader(this.presentClientSocket.getInputStream()));
            this.presentClientWriter = new BufferedWriter(new OutputStreamWriter(this.presentClientSocket.getOutputStream()));
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void readPresendClientAndWriteToFrnd() {
        String msg = "";
        while (!msg.equals("bye")) {
            try {
                msg = presentClientReader.readLine();
                clientFrndWriter.write(msg + "\n");
                clientFrndWriter.flush();
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

    public void readFromFrndAndWriteToPresent() {
        String msg = "";
        while (!msg.equals("bye")) {
            try {
                msg = clientFrndReader.readLine();
                presentClientWriter.write(msg);
                presentClientWriter.write("\n");
                presentClientWriter.flush();
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }
}
