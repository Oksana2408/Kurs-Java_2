package com.geekbrains.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainApp {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидаем подключения клиентов...");
            Socket socket = serverSocket.accept(); //ожидание подключения клиента к серверу
            System.out.println("Клиент подключился");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String msg = in.readUTF();
                if(msg.equals("/end")){
                    socket.close();
                }else{
                    System.out.print("Сообщение от клиента: " + msg + "\n");
                    out.writeUTF("echo: " + msg);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
