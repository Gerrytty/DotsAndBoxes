package display;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import game.*;

public class Main extends Application {

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {

        final Game game = new Game();

        Button singlePlay = createButton("Single play");
        Button multiPlayer = createButton("Multi player");

        singlePlay.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                game.setMode(Mode.SINGLE_PLAY);
            }
        });

        multiPlayer.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                game.setMode(Mode.MULTI_PLAYER);
            }
        });

        Scene scene = new Scene(getPane(createText(), singlePlay, multiPlayer));

        setStageParams(stage, scene);

        stage.show();

    }

    private static Text createText() {
        Text text = new Text("Box and Dots");
        text.setFill(Color.WHITE);

        text.setStyle("-fx-font-size: 60px");

        return text;
    }

    private static Button createButton(String text) {

        Button button = new Button(text);

        button.setMinWidth(450);
        button.setMinHeight(60);
        button.setStyle("-fx-font: 22 arial; -fx-base: #218413; -fx-text-fill: #ffffff");

        return button;
    }

    private static void setStageParams(Stage stage, Scene scene) {

        stage.setScene(scene);
        stage.setTitle("Dots and Boxes");
        stage.setFullScreen(true);

        // size of the window
        stage.setWidth(600);
        stage.setHeight(500);

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
