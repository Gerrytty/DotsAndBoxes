package game;

import java.util.ArrayList;

public class Square {

    ArrayList<Point> list;

    public Square() {
        list = new ArrayList<Point>();
    }

    public void addPoint(Point point) {
        list.add(point);
    }

    public Point getPoint(int index) {
        return list.get(index);
    }
}
