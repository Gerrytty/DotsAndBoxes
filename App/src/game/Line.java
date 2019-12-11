package game;

public class Line {

    private Point A;
    private Point B;

    public Line() {

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
}
