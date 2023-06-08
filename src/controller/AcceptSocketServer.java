package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class AcceptSocketServer extends Thread {
    private static ArrayList<Socket> acceptSocket = new ArrayList<>();
    private Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String username;


    public AcceptSocketServer(Socket socket, String messageUsername) {
        this.socket = socket;
        this.username = messageUsername;

        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String message = username + " joined the chat room. ";
            for (Socket accept : acceptSocket) {
                DataOutputStream data = new DataOutputStream(accept.getOutputStream());
                data.writeUTF("\n"+ message);
                data.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        acceptSocket.add(socket);

    }


    @Override
    public void run() {
        try {
            while (true) {
                String s = dataInputStream.readUTF();
                for (Socket accept1 : acceptSocket) {

                    if (accept1.getPort() == socket.getPort()) {
                        continue;
                    }
                    DataOutputStream dos = new DataOutputStream(accept1.getOutputStream());
                    dos.writeUTF(s);
                    dos.flush();
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
