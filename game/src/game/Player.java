package game;

import java.util.Random;

public class Player {

    private int points;

    private String letter;

    public Player() {
        points = 0;
        letter = (char)(new Random().nextInt(25) + 65) + "";
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public void addPoint() {
        points++;
    }
}
