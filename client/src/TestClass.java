import game.MyLine;
import game.Square;
import utills.JSON;

import java.util.ArrayList;

public class TestClass {
    public static void main(String[] args) {
        MyLine line = new MyLine();
        Square square = new Square();
        square.addLine(line);
        ArrayList<Square> squares = new ArrayList<>();
        squares.add(square);
        JsonTest test = new JsonTest();
        test.setSquares(squares);
        JSON<JsonTest> j = new JSON<>();
        System.out.println(j.createJSON(test));
    }
}
