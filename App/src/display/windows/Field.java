package display.windows;

import client.Client;
import client.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.Line;
import game.Point;
import game.PointWithButton;
import game.Square;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Field extends Application {

    public void start(Stage stage) {

        GridPane gridPane = generateField(Main.game.getHeight(), Main.game.getWidth());

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.setFullScreen(true);

        // size of the window
        stage.setWidth(600);
        stage.setHeight(500);

//        ObjectMapper mapper = new ObjectMapper();

//        String jsonValue = null;
//
//        try {
//            jsonValue = mapper.writeValueAsString(Main.game);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        Client.sendMessage(jsonValue);

        stage.show();

    }

    public GridPane generateField(int height, int width) {

        int h = height * 2 + 1;
        int w = width * 2 + 1;

        GridPane gridPane = createGridPane();

        setColumns(gridPane, w);
        setRows(gridPane, h);

        ArrayList<PointWithButton> pointWithButtons = new ArrayList<>();

        ArrayList<Point> list = setPoints(gridPane, pointWithButtons, h, w);

        setNeighbors(list);

//        setActions(pointWithButtons);

//        ArrayList<Square> squares = setSquares(h, w);
//        setAction(list);
//        setLines(gridPane, h, w);
//        addTextOnSquare(gridPane, squares.get(0), new Player("A"));

        return gridPane;
    }

    public GridPane createGridPane() {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(true);

        gridPane.setStyle("-fx-background-color: #09095f");

        return gridPane;
    }

//    private void setActions(ArrayList<PointWithButton> pointWithButtons) {
//
//        pointWithButtons.forEach(pointWithButton -> {
//
//            ToggleButton button = pointWithButton.getButton();
//
//            Point point = pointWithButton.getPoint();
//            ArrayList<Point> neighbours = point.getListOfNeighbors();
//
//            button.setOnAction(event -> {
//
//                for (int i = 0; i < neighbours.size(); i++) {
//                    Point neighbor = neighbours.get(i);
//
//                    for (int j = 0; j < pointWithButtons.size(); j++) {
//
//                        PointWithButton p = pointWithButtons.get(i);
//
//                        if(p.getPoint().equals(neighbor)) {
//                            p.getButton().setDisable(true);
//                        }
//                    }
//
//                }
//
//            });
//
//        });
//
//
//    }


    private void setAction(ArrayList<PointWithButton> allPoints) {

        allPoints.forEach(point -> {

            ToggleButton button = point.getButton();

            button.setOnAction(event -> {

                ArrayList<Point> neighbors = point.getPoint().getListOfNeighbors();

                if(button.isSelected()) {

                    point;

                    allPoints.forEach(p -> { if(!neighbors.contains(p) && !p.equals(point)) p.getButton().setDisable(true); });

                }

                else {

                    allPoints.forEach(p -> { if(!neighbors.contains(p)) p.getButton().setDisable(false); });

//                    point.makeNeighborsWhite();

                }

            });

        });

    }



    public ToggleButton createButton() {

        ToggleButton button = new ToggleButton();

        button.setStyle("-fx-base: #ffffff");
        double r = 10;
        button.setShape(new Circle(r));
        button.setMinSize(2 * r, 2 * r);
        button.setMaxSize(2 * r, 2 * r);

        return button;

    }


//    private void setNeighborsWithButton(ArrayList<PointWithButton> pointWithButtons) {
//
//
//
//    }

    public ArrayList<Point> setPoints(GridPane gridPane, ArrayList<PointWithButton> pointWithButtons,  int h, int w) {

        ArrayList<Point> points = new ArrayList<>();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if(i % 2 == 0 && j % 2 == 0) {

                    ToggleButton button = createButton();

                    gridPane.add(button, i, j);

                    Point point = new Point(i, j);

                    points.add(point);

                    pointWithButtons.add(new PointWithButton(button, point));

                }
            }
        }

        Main.game.setPoints(points);

        return points;

    }

    public ArrayList<Square> setSquares(int h, int w) {

        ArrayList<Square> list = new ArrayList<>();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if(i % 2 != 0 && j % 2 != 0) {
                    list.add(new Square(i, j));
                }
            }
        }

        Main.game.setSquares(list);

        return list;
    }

    private ArrayList<Line> setLines(GridPane gridPane, int h, int w) {

        ArrayList<Line> list = new ArrayList<>();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if(i % 2 != 0 && j % 2 == 0) {

                    list.add(new Line("horizontal"));
                    javafx.scene.shape.Line line = new javafx.scene.shape.Line(0,0,100, 0);
                    line.setStrokeWidth(5);
                    line.setStroke(Color.WHITE);
                    gridPane.add(line, i, j);

                }
                else if(i % 2 == 0 && j % 2 != 0) {

                    list.add(new Line("vertical"));
                    javafx.scene.shape.Line line = new javafx.scene.shape.Line(0,0,0, 100);
                    line.setStrokeWidth(5);
                    line.setTranslateX(8);
                    line.setStroke(Color.WHITE);
                    gridPane.add(line, i, j);

                }
            }
        }

        return list;

    }

    private void addTextOnSquare(GridPane gridPane, Square square, Player player) {

        Text text = new Text(player.getLetter());
        text.setStyle("-fx-font-size: 80px;");
        text.setFill(Color.WHITE);
        text.setTranslateX(20);

        gridPane.add(text, square.getX(), square.getY());

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

}
