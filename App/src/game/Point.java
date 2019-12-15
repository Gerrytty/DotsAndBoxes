package game;

import javafx.scene.control.ToggleButton;

import java.util.ArrayList;

public class Point {

    private int x;
    private int y;
    private boolean isFree;

    private ArrayList<Point> listOfNeighbors;

    private ToggleButton button;

    public Point() {

        listOfNeighbors = new ArrayList<>();
    }

    public Point(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    public Point(ToggleButton button, int x, int y) {
        this(x, y);
        this.button = button;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ToggleButton getButton() {
        return button;
    }

    public void setButton(ToggleButton button) {
        this.button = button;
    }

    public void addNeighbor(Point point) {
        listOfNeighbors.add(point);
    }

    public ArrayList<Point> getListOfNeighbors() {
        return listOfNeighbors;
    }

    public void makeNeighborsGreen() {
        listOfNeighbors.forEach(s -> s.button.setStyle("-fx-base: #31c318"));
    }

    public void makeNeighborsWhite() {
        listOfNeighbors.forEach(s -> s.button.setStyle("-fx-base: #ffffff"));
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    @Override
    public boolean equals(Object obj) {

        Point p = (Point) obj;

        return p.getX() == this.x && p.getY() == this.y;
    }
}
