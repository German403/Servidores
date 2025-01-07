package Ejercicio_1;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        final String SERVER_IP = "127.0.0.1"; // Dirección IP del servidor
        final int SERVER_PORT = 12345; // Puerto del servidor

        // Crear dos hilos para realizar dos conexiones simultáneas
        Thread client1 = new Thread(() -> connectToServer(SERVER_IP, SERVER_PORT, "Cliente 1"));
        Thread client2 = new Thread(() -> connectToServer(SERVER_IP, SERVER_PORT, "Cliente 2"));

        // Iniciar ambos hilos
        // Esperar a que ambos hilos terminen (opcional)
        try {
            client1.start();
            client1.join();
            client2.start();
            client2.join();
        } catch (InterruptedException e) {
            System.err.println("Error al esperar la finalización de los hilos: " + e.getMessage());
        }
    }

    private static void connectToServer(String serverIp, int serverPort, String clientName) {
        try (Socket socket = new Socket(serverIp, serverPort)) {
            System.out.println(clientName + " conectado al servidor:");
            System.out.println("  Puerto local: " + socket.getLocalPort());
            System.out.println("  Puerto remoto: " + socket.getPort());
            System.out.println("  Dirección IP remota: " + socket.getInetAddress().getHostAddress());
        } catch (IOException e) {
            System.err.println(clientName + " no pudo conectarse: " + e.getMessage());
        }
    }
}
