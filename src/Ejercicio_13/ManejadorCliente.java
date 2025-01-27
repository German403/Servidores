package Ejercicio_13;

import java.io.*;
import java.net.*;

public class ManejadorCliente implements Runnable {
    private final Socket clientSocket;

    public ManejadorCliente(Socket serverSocket) {
        this.clientSocket = serverSocket;
    }

    private String removeVowels(String str) {
        return str.replaceAll("[aeiouAEIOUáéíóúÁÉÍÓÚ]", "");
    }

    @Override
    public void run() {
        String receivedMessage;
        try (
                //Creamos los flujos de entrada y salida que utilizara el programa
                DataInputStream entry = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream exit = new DataOutputStream(clientSocket.getOutputStream());
        ) {
            while(true) {
                try {
                    //Leer la cadena recivida
                    receivedMessage = entry.readUTF();
                } catch (EOFException e) {
                    System.out.println("Conexion con el cliente terminada");
                    break;
                }

                if (receivedMessage.equals("*")) {
                    break;
                }

                System.out.println("Mensaje recivido del cliente: " + receivedMessage);

                //Eliminar las vocales del mensaje
                String modifiedMessage = removeVowels(receivedMessage);
                //System.out.println("Mensaje modificado: " + modifiedMessage);

                //Enviamos la cadena modificada
                exit.writeUTF(modifiedMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
