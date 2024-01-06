package com.onetoonecommunication.clientmodule.handler;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    public ClientHandler(Socket socket) {
        this.socket = socket;
        try {
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void registerUserName() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter User Name : ");
        String userName = scan.nextLine();
        try {
            writer.write(userName + "\n");
            writer.flush();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public void listActiveUsers() {
        try {
            System.out.println(reader.readLine());
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public void connectWithPeer() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("whom to Connect : ");
            String frndName = scanner.nextLine();
            writer.write(frndName + "\n");
            writer.flush();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void readFromFriend() {
        String friendMsg = "";
        try {
            friendMsg = reader.readLine();
            System.out.println(friendMsg);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public String writeToFriend() {
        Scanner scanner = new Scanner(System.in);
        String msg = scanner.nextLine();
        if(msg.equals("bye")) closeResources();
        try {
            writer.write(msg + "\n");
            writer.flush();
        } catch (IOException i) {
            i.printStackTrace();
        }
        return msg;
    }
    public void closeResources() {
        try {
            socket.close();
            writer.close();
            reader.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
