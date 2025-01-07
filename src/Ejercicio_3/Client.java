package Ejercicio_3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void sendMessage(int message, Socket socket) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void receiveMessage(Socket socket){
        // Recibir mensaje del servidor
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String serverMessage = reader.readLine();
            System.out.println("Mensaje del servidor: " + serverMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String SERVER_IP = "127.0.0.1"; // Dirección IP del servidor
        int SERVER_PORT = 12345; // Puerto del servidor

        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
            System.out.println("Conectado al servidor: " + socket.getRemoteSocketAddress());

            Scanner sc = new Scanner(System.in);
            System.out.println("Indique un numero entero");
            int num = sc.nextInt();
            sendMessage(num, socket);

            receiveMessage(socket);

        } catch (IOException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }

    }
}
