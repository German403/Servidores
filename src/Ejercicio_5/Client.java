package Ejercicio_5;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static void sendWords(Socket socket) {
        Scanner sc = new Scanner(System.in);
        String word;

        while (true) {
            System.out.println("Introduce palabras. Para terminar, introduce un asterisco (*)");
            word = sc.nextLine();
            try {
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(outputStream, true);
                writer.println(word);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (word.equals("*")) {
                break;
            }
        }

    }

    public static String receiveWord(Socket socket) {
        String word = "";
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            word = reader.readLine();
            System.out.println(word);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return word;
    }

    public static void main(String[] args) {
        String SERVER_IP = "127.0.0.1"; // Dirección IP del servidor
        int SERVER_PORT = 12345; // Puerto del servidor
        String word;

        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
            System.out.println("Conectado al servidor: " + socket.getRemoteSocketAddress());

            sendWords(socket);
            word = receiveWord(socket);
            System.out.println(word);
        } catch (IOException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}