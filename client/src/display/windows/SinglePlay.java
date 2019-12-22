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

public class SinglePlay extends Application {

    public void start(Stage stage) {

        stage.setFullScreen(true);

        Text text = new Text("Choose size of the field");
        text.setFill(Color.WHITE);
        text.setFont(Font.font("arial", 35));

        Button button1 = new GreenButton("3x2").getButton();
        Button button2 = new GreenButton("5x4").getButton();
        Button button3 = new GreenButton("8x6").getButton();
        Button button4 = new GreenButton("11x9").getButton();

        setActions(stage, button1, button2, button3, button4);

        FlowPane pane = new FlowPane(Orientation.VERTICAL, 20, 20, text, button1, button2, button3, button4);
        pane.setStyle("-fx-background-color: #09095f");
        pane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(pane);

        new StageParams().setParams(stage, scene);

        stage.show();

    }

    private void setActions(Stage stage, Button... buttons) {

        Game game = Main.game;

        Arrays.stream(buttons).forEach(button -> button.setOnAction(event -> {
            String[] strings = button.getText().split("x");
            game.setHeight(Integer.parseInt(strings[1]));
            game.setWidth(Integer.parseInt(strings[0]));

            try {
                Field.getField().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }));

    }
}
