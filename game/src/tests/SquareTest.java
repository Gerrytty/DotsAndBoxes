package tests;

import game.MyLine;
import game.Square;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SquareTest {

    private Square square1;
    private Square square2;

    @Before
    public void initSquares() {

        square1 = new Square();
        square2 = new Square();

        MyLine line1 = new MyLine();
        MyLine line2 = new MyLine();

        line1.setOnField(true);

        square1.addLine(line1);
        square1.addLine(line1);
        square1.addLine(line2);
        square1.addLine(line2);

        square2.addLine(line1);
        square2.addLine(line2);
        square2.addLine(line2);
        square2.addLine(line2);

    }

    @Test
    public void compareTest() {
        Assert.assertTrue(square1.compareTo(square2) < 0);
    }

}
