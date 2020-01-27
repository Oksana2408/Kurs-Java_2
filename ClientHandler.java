package com.geekbrains.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler { //занимается обработкой клиента на стороне сервера. запискает поток, отправляет сообщения, закрывает поток.
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public ClientHandler(Server server, Socket socket) throws IOException { //нельзя гасить осгибки через try/catch
        this.server = server;
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                while (true) { //цикл аутентификации и подписки на рассылку
                    String msg = in.readUTF();
                    System.out.print("Сообщение от клиента: " + msg + "\n");
                    if(msg.startsWith("/auth ")) {
                        String [] tokens = msg.split(" ", 3); //разбивает строку на массив строк (на 3 ячейки)
                        String nickFromAuthManager =  server.getAuthManager().getNickNameByLoginAndPassword(tokens[1], tokens[2]);
                        if(nickFromAuthManager != null){
                            if(server.isNickBusy(nickFromAuthManager)){
                                sendMsg("Данный пользователь уже в чате");
                                continue;
                            }
                            nickname = nickFromAuthManager;
                            server.subscribe(this);
                            sendMsg("/authok " + nickname);
                            server.broadcastMsg(nickname + " online");
                            break;
                        } else {
                            sendMsg("Указан неверный логин/пароль");
                        }
                    }
                }
                while (true) { //цикл общения с сервером (обмен текстовыми соощениями)
                    String msg = in.readUTF();
                    System.out.print("Сообщение от клиента: " + msg + "\n");
                    if(msg.startsWith("/")){
                        if (msg.equals("/end")) {
                            sendMsg("/end_confirm");
                            break;
                        }
                    } else {
                        server.broadcastMsg(nickname + " " + msg);
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            } finally {
                close();
            }
        }).start();
    }

    public void sendMsg(String msg){ //метод отправления сообщения сервером
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(){ //метод закрытия серверного соединания
        server.broadcastMsg(nickname + " offline");
        server.unsubscribe(this); //отписать от рассылки обратившегося клиента
        nickname = null;
        if(in != null){
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       if(out != null){
           try {
               out.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       if(socket != null){
           try {
               socket.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

    }

}
