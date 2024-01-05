package com.onetoonecommunication.clientmodule.handler;

import java.io.IOException;
import java.net.Socket;

public class SampleClient {
    private String clientIpAddress;
    private int clientPort;
    private Socket socket;
    public SampleClient (String clientIpAddress, int clientPort) {
        this.clientIpAddress = clientIpAddress;
        this.clientPort = clientPort;
        System.out.println("Client is running on IP Address : " + clientIpAddress + " And Port : " + clientPort);
    }
    public Socket connectToServer() {
        try {
            this.socket = new Socket(this.clientIpAddress, this.clientPort);
        } catch (IOException i) {
            i.printStackTrace();
        }
        return this.socket;
    }
}
