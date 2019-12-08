package display.elements;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageParams {

    public void setParams(Stage stage, Scene scene) {

        stage.setTitle("Dots and Boxes");
        stage.setFullScreen(true);

        // size of the window
        stage.setWidth(600);
        stage.setHeight(500);

        stage.setScene(scene);

    }
}
