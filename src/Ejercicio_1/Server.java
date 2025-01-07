package Ejercicio_1;

/*
Ejercicio 3.1.-
Realiza un programa servidor TCP que acepte dos clientes. Muestra por cada
cliente conectado sus puertos local y remoto.
Crea también el programa cliente que se conecte a ese servidor. Muestra los
puertos locales y remotos a los que está conectado su socket, y la dirección IP de la
máquina remota a la que se conecta.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int PORT = 12345; // Puerto del servidor

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado. Esperando conexiones...");

            // Aceptar dos clientes
            for (int i = 1; i <= 2; i++) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente " + i + " conectado:");
                System.out.println("  Puerto local: " + clientSocket.getLocalPort());
                System.out.println("  Puerto remoto: " + clientSocket.getPort());
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
