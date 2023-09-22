package Ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;
import java.util.Arrays;

public class Server {

    private static final int PORT = 9999;

    public static void main(String[] args) throws IOException {
        new frmClient().setVisible(true);
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server listening on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String str = in.readLine();
                int odd = 0;
                int even = 0;
                for (char c : str.toCharArray()) {
                    if ((c - '0') % 2 == 0) {
                        even++;
                    } else {
                        odd++;
                    }
                }
                out.println("Odd: " + odd + ", " + "Even: " + even);
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
