package Ex3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
                String name = in.readLine();
                name = name.trim();
                String eChar = "";
                String[] words = name.split(" ");
                for (String word : words) {
                    if (word.isEmpty()) {
                        continue;
                    }
                    char[] chars = word.toCharArray();
                    eChar += Character.toUpperCase(chars[0]);
                    for (int i = 1; i < chars.length; i++) {
                        eChar += Character.toLowerCase(chars[i]);
                    }
                    eChar += " ";
                }
                out.println(eChar.trim());

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
