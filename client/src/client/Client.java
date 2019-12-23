package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import display.windows.Field;
import display.windows.Main;
import display.windows.SinglePlayGameOver;
import game.Mode;
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

    private static int playerNumber;

    private Mode mode;

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

                    System.out.println(message);

                    if(message.startsWith("new client with number")) {

                        int numberOfThisPlayer = Integer.parseInt(message.charAt(message.length() - 1) + "");
                        playerNumber = numberOfThisPlayer;

                        Platform.runLater(() -> {
                            if(Field.getPlayerNumber() == 0) {
                                Field.setPlayerNumber(numberOfThisPlayer);
                                Field.setLetter((char)(numberOfThisPlayer + 64) + "");
                            }
                        });
                    }

                    else if(message.startsWith("start")) {
                        int numberOfThisPlayer = Integer.parseInt(message.charAt(message.length() - 1) + "");
                        playerNumber = numberOfThisPlayer;

                        Platform.runLater(() -> {
                            if(Field.getPlayerNumber() == 0) {
                                Field.setPlayerNumber(numberOfThisPlayer);
                                Field.setLetter((char)(numberOfThisPlayer + 64) + "");
                                Field.setIsMyMove(false);
                            }
                            Field.getField().start(Main.getMainStage());
                        });
                    }

                    else if(message.equals("game")) {
                        System.out.println("Game in progress");
                    }

                    else {

                        ObjectMapper objectMapper = new ObjectMapper();

                        Move move = null;

                        try {
                            move = objectMapper.readValue(message, Move.class);
                            if(!move.isGameOver()) {
                                Move finalMove = move;
                                Platform.runLater(() -> Field.drawLine(finalMove.getLine(), "C", true));
                            }

                            else {
                                System.out.println("Game over");
                                Platform.runLater(() -> new SinglePlayGameOver().start(Main.getMainStage()));
                            }
                        }
                        catch (Exception e) {
                            System.out.println("Not single play");
                        }

                        if(move == null) {
                            PlayerMove playerMove = objectMapper.readValue(message, PlayerMove.class);
                            Platform.runLater(() -> {
                                Field.drawLine(playerMove.getMyMove(), playerMove.getPastLetter(), false);
                                Field.setIsMyMove(playerMove.getWhoMove() == Field.getPlayerNumber());
                                Field.setAllButtonsVisible();
                                System.out.println("-----");
                                System.out.println(playerMove.getWhoMove());
                                System.out.println(playerNumber);
                                System.out.println(playerMove.getWhoMoveLetter());
                            });
                        }

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








