package Ejercicio_2;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String SERVER_IP = "127.0.0.1"; // Dirección IP del servidor
        int SERVER_PORT = 12345; // Puerto del servidor

        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
            System.out.println("Conectado al servidor: " + socket.getRemoteSocketAddress());

            // Recibir mensaje del servidor
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String serverMessage = reader.readLine();
            System.out.println("Mensaje del servidor: " + serverMessage);

            String clientMessage = serverMessage.toLowerCase();
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println(clientMessage);

        } catch (IOException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }

    }
}
