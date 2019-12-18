package server;

import game.MyLine;

public class Move {

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
}
