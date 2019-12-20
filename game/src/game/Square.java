package game;

import java.util.ArrayList;

public class Square implements Comparable<Square> {

    private int x;
    private int y;

    private String letter = "";

    private ArrayList<MyLine> lines;


    public Square() {
        lines = new ArrayList<>();
    }

    public Square(int x, int y) {
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

    public ArrayList<MyLine> getLines() {
        return lines;
    }

    public void setLines(ArrayList<MyLine> lines) {
        this.lines = lines;
    }

    public void addLine(MyLine line) {
        lines.add(line);
    }

    public void initLines() {
        lines.add(new MyLine(x, y - 1));
        lines.add(new MyLine(x - 1, y));
        lines.add(new MyLine(x, y + 1));
        lines.add(new MyLine(x + 1, y));
    }

    public boolean isSet() {
        return lines.stream().allMatch(MyLine::isOnField);
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getLetter() {
        return letter;
    }

    @Override
    public boolean equals(Object o) {
        Square square = (Square) o;
        return square.x == x && square.y == y;
    }

    private int countLinesOnField() {

        final int[] c = {0};

        lines.forEach(line -> {
            if(line.isOnField()) {
                c[0]++;
            }
        });

        return c[0];
    }

    @Override
    public int compareTo(Square square) {

        if(countLinesOnField() == 2 && square.countLinesOnField() ==  1) {
            return -1;
        }

        if(countLinesOnField() == 1 && square.countLinesOnField() == 2) {
            return 1;
        }

        return countLinesOnField() - square.countLinesOnField();
    }
}