package tests;

import game.LinePosition;
import game.MyLine;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(value = Parameterized.class)
public class LineTest {

    private int x;
    private int y;
    private LinePosition position;

    public LineTest(int x, int y, LinePosition position) {
        this.x = x;
        this.y = y;
        this.position = position;
    }

    @Parameterized.Parameters()
    public static Iterable<Object[]> dataForTest() {
        return Arrays.asList(new Object[][]{
                {0, 0, null},
                {1, 0, LinePosition.HORIZONTAL},
                {0, 1, LinePosition.VERTICAL},
                {2, 1, LinePosition.VERTICAL},
                {1, 2, LinePosition.HORIZONTAL}
        });
    }

    @Test
    public void positionTest() {
        MyLine myLine = new MyLine(x, y);
        Assert.assertEquals(position, myLine.getPosition());
    }
}
