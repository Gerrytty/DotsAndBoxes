package display.windows;

import display.elements.GreenButton;
import display.elements.StageParams;
import game.Game;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;

public class MultiPlayer extends Application {

    @Override
    public void start(Stage stage) {

        System.out.println(Main.game.getMode());

        stage.setFullScreen(true);

        Text text = new Text("Choose number of players");
        text.setFill(Color.WHITE);
        text.setFont(Font.font("arial", 35));

        Button button1 = new GreenButton("2").getButton();
        Button button2 = new GreenButton("3").getButton();
        Button button3 = new GreenButton("4").getButton();
        Button button4 = new GreenButton("5").getButton();

        setActions(stage, button1, button2, button3, button4);

        Scene scene = new Scene(createFlowPane(text, button1, button2, button3, button4));

        new StageParams().setParams(stage, scene);

        stage.show();

    }

    private FlowPane createFlowPane(Text text, Button... buttons) {
        FlowPane pane = new FlowPane(Orientation.VERTICAL, 20, 20, text, buttons[0], buttons[1], buttons[2], buttons[3]);
        pane.setStyle("-fx-background-color: #09095f");
        pane.setAlignment(Pos.CENTER);
        return pane;
    }

    private void setActions(Stage stage, Button... buttons) {

        Arrays.stream(buttons).forEach(button -> button.setOnAction(event -> {
            setWidthAndHeight(Integer.parseInt(button.getText()));
            try {
                new WaitingWindow().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

    }


    /***
     * Set width and height in dependency from number of players
     ***/
    private void setWidthAndHeight(int numberOfPlayers) {

        int[][] arr = {
                {3, 2},
                {5, 4},
                {8, 6},
                {11, 9}
        };

        Game game = Main.game;
        game.setNumberOfPlayers(numberOfPlayers);
        game.setWidth(arr[numberOfPlayers - 2][0]);
        game.setHeight(arr[numberOfPlayers - 2][1]);

    }
}
