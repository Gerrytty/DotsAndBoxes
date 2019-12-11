package display.windows;

import game.Point;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Field extends Application {

    public void start(Stage stage) {

        Scene scene = new Scene(generateField(2, 3));
        stage.setScene(scene);
        stage.setFullScreen(true);

        // size of the window
        stage.setWidth(600);
        stage.setHeight(500);

        stage.show();

    }

    public GridPane generateField(int height, int width) {

        int h = height * 2 + 1;
        int w = width * 2 + 1;

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(true);

        gridPane.setStyle("-fx-background-color: #09095f");

        setColumns(gridPane, w);
        setRows(gridPane, h);

        ArrayList<Point> list = setPoints(gridPane, h, w);

        setNeighbors(list);
        setAction(list);

        return gridPane;
    }


    private void setAction(ArrayList<Point> allPoints) {

        allPoints.forEach(point -> {

            ToggleButton button = point.getButton();

            button.setOnAction(event -> {

                ArrayList<Point> neighbors = point.getListOfNeighbors();

                if(button.isSelected()) {

                    point.makeNeighborsGreen();

                    allPoints.forEach(p -> { if(!neighbors.contains(p) && !p.equals(point)) p.getButton().setDisable(true); });

                }

                else {

                    allPoints.forEach(p -> { if(!neighbors.contains(p)) p.getButton().setDisable(false); });

                    point.makeNeighborsWhite();

                }

            });

        });

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

    private ArrayList<Point> setPoints(GridPane gridPane, int h, int w) {

        ArrayList<Point> list = new ArrayList<>();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if(i % 2 == 0 && j % 2 == 0) {

                    ToggleButton button = createButton();

                    gridPane.add(button, i, j);
                    list.add(new Point(button, i, j));


                }
            }
        }

        return list;

    }

    private void setNeighbors(ArrayList<Point> list) {

        for (int i = 0; i < list.size(); i++) {

            Point point = list.get(i);
            int x = point.getX();
            int y = point.getY();

            for (int j = 0; j < list.size(); j++) {

                Point point1 = list.get(j);
                int x1 = point1.getX();
                int y1 = point1.getY();

                if ((x1 == x && y1 == y - 2) || (x1 == x + 2 && y1 == y) ||
                        (x1 == x && y1 == y + 2) || (x1 == x - 2 && y1 == y)) {

                    point.addNeighbor(point1);

                }

            }
        }
    }

}
