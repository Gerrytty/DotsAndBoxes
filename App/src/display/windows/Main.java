package display.windows;

import client.Client;
import display.elements.GreenButton;
import display.elements.StageParams;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import game.*;

public class Main extends Application {

    final static Game game = new Game();

    public static void main(String[] args) {

        String ip = "127.0.0.1";
        int port = 7000;

        Client client = new Client();
        client.startConnection(ip, port);

        launch(args);

    }

    @Override
    public void start(final Stage stage) {

        Button singlePlay = new GreenButton("Single play").getButton();
        Button multiPlayer = new GreenButton("Multi player").getButton();

        singlePlay.setOnAction(event -> {

            game.setMode(Mode.SINGLE_PLAY);

            try {
                new SinglePlay().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        multiPlayer.setOnAction(event -> game.setMode(Mode.MULTI_PLAYER));

        Scene scene = new Scene(getPane(createText(), singlePlay, multiPlayer));

        new StageParams().setParams(stage, scene);

        stage.show();

    }

    private static Text createText() {

        Text text = new Text("Box and Dots");
        text.setFill(Color.WHITE);
        text.setStyle("-fx-font-size: 60px");

        return text;
    }

    private static GridPane getPane(Text text, Button singlePlay, Button multiPlayer) {
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #09095f");

        gridPane.setHgap(150);
        gridPane.setVgap(80);

        gridPane.setPadding(new Insets(0, 10, 0, 10));

        gridPane.add(text, 5, 2);
        gridPane.add(singlePlay, 5, 6);
        gridPane.add(multiPlayer, 5, 7);

        return gridPane;
    }
}
