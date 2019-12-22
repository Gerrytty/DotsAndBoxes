package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.Game;
import game.Mode;
import game.MyLine;
import utills.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiClientServer {

    private ServerSocket serverSocket;
    private List<ClientHandler> clients;

    private boolean gameIsStarted = false;

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

                    Game game = mapper.readValue(line, Game.class);

                    String serverMessage;

                    if(game.getMode().equals(Mode.SINGLE_PLAY)) {

                        Move move = computerMove(game);

                        JSON<Move> json = new JSON<>();

                        serverMessage = json.createJSON(move);

                    }

                    else {
                        serverMessage = "new client";
                        int numberOfClients = clients.size();
                        System.out.println("Current number of clients = " + numberOfClients);
                        System.out.println("Should be " + game.getNumberOfPlayers());

                        if(numberOfClients == game.getNumberOfPlayers() && !gameIsStarted) {
                            serverMessage = "start";
                            gameIsStarted = true;
                        }

                        else {
                            System.out.println("Game in process or little players");
                        }
                    }

                    for (ClientHandler client : clients) {
                        PrintWriter writer = new PrintWriter(
                                client.clientSocket.getOutputStream(), true);
                        writer.println(serverMessage);
                    }
                }


                reader.close();
                clientSocket.close();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        private Move computerMove(Game game) {

            Move move = new Move();

            ArrayList<MyLine> lines = game.getLines();

            int size = lines.size();

            if(size > 0) {
                MyLine line = lines.get(new Random().nextInt(size));
                move.setLine(line);
            }
            else {
                System.out.println("Game over");
                move.setGameOver(true);
            }

            return move;
        }

    }
}