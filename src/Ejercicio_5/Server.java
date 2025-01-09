package Ejercicio_5;

/*
Usando sockets TCP realiza un programa cliente que introduzca cadenas por
teclado hasta introducir un asterisco. Las cadenas se enviarán a un programa
servidor. El programa servidor lo muestra en pantalla y lo devuelve al cliente. El
cliente lo recibe y lo muestra en pantalla.
 */

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
    public static String reciveWords(Socket clientSocket) {
        String word = "";
        try {
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            word = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return word;
    }

    public static void sendWords(Socket clientSocket, ArrayList<String> words) {
        for (String word : words) {
            try {
                OutputStream outputStream = clientSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(outputStream, true);
                writer.println(word);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String word;
        ArrayList<String> words = new ArrayList<>();
        int PORT = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado. Esperando conexiones...");
            Socket clientSocket = serverSocket.accept();

            while (true) {
                word = reciveWords(clientSocket);
                if (word.equals("*")) {
                    System.out.println("El cliente ha cerrado la transmision de palabras");
                    break;
                }
                System.out.println("La palabra enviada por el cliente: " + word);
                words.add(word);
            }
            sendWords(clientSocket, words);
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
