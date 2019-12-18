package server;

import game.MyLine;

import java.util.ArrayList;

public class Computer {

    private int points;

    private ArrayList<MyLine> allLines;

    public Computer() {
        points = 0;
    }

    public void addPoint() {
        points++;
    }

    public ArrayList<MyLine> getAllLines() {
        return allLines;
    }

    public void setAllLines(ArrayList<MyLine> allLines) {
        this.allLines = allLines;
    }

    public void removeLine(int number) {
        allLines.remove(number);
    }
}
