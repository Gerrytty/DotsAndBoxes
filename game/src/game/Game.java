package game;

import java.util.ArrayList;

public class Game {

    private Mode mode; // single or multi player
    private int height;
    private int width;

    private ArrayList<Point> points;
    private ArrayList<MyLine> lines;
//    private ArrayList<Square> squares;

    public Game() {
        points = new ArrayList<>();
        lines = new ArrayList<>();
//        squares = new ArrayList<>();
    }

    public Game(Mode mode) {
        this();
        this.mode = mode;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public ArrayList<MyLine> getLines() {
        return lines;
    }

    public void setLines(ArrayList<MyLine> lines) {
        this.lines = lines;
    }

    public void addLine(MyLine line) {
        lines.add(line);
    }

    public void removeLine(MyLine line) {
        lines.remove(line);
    }


}
