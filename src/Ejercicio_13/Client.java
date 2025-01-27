package Ejercicio_13;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String host = "localhost";
        final int port = 11223;
        String message;
        Scanner sc = new Scanner(System.in);

        try (Socket clientSocket = new Socket(host, port);
             DataInputStream entry = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream exit = new DataOutputStream(clientSocket.getOutputStream());
        ) {
            while (true) {
                System.out.println("Introduce una cadena de texto o '*' para salir");
                message = sc.nextLine();

                if (message.equals("*")) {
                    System.out.println("Cerrando el programa");
                    break;
                }

                System.out.println("Mensaje enviado al servidor: " + message);

                //Enviar el mensaje
                exit.writeUTF(message);

                //Recibir la cadena modificada
                String response = entry.readUTF();
                System.out.println("Respuesta del servidor: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
