package Ejercicio_3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void sendMessage(int message, Socket clientSocket){
        int send = message*message;
        try {
            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println(send);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int receiveMessage(Socket clientSocket) {
        int clientMessage = 0;
        try {
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            clientMessage = Integer.parseInt(reader.readLine());
            System.out.println("Mensaje del cliente: " + clientMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientMessage;
    }

    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor iniciado. Esperando conexiones...");
            for (int i = 0; i < 1; i++) {
                Socket clientSocket = serverSocket.accept();
                int clientMessage = receiveMessage(clientSocket);
                sendMessage(clientMessage, clientSocket);
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
