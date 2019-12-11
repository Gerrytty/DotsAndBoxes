import game.Point;
import game.Square;

import java.util.ArrayList;

public class Squares {

    public void f() {
        int width = 0;

        ArrayList<Point> list = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ": " + list.get(i).getX() + " " + list.get(i).getY());
        }

        System.out.println("---------------------");

        System.out.println(list.size());

        ArrayList<Square> squares = new ArrayList<>();

        for (int i = 0; i < list.size() - width; i++) {
            if(!((i + 1) % width == 0)) {
                Square square = new Square();
                Point p = list.get(i);
                square.addPoint(p);
                square.addPoint(list.get(i + 1));
                square.addPoint(list.get(i + width));
                square.addPoint(list.get(i + width + 1));
                squares.add(square);
            }

        }

        System.out.println("--------------------");

        for (int i = 0; i < squares.size(); i++) {

            Point p1 = squares.get(i).getPoint(0);
            Point p2 = squares.get(i).getPoint(1);
            Point p3 = squares.get(i).getPoint(2);
            Point p4 = squares.get(i).getPoint(3);

            System.out.println("Point 1: " + p1.getX() + " " + p1.getY());
            System.out.println("Point 2: " + p2.getX() + " " + p2.getY());
            System.out.println("Point 3: " + p3.getX() + " " + p3.getY());
            System.out.println("Point 4: " + p4.getX() + " " + p4.getY());
            System.out.println();

        }
    }
}
