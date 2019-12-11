package game;

import java.util.ArrayList;

public class Map {

    private int size;

    private ArrayList<Point> points;

    private ArrayList<Line> lines;

    public Map() {
        this.points = new ArrayList<>();
        this.lines = new ArrayList<>();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }
}
