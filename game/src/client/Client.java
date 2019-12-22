package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import display.windows.Field;
import display.windows.SinglePlayGameOver;
import javafx.application.Platform;
import server.Move;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static Socket clientSocket;
    private static PrintWriter writer;
    private static BufferedReader reader;

    private static Client client;

    private Client() {

    }

    public static Client getClient() {

        if(client == null) {
            return new Client();
        }

        else return client;
    }

    public static void startConnection(String ip, int port) {

        try {
            clientSocket = new Socket(ip, port);
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
            reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            new Thread(receiveMessagesTask).start();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    public static void sendMessage(String message) {
        writer.println(message);
    }

    private static Runnable receiveMessagesTask = new Runnable() {
        public void run() {
            while (true) {
                try {
                    String message = reader.readLine();

                    if(message == null) {
                        closeConnection();
                        System.exit(0);
                    }

                    ObjectMapper objectMapper = new ObjectMapper();
                    Move move = objectMapper.readValue(message, Move.class);

                    if(!move.isGameOver()) {
                        Platform.runLater(() -> Field.drawLine(move.getLine(), "C"));
                    }

                    else {
                        System.out.println("Game over");
                        Platform.runLater(() -> new SinglePlayGameOver().start(Field.getFieldStage()));
                    }

                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    };

    public static void closeConnection() {

        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}








