package Ex4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;
import java.util.Arrays;

import static java.util.Arrays.copyOfRange;

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
                int index = Integer.parseInt(in.readLine());
                out.println(Arrays.toString(moveElement(arr, index)));

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] moveElement(int[] Array, int index) {
        int len = Array.length;
        int[] res = new int[len];
        for (int i = 0; i < len - index; i++) {
            res[i] = Array[index + i];
        }
        for (int i = 0; i < index; i++) {
            res[len - index + i] = Array[i];
        }
        return res;
    }
}
