package game;

import java.util.ArrayList;
import java.util.HashMap;

public class Point {

    private int x;
    private int y;

    private boolean isFree;

    private boolean isWaiting;

    public Point() {

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

    public HashMap<Point, ArrayList<Point>> setNeighbors(ArrayList<Point> list) {

        HashMap<Point, ArrayList<Point>> map = new HashMap<>();

        list.forEach(point -> {

            int x = point.getX();
            int y = point.getY();

            ArrayList<Point> neighbors = new ArrayList<>();

            list.forEach(anotherPoint -> {

                int x1 = anotherPoint.getX();
                int y1 = anotherPoint.getY();

                if ((x1 == x && y1 == y - 2) || (x1 == x + 2 && y1 == y) ||
                        (x1 == x && y1 == y + 2) || (x1 == x - 2 && y1 == y)) {

                    neighbors.add(anotherPoint);

                }

            });

            map.put(point, neighbors);

        });

        return map;

    }

    @Override
    public boolean equals(Object o) {
        Point point = (Point) o;

        return point.x == x && point.y == y;
    }
}
