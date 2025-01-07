package Ejercicio_2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor iniciado. Esperando conexiones...");
            for (int i = 0; i < 1; i++) {
                Socket clientSocket = serverSocket.accept();
                OutputStream outputStream = clientSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(outputStream, true);
                writer.println("PRUEBA");

                InputStream inputStream = clientSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String clientMessage = reader.readLine();
                System.out.println("Mensaje del cliente: " + clientMessage);
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
