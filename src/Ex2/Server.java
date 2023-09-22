package Ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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
                int n = Integer.parseInt(in.readLine());
                int[] arr = new int[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = Integer.parseInt(in.readLine());
                }
                Sort(arr);
                out.println(arr[0]);
                out.println(arr[1]);
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Sort(int[] Array) {
        int temp;
        for (int i = 0; i < Array.length; i++) {
            for (int j = 0; j < Array.length; j++) {
                if (Array[i] < Array[j]) {
                    temp = Array[i];
                    Array[i] = Array[j];
                    Array[j] = temp;
                }
            }
        }
    }
}
