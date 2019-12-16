package game;

import java.util.ArrayList;

public class Point {

    private int x;
    private int y;

    private boolean isDisabled;

    private ArrayList<Point> listOfNeighbors;

    public Point() {
        listOfNeighbors = new ArrayList<>();
    }

    public Point(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public ArrayList<Point> getListOfNeighbors() {
        return listOfNeighbors;
    }

    public void setListOfNeighbors(ArrayList<Point> listOfNeighbors) {
        this.listOfNeighbors = listOfNeighbors;
    }

    @Override
    public boolean equals(Object obj) {

        Point p = (Point) obj;

        return p.x == x && p.y == y;
    }

    public void addNeighbor(Point point) {
        listOfNeighbors.add(point);
    }
}
