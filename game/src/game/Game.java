package game;

import client.PlayerMove;

import java.util.ArrayList;

public class Game {

    private Mode mode; // single or multi player
    private int height;
    private int width;
    private int numberOfPlayers;

    private Player player;

    private ArrayList<Point> points;
    private ArrayList<MyLine> lines;
    private ArrayList<Square> squares;

    private ArrayList<Player> players;

    private PlayerMove playerMove;

    private boolean isGameOver;

    public Game() {
        points = new ArrayList<>();
        lines = new ArrayList<>();
        squares = new ArrayList<>();
        players = new ArrayList<>();
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }

    public void setSquares(ArrayList<Square> squares) {
        this.squares = squares;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public PlayerMove getPlayerMove() {
        return playerMove;
    }

    public void setPlayerMove(PlayerMove playerMove) {
        this.playerMove = playerMove;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}
