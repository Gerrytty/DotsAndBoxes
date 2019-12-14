package game;

public class Line {

    private Point A;
    private Point B;

    private String pos;

    public Line() {

    }

    public Line(String pos) {
        this.pos = pos;
    }

    public Line(Point A, Point B) {
        this.A = A;
        this.B = B;
    }

    public Point getA() {
        return A;
    }

    public void setA(Point a) {
        A = a;
    }

    public Point getB() {
        return B;
    }

    public void setB(Point b) {
        B = b;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}
