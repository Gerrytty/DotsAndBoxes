package game;

import java.util.ArrayList;

public class Point {

    private int x;
    private int y;

    private boolean isFree;

    private boolean isWaiting;

    private ArrayList<Point> neighbors;

    public Point() {
        neighbors = new ArrayList<>();
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

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public boolean isWaiting() {
        return isWaiting;
    }

    public void setWaiting(boolean waiting) {
        isWaiting = waiting;
    }

    public ArrayList<Point> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(ArrayList<Point> neighbor) {
        this.neighbors = neighbor;
    }

    public void addNeighbor(Point point) {
        neighbors.add(point);
    }

    @Override
    public boolean equals(Object o) {
        Point point = (Point) o;

        return point.x == x && point.y == y;
    }
}
