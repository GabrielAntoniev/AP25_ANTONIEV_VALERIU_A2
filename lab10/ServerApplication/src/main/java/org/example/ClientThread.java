package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private final GameServer server;
    private final Socket socket;

    public ClientThread(GameServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String request;
            while ((request = in.readLine()) != null) {
                if (request.equalsIgnoreCase("stop")) {
                    out.println("Server stopped");
                    server.stop();
                    break;
                } else {
                    out.println("Server received The request: " + request);
                }
            }
        } catch (IOException e) {
            System.err.println("Client error:" + e.getMessage());
        }
    }
}