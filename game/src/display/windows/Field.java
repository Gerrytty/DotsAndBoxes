package display.windows;

import game.LinePosition;
import game.MyLine;
import game.Point;
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
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class Field extends Application {

    @Override
    public void start(Stage stage) {

        GridPane gridPane = generateField(Main.game.getHeight(), Main.game.getWidth());

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.setFullScreen(true);

        // size of the window
        stage.setWidth(600);
        stage.setHeight(500);

        stage.show();


    }

    private GridPane generateField(int height, int width) {

        int h = height * 2 + 1;
        int w = width * 2 + 1;

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(true);

        gridPane.setStyle("-fx-background-color: #09095f");

        setColumns(gridPane, w);
        setRows(gridPane, h);

        ArrayList<Point> points = setPoints(h, w);
        setNeighbors(points);

        HashMap<Point, ToggleButton> pointToButton = setButtons(gridPane, points);

        setActions(gridPane, pointToButton);

        return gridPane;
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

    private void setActions(GridPane gridPane, HashMap<Point, ToggleButton> map) {

        map.forEach((point, toggleButton) -> toggleButton.setOnAction(event -> {

            ArrayList<Point> neighbors = point.getNeighbors();

            neighbors.forEach(neighbor -> map.forEach((point1, toggleButton1) -> {

                if(point1.equals(neighbor)) {
                    makeGreen(toggleButton1);
                }

            }));

            point.setWaiting(true);

            ArrayList<Point> waitingPoints = new ArrayList<>();

            map.forEach((waitingPoint, button) -> {

                if(waitingPoint.isWaiting()) {
                    waitingPoints.add(waitingPoint);
                }
            });


            if(waitingPoints.size() == 2) {
                System.out.println("Drawing line");
                drawLine(gridPane, waitingPoints.get(0), waitingPoints.get(1));
                waitingPoints.get(0).setWaiting(false);
                waitingPoints.get(1).setWaiting(false);
            }

            else if(waitingPoints.size() > 2) {
                System.out.println("Fix this");
            }

        }));

    }

    private void drawLine(GridPane gridPane, Point p1, Point p2) {

        MyLine myLine = new MyLine(p1, p2);

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

        gridPane.add(line, myLine.getX(), myLine.getY());
    }

    private void setNeighbors(ArrayList<Point> list) {

        list.forEach(point -> {

            int x = point.getX();
            int y = point.getY();

            list.forEach(anotherPoint -> {

                int x1 = anotherPoint.getX();
                int y1 = anotherPoint.getY();

                if ((x1 == x && y1 == y - 2) || (x1 == x + 2 && y1 == y) ||
                        (x1 == x && y1 == y + 2) || (x1 == x - 2 && y1 == y)) {

                    point.addNeighbor(anotherPoint);

                }

            });

        });

    }

    public void makeGreen(ToggleButton button) {
        button.setStyle("-fx-base: #31c318");
    }

    public void makeWhite(ToggleButton button) {
        button.setStyle("-fx-base: #ffffff");
    }

}
