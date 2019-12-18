package display.elements;

import javafx.scene.control.Button;

public class GreenButton {

    private Button button;

    public Button getButton() {
        return button;
    }

    public GreenButton(String text) {
        Button button = new Button(text);

        button.setMinWidth(450);
        button.setMinHeight(60);
        button.setStyle("-fx-font: 22 arial; -fx-base: #218413; -fx-text-fill: #ffffff");

        this.button = button;
    }
}
