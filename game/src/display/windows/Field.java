package display.windows;

import client.Client;
import client.PlayerMove;
import game.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utills.JSON;

import java.util.ArrayList;
import java.util.HashMap;

public class Field extends Application {

    private static int playerNumber = 0;

    private static String playerLetter = (char)(playerNumber + 65) + "";

    private static Field field;
    private static GridPane grid;
    private static Stage fieldStage;

    private static ArrayList<MyLine> freeLinesOnField;

    private static ArrayList<MyLine> allLines;
    private static ArrayList<Square> allSquares;

    private static int playerScore = 0;
    private static int computerScore = 0;

    private static boolean isMyMove = false;

    private static Player player;

    private Field() {
        grid = new GridPane();
    }

    public static Field getField() {
        if(field == null) {
            field = new Field();
        }

        return field;
    }

    private static HashMap<Point, ToggleButton> map = new HashMap<>();

    @Override
    public void start(Stage stage) {

        fieldStage = stage;

        generateField(Main.game.getHeight(), Main.game.getWidth());

        Scene scene = new Scene(grid);
        stage.setScene(scene);

        // size of the window
        stage.setWidth(600);
        stage.setHeight(500);

        stage.setOnCloseRequest(event -> System.exit(0));

        stage.show();

    }

    private void generateField(int height, int width) {

        int h = height * 2 + 1;
        int w = width * 2 + 1;

        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(true);

        grid.setStyle("-fx-background-color: #09095f");

        setColumns(grid, w);
        setRows(grid, h);

        ArrayList<Point> points = setPoints(h, w);

        HashMap<Point, ToggleButton> pointToButton = setButtons(grid, points);
        map = pointToButton;

        if(playerNumber != 0 && playerNumber != 1) {
            map.forEach((point, button) -> button.setDisable(true));
        }

        ArrayList<MyLine> listOfAllLines = new MyLine().initAllLines(h, w);
        freeLinesOnField = listOfAllLines;
        allLines = listOfAllLines;

        Main.game.setLines(freeLinesOnField);

        ArrayList<Square> squares = initAllSquares(h, w);
        allSquares = squares;

        setActions();

    }

    public static void setAllButtonsVisible() {
        map.forEach((point, button) -> button.setDisable(!isIsMyMove()));
    }

