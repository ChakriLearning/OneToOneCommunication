package com.onetoonecommunication.servermodule.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SampleServer {
    private int serverPort;
    public SampleServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public ServerSocket runServer() {
        try {
            return new ServerSocket(this.serverPort);
        } catch (IOException i) {
            i.printStackTrace();
        }
        return null;
    }

    public Socket acceptClientRequest(ServerSocket serverSocket) {
        try {
            return serverSocket.accept();
        } catch (IOException i) {
            i.printStackTrace();
        }
        return null;
    }
}
