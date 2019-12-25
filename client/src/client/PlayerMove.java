package client;

import game.MyLine;

import java.util.HashMap;

public class PlayerMove {

    private int whoMove;
    private int pastMove;

    private MyLine myMove;

    private String whoMoveLetter;
    private String pastLetter;

    private boolean isGameOver;

    private HashMap<String, Integer> letters;
    private int countSetSquares;

    public PlayerMove() {

    }

    public int getWhoMove() {
        return whoMove;
    }

    public void setWhoMove(int whoMove) {
        whoMoveLetter = (char)(whoMove + 64) + "";
        this.whoMove = whoMove;
    }

    public MyLine getMyMove() {
        return myMove;
    }

    public void setMyMove(MyLine myMove) {
        this.myMove = myMove;
    }

    public String getWhoMoveLetter() {
        return whoMoveLetter;
    }

    public void setWhoMoveLetter(String whoMoveLetter) {
        this.whoMoveLetter = whoMoveLetter;
    }

    public String getPastLetter() {
        return pastLetter;
    }

    public void setPastLetter(String pastLetter) {
        this.pastLetter = pastLetter;
    }

    public int getPastMove() {
        return pastMove;
    }

    public void setPastMove(int pastMove) {
        pastLetter = (char)(pastMove + 64) + "";
        this.pastMove = pastMove;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public HashMap<String, Integer> getLetters() {
        return letters;
    }

    public void setLetters(HashMap<String, Integer> letters) {
        this.letters = letters;
    }

    public int getCountSetSquares() {
        return countSetSquares;
    }

    public void setCountSetSquares(int countSetSquares) {
        this.countSetSquares = countSetSquares;
    }
}
