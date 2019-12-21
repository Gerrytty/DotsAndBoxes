package server;

import game.MyLine;
import game.Square;

import java.util.ArrayList;

public class Move {
    private ArrayList<MyLine> lines;
    private ArrayList<Square> squares;

    public Move() {

    }

    public Move(MyLine line) {
        this.line = line;
    }

    private MyLine line;

    public MyLine getLine() {
        return line;
    }

    public void setLine(MyLine line) {
        this.line = line;
    }

    public void setSquares(ArrayList<Square> squares) {
        this.squares = squares;
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }

    public ArrayList<MyLine> getLines() {
        return lines;
    }

    public void setLines(ArrayList<MyLine> lines) {
        this.lines = lines;
    }
}
