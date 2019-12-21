package display.windows;

import client.Client;
import game.*;
import javafx.application.Application;
import javafx.application.Platform;
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

    private static Field field;
    private static boolean isVisible = true;
    private static GridPane grid;

    private static MyLine lineFromServer;

    private ArrayList<MyLine> freeLinesOnField;

    private Field() {
        grid = new GridPane();
    }

    public static Field getField() {
        if(field == null) {
            field = new Field();
        }

        return field;
    }

    @Override
    public void start(Stage stage) {

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

        ArrayList<MyLine> listOfAllLines = new MyLine().initAllLines(h, w);
        freeLinesOnField = listOfAllLines;

        Main.game.setLines(freeLinesOnField);

        ArrayList<Square> squares = initAllSquares(h, w);

        setActions(listOfAllLines, squares, pointToButton);

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

    private void setActions(ArrayList<MyLine> allLines,
                            ArrayList<Square> squares, HashMap<Point, ToggleButton> map) {

        System.out.println(squares.size());

        map.forEach((point, toggleButton) -> toggleButton.setOnAction(event -> {

            point.setWaiting(true);

            ArrayList<Point> waitingPoints = new ArrayList<>();

            map.forEach((waitingPoint, button) -> {

                if(waitingPoint.isWaiting()) {
                    waitingPoints.add(waitingPoint);
                }
            });


            if(waitingPoints.size() == 2) {
                System.out.println("Player drawing line");

                MyLine myLine = new MyLine(waitingPoints.get(0), waitingPoints.get(1));

                drawLine(allLines, squares, myLine);
                waitingPoints.get(0).setWaiting(false);
                waitingPoints.get(1).setWaiting(false);
                isVisible = false;

                freeLinesOnField.remove(myLine);
                Main.game.setLines(freeLinesOnField);

                String s = new JSON<Game>().createJSON(Main.game);
                Client.sendMessage(s);

                if(lineFromServer != null) {
                    System.out.println("Computer will drawing");
                    drawLine(allLines, squares, lineFromServer);
                    lineFromServer = null;
                }
                else {
                    System.out.println("too long");
                }

                isVisible = true;

            }

        }));

    }

    public static void drawLine(ArrayList<MyLine> allLines,
                          ArrayList<Square> squares, MyLine myLine) {

        allLines.forEach(line -> {
            if(line.equals(myLine)) {
                line.setOnField(true);
                System.out.println("Line set of field = true");
                squares.forEach(square -> square.getLines().forEach(l -> {
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

        squares.forEach(square -> {
            if(square.isSet()) {
                System.out.println("Square is set");
                square.setLetter(Main.game.getPlayer().getLetter());
                grid.add(createText(square.getLetter()), square.getX(), square.getY());
            }
        });

    }

    private static Text createText(String letter) {
        Text text = new Text(letter);
        text.setTranslateX(30);
        text.setFill(Color.WHITE);
        text.setStyle("-fx-font-size: 60px;");

        return text;
    }

    public static void setIsVisible(boolean isVisible) {
        Field.isVisible = isVisible;
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

    public static MyLine getLineFromServer() {
        return lineFromServer;
    }

    public static void setLineFromServer(MyLine line) {
        lineFromServer = line;
    }
}
