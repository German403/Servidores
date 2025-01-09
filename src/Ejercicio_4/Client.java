package Ejercicio_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String SERVER_IP = "127.0.0.1"; // Dirección IP del servidor
        final int SERVER_PORT = 12345; // Puerto del servidor

        System.out.println("Indique el numero de clientes");
        Scanner sc = new Scanner(System.in);
        int numClientes = sc.nextInt();

        try {
            for (int i = 0; i < numClientes; i++) {
                Thread cliente = new Thread(() -> connectToServer(SERVER_IP, SERVER_PORT));
                cliente.start();
                cliente.join();
            }
        } catch (InterruptedException e) {
            System.err.println("Error al esperar la finalización de los hilos: " + e.getMessage());
        }
    }

    private static void connectToServer(String serverIp, int serverPort) {
        try (Socket socket = new Socket(serverIp, serverPort)) {
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String serverMessage = reader.readLine();
            System.out.println(serverMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
