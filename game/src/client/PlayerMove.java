package client;

import game.MyLine;

public class PlayerMove {

    private int whoMove;
    private int pastMove;

    private MyLine myMove;

    private String whoMoveLetter;
    private String pastLetter;

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
}