    private ArrayList<Square> initAllSquares(int h, int w) {
        ArrayList<Square> squares = new ArrayList<>();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if(i % 2 != 0 && j % 2 != 0) {
                    Square square = new Square(i, j);
                    square.initLines();
                    squares.add(square);
                }
            }
        }

        Main.game.setSquares(squares);

        return squares;
    }

    private void setColumns(GridPane gridPane, int w) {

        for (int i = 0; i < w; i++) {
            if(i % 2 != 0) {
                gridPane.getColumnConstraints().add(new ColumnConstraints(100));
            }
            else {
                gridPane.getColumnConstraints().add(new ColumnConstraints(20));
            }
        }
    }

    private void setRows(GridPane gridPane, int h) {

        for (int i = 0; i < h; i++) {
            if(i % 2 != 0) {
                gridPane.getRowConstraints().add(new RowConstraints(100));
            }
            else {
                gridPane.getRowConstraints().add(new RowConstraints(20));
            }
        }

    }

    private ToggleButton createButton() {

        ToggleButton button = new ToggleButton();

        button.setStyle("-fx-base: #ffffff");
        double r = 10;
        button.setShape(new Circle(r));
        button.setMinSize(2 * r, 2 * r);
        button.setMaxSize(2 * r, 2 * r);

        return button;

    }

    private HashMap<Point, ToggleButton> setButtons(GridPane gridPane, ArrayList<Point> points) {

        HashMap<Point, ToggleButton> buttons = new HashMap<>();

        points.forEach(point -> {
            ToggleButton button = createButton();
            buttons.put(point, button);
            gridPane.add(button, point.getX(), point.getY());
        });

        return buttons;
    }

    private void setActions() {

        map.forEach((point, toggleButton) -> toggleButton.setOnAction(event -> {

            point.setWaiting(true);

            ArrayList<Point> waitingPoints = new ArrayList<>();

            map.forEach((waitingPoint, button) -> {

                if(waitingPoint.isWaiting()) {
                    waitingPoints.add(waitingPoint);
                }
            });


            if(waitingPoints.size() == 2) {

                MyLine myLine = new MyLine(waitingPoints.get(0), waitingPoints.get(1));

                drawLine(myLine, playerLetter, true);
                waitingPoints.get(0).setWaiting(false);
                waitingPoints.get(1).setWaiting(false);

                Main.game.setLines(freeLinesOnField);

                if(Main.game.getMode().equals(Mode.SINGLE_PLAY)) {
                    String s = new JSON<Game>().createJSON(Main.game);
                    Client.sendMessage(s);
                }

                else {
                    PlayerMove playerMove = new PlayerMove();
                    playerMove.setMyMove(myLine);
                    Main.game.setPlayerMove(playerMove);
                    String s = new JSON<Game>().createJSON(Main.game);
                    Client.sendMessage(s);
                }

            }

        }));

    }

    public static void drawLine(MyLine myLine, String letter, boolean iDraw) {

        allLines.forEach(line -> {
            if(line.equals(myLine)) {
                line.setOnField(true);

                allSquares.forEach(square -> square.getLines().forEach(l -> {
                    if(l.equals(line)) {
                        l.setOnField(true);
                    }
                }));
            }
        });

        Line line = new Line();
        line.setStrokeWidth(5);
        line.setStroke(Color.WHITE);

        if(myLine.getPosition() == null) {
            System.out.println("Error in drawLine method");
        }

        else if(myLine.getPosition().equals(LinePosition.VERTICAL)) {
            line.setEndY(100);
            line.setTranslateX(8);

        }

        else {
            line.setEndX(100);
        }


        grid.add(line, myLine.getX(), myLine.getY());

        final Square[] mySquare = {null};

        allSquares.forEach(square -> {
            if(square.isSet()) {
                System.out.println("Square is set");
//                if(iDraw) {
//                    square.setLetter(playerLetter);
//                }
//                else {
//                    square.setLetter(letter);
//                }
                square.setLetter(letter);
                grid.add(createText(square.getLetter()), square.getX(), square.getY());
                mySquare[0] = square;
                if(letter.equals(playerLetter)) {
                    playerScore++;
                }
                else {
                    computerScore++;
                }
            }
        });

        if(mySquare[0] != null) {
            allSquares.remove(mySquare[0]);
        }

        freeLinesOnField.remove(myLine);

    }

    private static Text createText(String letter) {
        Text text = new Text(letter);
        text.setTranslateX(30);
        text.setFill(Color.WHITE);
        text.setStyle("-fx-font-size: 60px;");

        return text;
    }

    private ArrayList<Point> setPoints(int h, int w) {

        ArrayList<Point> list = new ArrayList<>();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if(i % 2 == 0 && j % 2 == 0) {
                    list.add(new Point(i, j));

                }
            }
        }

        Main.game.setPoints(list);

        return list;

    }

    public static Stage getFieldStage() {
        return fieldStage;
    }

    public static int getComputerScore() {
        return computerScore;
    }

    public static int getPlayerScore() {
        return playerScore;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Field.player = player;
    }

    public static void setPlayerNumber(int playerNumber) {
        Field.playerNumber = playerNumber;
    }

    public String getLetter() {
        return playerLetter;
    }

    public static void setLetter(String letter) {
        Field.playerLetter = letter;
    }

    public static boolean isIsMyMove() {
        return isMyMove;
    }

    public static void setIsMyMove(boolean isMyMove) {
        Field.isMyMove = isMyMove;
    }

    public static int getPlayerNumber() {
        return playerNumber;
    }

}
