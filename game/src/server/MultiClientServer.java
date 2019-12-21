package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import display.windows.Field;
import game.Game;
import game.MyLine;
import game.Square;
import utills.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MultiClientServer {

    private ServerSocket serverSocket;
    private List<ClientHandler> clients;

    public MultiClientServer() {

        clients = new ArrayList<>();
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        for(;;) {
            try {
                ClientHandler handler = new ClientHandler(serverSocket.accept());
                handler.start();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private class ClientHandler extends Thread {

        private Socket clientSocket;
        private BufferedReader reader;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            clients.add(this);
            System.out.println("New client!");
        }

        @Override
        public void run() {

            System.out.println("in run");

            ObjectMapper mapper = new ObjectMapper();

            try {
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {

                    Move move = getMove(mapper, line);

                    JSON<Move> json = new JSON<>();

                    for (ClientHandler client : clients) {
                        PrintWriter writer = new PrintWriter(
                                client.clientSocket.getOutputStream(), true);
                        writer.println(json.createJSON(move));
                    }
                    System.out.println("Client message = " + line + "\n");
                    System.out.println("Server message = " + json.createJSON(move) + "\n");
                }


                reader.close();
                clientSocket.close();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        private Move getMove(ObjectMapper mapper, String s) {
            Move move = new Move();
            try {

                Game game = mapper.readValue(s, Game.class);

                ArrayList<MyLine> lines = game.getLines();
                MyLine line = lines.get(new Random().nextInt(lines.size()));
                move.setLine(line);
                move.setLines(game.getLines());
                move.setSquares(game.getSquares());

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }


            return move;
        }

    }
}