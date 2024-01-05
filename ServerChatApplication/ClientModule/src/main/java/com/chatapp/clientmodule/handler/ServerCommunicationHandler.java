package com.chatapp.clientmodule.handler;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
public class ServerCommunicationHandler {
    private final Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    public ServerCommunicationHandler(Socket socket) {
        this.socket = socket;
        try {
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public void writeClientName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter your name : ");
        String clientName = scanner.nextLine();
        try {
            writer.write(clientName + "\n");
            writer.flush();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public String writeToServer() {
        Scanner scanner = new Scanner(System.in);
        String msg =  scanner.nextLine();
           try {
               writer.write(msg + "\n");
               writer.flush();
           } catch (IOException i) {
               i.printStackTrace();
           }
           return msg;
    }
    public void readAndPrintFromServer() {
        try {
            String msgFromServer = reader.readLine();
            System.out.println("server received : " + msgFromServer);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public void closeResources () {
        try {
            socket.close();
            writer.close();
            reader.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
