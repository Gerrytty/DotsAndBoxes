package display.windows;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MultiPlayerGameOver extends Application {

    private GridPane gridPane;

    @Override
    public void start(Stage stage) {
        generateField();
        addText();

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);

        // size of the window
        stage.setWidth(600);
        stage.setHeight(500);

        stage.setOnCloseRequest(event -> System.exit(0));

        stage.show();

    }

    private void generateField() {

        gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #09095f");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(50);
        gridPane.setVgap(50);

    }

    private void addText() {

        int computerPoints = Field.getComputerScore();
        int userPoints = Field.getPlayerScore();

        Text gameOver = createText("Game over", 60, Color.WHITE);
        Text computerScore = createText("Other player points: " + computerPoints, 30, Color.WHITE);
        Text userScore = createText("Your points: " + userPoints, 30, Color.WHITE);

        Text whoWin = computerPoints < userPoints ? createText("You win!", 50, Color.GREEN) :
                computerPoints > userPoints ? createText("Other player win! :(", 50, Color.RED) :
                        createText("Dead heat", 50, Color.WHITE);

        gridPane.add(gameOver, 0, 0);
        gridPane.add(computerScore, 0, 2);
        gridPane.add(userScore, 0, 3);
        gridPane.add(whoWin, 0, 4);

    }

    private Text createText(String text, int size, Color color) {
        Text t = new Text();
        t.setText(text);
        t.setFill(color);
        t.setStyle("-fx-font-size: " + size + "px");
        return t;
    }

}