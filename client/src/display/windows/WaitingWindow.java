package display.windows;

import client.Client;
import game.Game;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utills.JSON;

public class WaitingWindow extends Application {

    @Override
    public void start(Stage stage) {

        stage.setWidth(800);
        stage.setHeight(500);

        FlowPane pane = new FlowPane();
        pane.setStyle("-fx-background-color: #09095f");
        pane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane);

        Text waiting = new Text("We are waiting other players");
        waiting.setFill(Color.WHITE);
        waiting.setStyle("-fx-font-size: 50px");
        pane.getChildren().add(waiting);

        stage.setScene(scene);
        stage.show();

        Client.sendMessage( new JSON<Game>().createJSON(Main.game));

    }
}
