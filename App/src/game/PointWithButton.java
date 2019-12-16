package game;

import javafx.scene.control.ToggleButton;

import java.util.ArrayList;

public class PointWithButton {

    private Point point;

    private ToggleButton button;

    private ArrayList<PointWithButton> listOfNeighbors;

    public PointWithButton() {
        listOfNeighbors = new ArrayList<>();
    }


    public PointWithButton(Point point) {
        this.point = point;
    }

    public PointWithButton(ToggleButton button, Point point) {
        this(point);
        this.button = button;
    }

    public ToggleButton getButton() {
        return button;
    }

    public void setButton(ToggleButton button) {
        this.button = button;
    }

    public boolean equals(Object obj) {
        PointWithButton p = (PointWithButton)obj;
        return p.getPoint().equals(this.getPoint());
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void add(PointWithButton pointWithButton) {
        listOfNeighbors.add(pointWithButton);
    }

    public void makeNeighborsGreen() {
        listOfNeighbors.forEach(s -> s.button.setStyle("-fx-base: #31c318"));
    }

    public void makeNeighborsWhite() {
        listOfNeighbors.forEach(s -> s.button.setStyle("-fx-base: #ffffff"));
    }
}
