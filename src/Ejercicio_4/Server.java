package Ejercicio_4;

/*
Escribe un programa servidor que pueda atender hasta 3 clientes. Debe enviar a
cada cliente un mensaje indicando el número de cliente que es. Este número será 1,
2 o 3. El cliente mostrará el mensaje recibido. Cambia el programa para que lo haga
con N clientes, siendo N un parámetro que tendrás que definir en el programa
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        int PORT = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado. Esperando conexiones...");

            System.out.println("Indique el numero de clientes");
            Scanner sc = new Scanner(System.in);
            int numClientes = sc.nextInt();
            System.out.println("Se ha fijado un limite de " + numClientes + " clientes en el servidor");

            // Aceptar n clientes
            for (int i = 1; i <= numClientes; i++) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente " + i + " conectado");

                //Envia el mensaje de conexion al cliente
                String message = "Bienvenido cliente " + i;
                try {
                    OutputStream outputStream = clientSocket.getOutputStream();
                    PrintWriter writer = new PrintWriter(outputStream, true);
                    writer.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
