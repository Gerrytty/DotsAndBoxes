package client;

import java.util.Scanner;
import display.windows.Field;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientMain extends Application {

    public static void main(String[] args) {

        String ip = "127.0.0.1";
        int port = 7000;

        Client client = new Client();

        client.startConnection(ip, port);

        launch(args);

        Scanner sc = new Scanner(System.in);

        while (true) {
            String message = sc.nextLine();
            client.sendMessage(message);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}