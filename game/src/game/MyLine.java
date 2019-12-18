package game;

public class MyLine {

    private int x;
    private int y;

    private LinePosition position;

    private Point p1;
    private Point p2;

    private boolean isOnField = false;

    public MyLine() {

    }

    public MyLine(LinePosition position, int x, int y) {
        this.position = position;
        this.x = x;
        this.y = y;
    }

    public MyLine(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public MyLine(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;

        int point1x = p1.getX();
        int point2x = p2.getX();
        int point1y = p1.getY();
        int point2y = p2.getY();

        if(point1x == point2x) {
            position = LinePosition.VERTICAL;

            x = point1x;
            y = (point1y < point2y ? point1y : point2y) + 1;
        }

        else if(point1y == point2y) {
            position = LinePosition.HORIZONTAL;

            x = (point1x < point2x ? point1x : point2x) + 1;
            y = point1y;
        }

        else {
            System.out.println("ЫЫЫЫЫЫЫЫЫы");
        }

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

    public LinePosition getPosition() {
        return position;
    }

    public void setPosition(LinePosition position) {
        this.position = position;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    @Override
    public boolean equals(Object o) {
        MyLine line = (MyLine) o;

        return line.x == x && line.y == y;
    }

    public boolean isOnField() {
        return isOnField;
    }

    public void setOnField(boolean onField) {
        isOnField = onField;
    }
}
