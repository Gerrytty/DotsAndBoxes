import game.Point;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Field extends Application {

    public void start(Stage stage) throws Exception {

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

        for (int i = 0; i < w; i++) {
            if(i % 2 != 0) {
                gridPane.getColumnConstraints().add(new ColumnConstraints(100));
            }
            else {
                gridPane.getColumnConstraints().add(new ColumnConstraints(20));
            }
        }

        for (int i = 0; i < h; i++) {
            if(i % 2 != 0) {
                gridPane.getRowConstraints().add(new RowConstraints(100));
            }
            else {
                gridPane.getRowConstraints().add(new RowConstraints(20));
            }
        }

        ArrayList<Point> list = new ArrayList<Point>();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if(i % 2 == 0 && j % 2 == 0) {

                    Button button = new Button();
                    button.setStyle("-fx-background-radius: 30;");
                    final int finalI = i;
                    final int finalJ = j;
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.out.println(finalI + " " + finalJ);
                        }
                    });
                    gridPane.add(button, i, j);
                    list.add(new Point(button, i, j));

                }
            }
        }

        for (int i = 0; i < list.size(); i++) {

            Point point = list.get(i);
            int x = point.getX();
            int y = point.getY();

            for (int j = 0; j < list.size(); j++) {

                Point point1 = list.get(j);
                int x1 = point.getX();
                int y1 = point.getY();

                System.out.println(j + ": " + x1 + " " + y1);

                if((x1 == x && y1 == y - 2)) {
                    System.out.println("1");
                }

                if(x1 == x + 2 && y1 == y) {
                    System.out.println("2");
                }

                if(x1 == x && y1 == y + 2) {
                    System.out.println("3");
                }

                if(x1 == x - 2 && y1 == y) {
                    System.out.println("4");
                }

                if ((x1 == x && y1 == y - 2) || (x1 == x + 2 && y1 == y) ||
                        (x1 == x && y1 == y + 2) || (x1 == x - 2 && y1 == y)) {

                    System.out.println("kek");

                    point.addNeighbor(point1);

                }

            }

            System.out.println("---");

        }

//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).getListOfNeighbors().size());
//        }

        return gridPane;
    }

}
