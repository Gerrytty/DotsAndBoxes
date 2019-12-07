package game;

public class Game {


    private Mode mode; // single or multi player

    public Game() {

    }

    public Game(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }
}
