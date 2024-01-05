package com.chatapp.servermodule.server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class SampleServer {
    private final int port;
    private ServerSocket serverSocket;
    public SampleServer(int port) {
        this.port = port;
    }
    public ServerSocket startServer() {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("server is running on port :- " + port);
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return this.serverSocket;
    }
    public Socket acceptClientConnection() {
        try {
            return serverSocket.accept();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return null;
    }
    public void stopServer() {
        try {
            serverSocket.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
