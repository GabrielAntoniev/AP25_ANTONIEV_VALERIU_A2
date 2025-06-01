package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private boolean running = true;

    public GameServer(int port) throws IOException {
        ServerSocket serverSocket = null;
        try  {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            while (running) {
                Socket clientSocket = serverSocket.accept();
                new ClientThread(this, clientSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Ooops... " + e.getMessage());
            e.printStackTrace();
        }
        finally{
            assert serverSocket != null;
            serverSocket.close();
        }
    }

    public void stop() {
        System.out.println("Server stopped for an user");
        running = false;
    }

    public static void main(String[] args) throws IOException {
        new GameServer(5000);
    }
}