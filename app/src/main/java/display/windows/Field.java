package display.windows;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Field extends Application {

    public void start(Stage stage) throws Exception {

        // myButton.setEnabled(true);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(100);
        gridPane.setVgap(100);
        gridPane.setGridLinesVisible(true);

        stage.setTitle("Dots and Boxes");
        stage.setFullScreen(true);

        // size of the window
        stage.setWidth(600);
        stage.setHeight(500);

//        int k = 1;
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 5; j++) {
//                Button button = new Button(Integer.toString(k++));
//                gridPane.add(button, i, j);
//            }
//        }
//
//
//        Scene scene = new Scene(gridPane);
//        stage.setScene(scene);
//
//        Text text = new Text("A");
//        text.setX(500);
//        text.setY(500);

        System.out.println(gridPane.getChildren().get(0).getLayoutX() + " " + gridPane.getChildren().get(0).getLayoutY());

//        System.out.println(gridPane.getChildren().stream().forEach(s -> System.out.println(s.getScaleX())));

        stage.show();

    }
}
