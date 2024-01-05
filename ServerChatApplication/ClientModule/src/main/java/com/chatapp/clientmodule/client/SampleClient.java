package com.chatapp.clientmodule.client;

import java.io.IOException;
import java.net.*;

public class SampleClient {
    private final String ipAddress;
    private final int port;
    public SampleClient(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public Socket connectToServer() {
        try {
            return new Socket(ipAddress,port);
        } catch (IOException i) {
            i.printStackTrace();
        }
        return null;
    }
}
