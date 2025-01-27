package Ejercicio_13;

/*
El servidor abrirá el puerto 11223 y atenderá clientes en un bucle infinito a través de hilos ManejadorCliente. El cliente conectará al servidor en la dirección “localhost” en el puerto 11223y
creará dos objetos DataInputStream y DataOutputStream para gestionar los
flujos de información. Solicitará al usuario cadenas de texto desde el teclado hasta
que se introduzca un asterisco (*) y se las enviará al servidor. Una vez recibida la
respuesta la mostrará por pantalla.
 */



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 11223;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor iniciado esperando conexiones en el puerto " + port);
            while (true) {
                //Aceptamos al cliente
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                //Creamos un hilo ManejarCliente y lo iniciamos
                Thread client = new Thread(new ManejadorCliente(clientSocket));
                client.start();
                
                //Esperamos a que el hilo finalice
                client.join();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
