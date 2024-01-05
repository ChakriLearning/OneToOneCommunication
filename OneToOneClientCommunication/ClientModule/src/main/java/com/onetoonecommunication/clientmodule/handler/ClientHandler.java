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
        String userName = "";
        do {
            userName = scan.nextLine();
        } while (userName.length() >=4)
    }

    public void takeAndGiveWhomToChat() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(reader.readLine());
            System.out.println("whom to chat : ");
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
