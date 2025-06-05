package P2Pv2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Peer2 {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java P2Pv2.Peer2 <localPort> <remotePort> <peerIP>");
            return;
        }

        int localPort = Integer.parseInt(args[0]);
        int remotePort = Integer.parseInt(args[1]);
        String peerIP = args[2];

        startPeer(localPort, remotePort, peerIP, "Peer1");
    }

    public static void startPeer(int localPort, int remotePort, String peerIP, String peerName) {
        // Server thread
        Thread serverThread = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(localPort)) {
                System.out.println("Peer2 (Server) listening on port " + localPort);
                while (true) {
                    try (
                        Socket clientSocket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                    ) {
                        String message = in.readLine();
                        System.out.println(peerName + " says: " + message);
                    } catch (IOException e) {
                        System.out.println("Error receiving message: " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                System.out.println("Could not start server on port " + localPort);
                e.printStackTrace();
            }
        });

        serverThread.start();

        // Client loop
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Type messages to send to " + peerName + ". Type 'exit' to quit.");
            while (true) {
                String msg = scanner.nextLine();
                if ("exit".equalsIgnoreCase(msg)) {
                    System.out.println("Exiting...");
                    break;
                }
                try (
                    Socket socket = new Socket(peerIP, remotePort);
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
                ) {
                    out.println(msg);
                } catch (IOException e) {
                    System.out.println("Could not send message: " + e.getMessage());
                }
            }
        }
    }
}
